package io.github.pheonixvx.fof.entity.abominationskeleton;

import io.github.pheonixvx.fof.entity.abominationskeleton.AbstractAbominationSkeletonEntity;
import io.github.pheonixvx.fof.entity.projectiles.AbominationSkeletonProjectileEntity;
import io.github.pheonixvx.fof.registry.RegistryEntity;
import io.github.pheonixvx.fof.registry.RegistryHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class NetherAbominationSkeletonEntity extends AbstractAbominationSkeletonEntity {
	public NetherAbominationSkeletonEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	/*@Override
	public boolean tryAttack (Entity target) {
		if (target instanceof LivingEntity) {
			((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200));
			if (!this.world.isClient) {
				AbominationSkeletonProjectileEntity projectile = new AbominationSkeletonProjectileEntity(RegistryEntity.NETHER_ABOMINATION_SKELETON_PROJECTILE_ENTITY_TYPE, this.world, new BlockPos(this.getPos()), target);
				System.out.println(projectile.getEntityName());
			}
		} else {
			super.tryAttack(target);
		}
		return true;
	}*/

	public static DefaultAttributeContainer.Builder createNetherAbominationSkeletonEntityAttributes() {
		return NetherAbominationSkeletonEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, RegistryHelper.config.NETHER_ABOMINATION_SKELETON_ATTACK_DAMAGE)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, RegistryHelper.config.NETHER_ABOMINATION_SKELETON_MOVEMENT_SPEED);
	}
}
