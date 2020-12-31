package io.github.pheonixvx.fof.entity;

import io.github.pheonixvx.fof.entity.goals.EntityMeleeAttack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DwellerBugEntity extends HostileEntity implements Monster, IAnimatable {

	private final AnimationFactory animationFactory = new AnimationFactory(this);
	private boolean isBiting = false;
	// private boolean isSpitting = false;

	public DwellerBugEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.ignoreCameraFrustum = true;
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
		this.goalSelector.add(4, new EntityMeleeAttack(this, 1.0D, false, 8.0D));
		// Entity will follow player
		this.targetSelector.add(5, new FollowTargetGoal(this, PlayerEntity.class, true));
		// Entity will attempt revenge
		this.targetSelector.add(3, new RevengeGoal(this, new Class[0]));
	}

	@Override
	public void tick () {
		isBiting = false;
		Box box = new Box(
			Math.abs(this.getX()), Math.abs(this.getY()), Math.abs(this.getZ()),
			Math.abs(this.getX() + 12), Math.abs(this.getY() + 12), Math.abs(this.getZ() + 12)
		);
		/*if (this.world.getClosestEntityIncludingUngeneratedChunks(PlayerEntity.class, new TargetPredicate().setPredicate(entity -> true).setBaseMaxDistance(8.0D), this, this.getX(), this.getY(), this.getZ(), box) != null) {
			isSpitting = true;
		} else */
		if (this.world.getClosestEntityIncludingUngeneratedChunks(PlayerEntity.class, new TargetPredicate().setPredicate(entity -> true).setBaseMaxDistance(2.0D), this, this.getX(), this.getY(), this.getZ(), box) != null) {
			isBiting = true;
		} else {
			isBiting = false;
			// isSpitting = false;
		}
		super.tick();
	}

	// Animation stuff
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.isNavigating()) {
			event.getController().setAnimation(
				new AnimationBuilder().addAnimation("walking", true)
			);
		} else {
			event.getController().setAnimation(
				new AnimationBuilder().addAnimation("idle", true)
			);
		}

		if (this.isBiting) {
			event.getController().setAnimation(
				new AnimationBuilder().addAnimation("bite", true)
			);
		} /*else if (this.isSpitting) {
			event.getController().setAnimation(
				new AnimationBuilder().addAnimation("spitting", true)
			);
		}*/
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers (AnimationData animationData) {
		animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.animationFactory;
	}

	@Override
	public boolean isAngryAt(PlayerEntity player) {
		return true;
	}
}
