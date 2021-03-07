package io.github.pheonixvx.fof.entity;

import io.github.pheonixvx.fof.entity.goals.EntityMeleeAttack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("EntityConstructor")
public class GoliathWolfEntity extends HorseBaseEntity implements Monster, IAnimatable, Saddleable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);
	protected float jumpStrength = 0.5F;

	public GoliathWolfEntity (EntityType<? extends HorseBaseEntity> entityType, World world) {
		super(entityType, world);
		this.ignoreCameraFrustum = true;
	}

	@Override
	protected void initGoals () {
		// Entity will walk around.
		this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.50D, 0.0F));
		// Entity will look at Player.
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 12.0F));
		// Entity will look around
		this.goalSelector.add(5, new LookAroundGoal(this));
		// Entity will melee-attack
		this.goalSelector.add(4, new EntityMeleeAttack(this, 1.0D, false, 8.0D));
		// Entity will follow player
		this.targetSelector.add(5, new FollowTargetGoal<>(this, PlayerEntity.class, true));
		// Entity will attempt revenge
		this.targetSelector.add(3, new RevengeGoal(this));
	}

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

	@Override
	public void tick () {
		super.tick();
		Box box = this.getBoundingBox().expand(10, 10, 10);
		List<WolfEntity> wolves = this.world.getEntitiesByType(EntityType.WOLF, box, EntityPredicates.canBePushedBy(this));
		PlayerEntity player = this.world.getClosestPlayer(this, 10);
		if (!wolves.isEmpty() && !this.world.isClient() && player != null && !player.getUuid().equals(this.getOwnerUuid())) {
			// Wolves are in the immediate area
			for (WolfEntity wolf : wolves) {
				if (!player.isCreative()) {
					wolf.setTarget(player);
				}
			}
		}
	}

	@Override
	public boolean tryAttack (Entity target) {
		if (target.getUuid().equals(this.getOwnerUuid()) || this.isTame()) {
			// This is our owner or we are tamed, do not attack.
			// Attack hostile entities in the area
			Box box = this.getBoundingBox().expand(5, 3, 5);
			List<HostileEntity> hostiles = this.world.getEntitiesByClass(HostileEntity.class, box, EntityPredicates.canBePushedBy(this));
			if (!hostiles.isEmpty()) {
				for (HostileEntity hostile : hostiles) {
					this.getNavigation().startMovingTo(hostile, 8.0f);
					this.getLookControl().lookAt(hostile, 30.0F, 30.0F);
					this.setTarget(hostile);
					return super.tryAttack(hostile);
				}
			}

			return false;
		} else {
			return super.tryAttack(target);
		}
	}

	@Override
	public boolean canBeSaddled () {
		PlayerEntity player = this.world.getClosestPlayer(this, 3);
		if (player != null && player.getUuid().equals(this.getOwnerUuid())) {
			return true;
		} else {
			return this.getHealth() <= 10;
		}
	}

	@Override
	public ActionResult interactMob (PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		ActionResult actionResult = itemStack.useOnEntity(player, this, hand);
		if (actionResult.isAccepted()) {
			return actionResult;
		}

		if (this.isSaddled() && !this.hasPassengers() && !player.shouldCancelInteraction()) {
			if (!this.world.isClient) {
				player.yaw = this.yaw;
				player.pitch = this.pitch;
				this.setTame(true);
				this.setOwnerUuid(player.getUuid());
				this.putPlayerOnBack(player);
			}
		}
		return ActionResult.success(this.world.isClient);
	}

	@Override
	public double getJumpStrength () {
		return this.jumpStrength;
	}

	@Override
	public boolean canBeControlledByRider () {
		return true;
	}

	@Nullable
	@Override
	public Entity getPrimaryPassenger () {
		return this.getPassengerList().isEmpty() ? null : this.getPassengerList().get(0);
	}

	@Override
	public void writeCustomDataToTag (CompoundTag tag) {
		tag.putBoolean("EatingHaystack", this.isEatingGrass());
		tag.putBoolean("Bred", this.isBred());
		tag.putInt("Temper", this.getTemper());
		tag.putBoolean("Tame", this.isTame());
		if (this.getOwnerUuid() != null) {
			tag.putUuid("Owner", this.getOwnerUuid());
		}

		if (!this.items.getStack(0).isEmpty()) {
			tag.put("SaddleItem", this.items.getStack(0).toTag(new CompoundTag()));
		} else if (this.isSaddled()) {
			tag.put("SaddleItem", new ItemStack(Items.SADDLE).toTag(new CompoundTag()));
		}
	}

	@Override
	public void readCustomDataFromTag (CompoundTag tag) {
		this.setEatingGrass(tag.getBoolean("EatingHaystack"));
		this.setBred(tag.getBoolean("Bred"));
		this.setTemper(tag.getInt("Temper"));
		this.setTame(tag.getBoolean("Tame"));
		UUID uUID2;
		if (tag.containsUuid("Owner")) {
			uUID2 = tag.getUuid("Owner");
		} else {
			String string = tag.getString("Owner");
			uUID2 = ServerConfigHandler.getPlayerUuidByName(this.getServer(), string);
		}

		if (uUID2 != null) {
			this.setOwnerUuid(uUID2);
		}

		if (tag.contains("SaddleItem", 10)) {
			ItemStack itemStack = ItemStack.fromTag(tag.getCompound("SaddleItem"));
			if (itemStack.getItem() == Items.SADDLE) {
				this.items.setStack(0, itemStack);
			}
		}
		this.updateSaddle();
	}
}
