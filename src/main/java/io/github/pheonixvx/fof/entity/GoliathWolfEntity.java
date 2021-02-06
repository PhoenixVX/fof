package io.github.pheonixvx.fof.entity;

import io.github.pheonixvx.fof.entity.goals.EntityMeleeAttack;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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

public class GoliathWolfEntity extends HostileEntity implements Monster, IAnimatable, Saddleable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);
	private final SaddledComponent saddledComponent;
	private static final TrackedData<Boolean> SADDLED = DataTracker.registerData(GoliathWolfEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<Integer> BOOST_TIME = DataTracker.registerData(GoliathWolfEntity.class, TrackedDataHandlerRegistry.INTEGER);

	protected float jumpStrength;

	public GoliathWolfEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.ignoreCameraFrustum = true;
		this.saddledComponent = new SaddledComponent(this.dataTracker, BOOST_TIME, SADDLED);
	}

	@Override
	protected void initGoals() {
		// Entity will walk around.
		this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.25D, 0.0F));
		// Entity will look at Player.
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 12.0F));
		// Entity will look around
		this.goalSelector.add(5, new LookAroundGoal(this));
		// Entity will melee-attack
		this.goalSelector.add(4, new EntityMeleeAttack(this, 0.75D, false, 8.0D));
		// Entity will follow player
		this.targetSelector.add(5, new FollowTargetGoal(this, PlayerEntity.class, true));
		// Entity will attempt revenge
		this.targetSelector.add(3, new RevengeGoal(this));
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(SADDLED, false);
		this.dataTracker.startTracking(BOOST_TIME, 0);
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
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
		animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
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
		if (!wolves.isEmpty() && !this.world.isClient()) {
			// Wolves are in the immediate area
			for (int i = 0; i < wolves.size(); i++) {
				WolfEntity wolf = wolves.get(i);
				PlayerEntity player = this.world.getClosestPlayer(this, 10);
				if (player != null && !player.isCreative()) {
					wolf.setTarget(player);
				}
			}
		}
	}

	@Override
	public boolean canBeSaddled () {
		return this.getHealth() <= 10;
	}

	@Override
	public void saddle (@Nullable SoundCategory sound) {
		this.saddledComponent.setSaddled(true);
		if (sound != null) {
			this.world.playSoundFromEntity(null, this, SoundEvents.ENTITY_PIG_SADDLE, sound, 0.5F, 1.0F);
		}
	}

	@Override
	public boolean isSaddled () {
		return this.saddledComponent.isSaddled();
	}

	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		//boolean bl = this.isBreedingItem(player.getStackInHand(hand));
		if (this.isSaddled() && !this.hasPassengers() && !player.shouldCancelInteraction()) {
			if (!this.world.isClient) {
				player.yaw = this.yaw;
				player.pitch = this.pitch;
				player.startRiding(this);
			}

			return ActionResult.success(this.world.isClient);
		} else {
			ActionResult actionResult = super.interactMob(player, hand);
			if (!actionResult.isAccepted()) {
				ItemStack itemStack = player.getStackInHand(hand);
				return itemStack.getItem() == Items.SADDLE ? itemStack.useOnEntity(player, this, hand) : ActionResult.PASS;
			} else {
				return actionResult;
			}
		}
	}

	@Override
	public void travel(Vec3d movementInput) {
		if (this.isAlive()) {
			if (this.hasPassengers() && this.canBeControlledByRider() && this.isSaddled()) {
				LivingEntity livingEntity = (LivingEntity) this.getPrimaryPassenger();
				if (livingEntity != null) {
					System.out.println("It's working");

					this.yaw = livingEntity.yaw;
					this.prevYaw = this.yaw;
					this.pitch = livingEntity.pitch * 0.5F;
					this.setRotation(this.yaw, this.pitch);
					this.bodyYaw = this.yaw;
					this.headYaw = this.bodyYaw;
					float sideWaysSpeed = livingEntity.sidewaysSpeed * 0.5F;
					float forwardSpeed = livingEntity.forwardSpeed;
					if (forwardSpeed <= 0.0F) {
						forwardSpeed *= 0.25F;
					}

					if (this.onGround && this.jumpStrength == 0.0F && !this.jumping) {
						sideWaysSpeed = 0.0F;
						forwardSpeed = 0.0F;
					}

					if (this.jumpStrength > 0.0F && this.isDescending() && this.onGround) {
						double d = this.getJumpStrength() * (double) this.jumpStrength * (double) this.getJumpVelocityMultiplier();
						double h;
						if (this.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
							h = d + (double) ((float) (this.getStatusEffect(StatusEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
						} else {
							h = d;
						}

						Vec3d velocity = this.getVelocity();
						this.setVelocity(velocity.x, h, velocity.z);
						this.velocityDirty = true;
						if (forwardSpeed > 0.0F) {
							float i = MathHelper.sin(this.yaw * 0.017453292F);
							float j = MathHelper.cos(this.yaw * 0.017453292F);
							this.setVelocity(this.getVelocity().add(-0.4F * i * this.jumpStrength, 0.0D, 0.4F * j * this.jumpStrength));
						}

						this.jumpStrength = 0.0F;
					}

					this.flyingSpeed = this.getMovementSpeed() * 0.1F;
					if (this.isLogicalSideForUpdatingMovement()) {
						this.setMovementSpeed((float) this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
						super.travel(new Vec3d(sideWaysSpeed, movementInput.y, forwardSpeed));
					} else if (livingEntity instanceof PlayerEntity) {
						this.setVelocity(Vec3d.ZERO);
					}

					if (this.onGround) {
						this.jumpStrength = 0.0F;
					}

					this.method_29242(this, false);
				}
			} else {
				this.flyingSpeed = 0.02F;
				super.travel(movementInput);
			}
		}
	}

	public double getJumpStrength() {
		return this.getAttributeValue(EntityAttributes.HORSE_JUMP_STRENGTH);
	}

	@Override
	public boolean canBeControlledByRider () {
		return true;
	}

	@Override
	public void writeCustomDataToTag(CompoundTag tag) {
		super.writeCustomDataToTag(tag);
		this.saddledComponent.toTag(tag);
	}

	@Override
	public void readCustomDataFromTag(CompoundTag tag) {
		super.readCustomDataFromTag(tag);
		this.saddledComponent.fromTag(tag);
	}

	@Nullable
	@Override
	public Entity getPrimaryPassenger () {
		return this.getPassengerList().isEmpty() ? null : this.getPassengerList().get(0);
	}
}
