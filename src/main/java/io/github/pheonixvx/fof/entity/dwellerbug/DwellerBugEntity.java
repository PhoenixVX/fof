package io.github.pheonixvx.fof.entity.dwellerbug;

import io.github.pheonixvx.fof.entity.goals.EntityMeleeAttack;
import io.github.pheonixvx.fof.registry.RegistryHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

@SuppressWarnings("EntityConstructor")
public class DwellerBugEntity extends HostileEntity implements Monster, IAnimatable {

	private final AnimationFactory animationFactory = new AnimationFactory(this);

	public DwellerBugEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.ignoreCameraFrustum = true;
	}

	public static DefaultAttributeContainer.Builder createDwellerBugAttributes() {
		return DwellerBugEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, RegistryHelper.config.DWELLER_BUG_ATTACK_DAMAGE).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, RegistryHelper.config.DWELLER_BUG_MOVEMENT_SPEED);
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
		this.goalSelector.add(4, new EntityMeleeAttack(this, 0.250D, false, 8.0D));
		// Entity will follow player
		this.targetSelector.add(5, new FollowTargetGoal<>(this, PlayerEntity.class, true));
		// Entity will attempt revenge
		this.targetSelector.add(3, new RevengeGoal(this));
	}

	// Animation stuff
	private <E extends IAnimatable> PlayState predicate (AnimationEvent<E> event) {
		if (!(lastLimbDistance > -0.15F && lastLimbDistance < 0.15F) && !this.isAttacking()) {
			event.getController().setAnimation(
				new AnimationBuilder().addAnimation("walking", true)
			);
			return PlayState.CONTINUE;
		}
		if (this.isAttacking()) {
			event.getController().setAnimation(
				new AnimationBuilder().addAnimation("bite", true)
			);
			return PlayState.CONTINUE;
		}
		/*else if (this.isSpitting) {
			event.getController().setAnimation(
				new AnimationBuilder().addAnimation("spitting", true)
			);
		}*/
		event.getController().setAnimation(
			new AnimationBuilder().addAnimation("idle", true)
		);
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers (AnimationData animationData) {
		animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory () {
		return this.animationFactory;
	}
}
