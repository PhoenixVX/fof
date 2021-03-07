package io.github.pheonixvx.fof.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class NetherAbominationSkeletonEntity extends AbstractAbominationSkeletonEntity {
	public NetherAbominationSkeletonEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public boolean tryAttack (Entity target) {
		if (target instanceof LivingEntity) {
			((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200));
		} else {
			super.tryAttack(target);
		}
		return true;
	}
}
