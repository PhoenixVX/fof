package io.github.pheonixvx.fof.entity;

import io.github.pheonixvx.fof.entity.goals.EntityMeleeAttack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class AbominationSkeletonEntity extends HostileEntity implements Monster, IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);

	public AbominationSkeletonEntity (EntityType<? extends HostileEntity> entityType, World world) {
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
		this.targetSelector.add(5, new FollowTargetGoal<>(this, PlayerEntity.class, true));
		// Entity will attempt revenge
		this.targetSelector.add(3, new RevengeGoal(this));
	}

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
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
