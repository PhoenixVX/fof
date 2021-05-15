package io.github.pheonixvx.fof.entity.eldritchgown;

import io.github.pheonixvx.fof.entity.goals.EntityMeleeAttack;
import io.github.pheonixvx.fof.entity.goliathwolf.GoliathWolfEntity;
import io.github.pheonixvx.fof.registry.RegistryHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Map;

@SuppressWarnings("EntityConstructor")
public class EldritchGownEntity extends HostileEntity implements Monster, IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);

	public EldritchGownEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.ignoreCameraFrustum = true;
	}

	public static DefaultAttributeContainer.Builder createEldritchGownAttributes() {
		return EldritchGownEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, RegistryHelper.config.ELDRITCH_GOWN_MAX_HEALTH).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, RegistryHelper.config.ELDRITCH_GOWN_ATTACK_DAMAGE);
	}

	@Override
	protected void initGoals () {
		// Entity will walk around.
		this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.175D, 0.0F));
		// Entity will look at Player.
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 12.0F));
		// Entity will look around
		this.goalSelector.add(5, new LookAroundGoal(this));
		// Entity will melee-attack
		this.goalSelector.add(4, new EntityMeleeAttack(this, 0.20D, false, 4.0D));
		// Entity will follow player
		this.targetSelector.add(5, new FollowTargetGoal<>(this, PlayerEntity.class, true));
		// Entity will attempt revenge
		this.targetSelector.add(3, new RevengeGoal(this));
	}

	private <E extends IAnimatable> PlayState predicate (AnimationEvent<E> event) {
		if (!(lastLimbDistance > -0.15F && lastLimbDistance < 0.15F) && !this.isAttacking()) {
			// Assume they are walking
			event.getController().setAnimation(
				new AnimationBuilder().addAnimation("walking", true)
			);
			return PlayState.CONTINUE;
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
		return PlayState.CONTINUE;
	}

	@Override
	public boolean damage (DamageSource source, float amount) {
		// Only allow attacks with enchanted weapons to affect this entity.
		if (source.getAttacker() instanceof PlayerEntity) {
			PlayerEntity entity = (PlayerEntity) source.getAttacker();
			if (entity.getMainHandStack() != null) {
				ItemStack stack = entity.getMainHandStack();
				Map<Enchantment, Integer> enchants = EnchantmentHelper.get(stack);
				if (!enchants.isEmpty() && !this.world.isClient) {
					this.setHealth(this.getHealth() - amount);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean tryAttack (Entity target) {
		if (target instanceof PlayerEntity) {
			PlayerEntity entity = (PlayerEntity) target;
			ItemStack stack = entity.inventory.getArmorStack(2);

			Map<Enchantment, Integer> enchants = EnchantmentHelper.get(stack);
			if (enchants.containsKey(Enchantments.PROTECTION) && !this.world.isClient) {
				entity.damage(DamageSource.GENERIC, (float) (this.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * 3));
			} else {
				this.dealDamage(this, target);
			}
		}
		return super.tryAttack(target);
	}

	@Override
	public void registerControllers (AnimationData animationData) {
		animationData.addAnimationController(
			new AnimationController<>(this, "controller", 0, this::predicate)
		);
	}

	@Override
	public AnimationFactory getFactory () {
		return this.animationFactory;
	}
}
