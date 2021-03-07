package io.github.pheonixvx.fof.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class AbominationSkeletonEntity extends AbstractAbominationSkeletonEntity {
	public AbominationSkeletonEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
}
