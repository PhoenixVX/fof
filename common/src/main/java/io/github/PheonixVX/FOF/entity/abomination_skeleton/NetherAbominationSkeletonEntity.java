package io.github.PheonixVX.FOF.entity.abomination_skeleton;

import io.github.PheonixVX.FOF.registry.RegistryHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class NetherAbominationSkeletonEntity extends AbstractAbominationSkeletonEntity {
    public static final EntityType<NetherAbominationSkeletonEntity> TYPE = EntityType.Builder.create(NetherAbominationSkeletonEntity::new, SpawnGroup.MONSTER).setDimensions(1.25F, 2.75F).build("fof_nether_abomination_skeleton");

    public NetherAbominationSkeletonEntity (EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createNetherAbominationSkeletonEntityAttributes() {
        return NetherAbominationSkeletonEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, RegistryHelper.config.NETHER_ABOMINATION_SKELETON_ATTACK_DAMAGE).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, RegistryHelper.config.NETHER_ABOMINATION_SKELETON_MOVEMENT_SPEED);
    }

    @Override
    public boolean tryAttack (Entity target) {
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200));
            if (!this.world.isClient) {
                /* TODO: AbominationSkeletonProjectileEntity projectile = new AbominationSkeletonProjectileEntity(RegistryEntity.NETHER_ABOMINATION_SKELETON_PROJECTILE_ENTITY_TYPE, this.world, new BlockPos(this.getPos()), target);
                System.out.println(projectile.getEntityName());*/
            }
        } else {
            super.tryAttack(target);
        }
        return true;
    }
}