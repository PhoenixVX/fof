package io.github.pheonixvx.fof.entity.abominationskeleton;

import io.github.pheonixvx.fof.entity.abominationskeleton.AbstractAbominationSkeletonEntity;
import io.github.pheonixvx.fof.registry.RegistryHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class AbominationSkeletonEntity extends AbstractAbominationSkeletonEntity {
	public AbominationSkeletonEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAbominationSkeletonEntityAttributes() {
		return AbominationSkeletonEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, RegistryHelper.config.ABOMINATION_SKELETON_ATTACK_DAMAGE)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, RegistryHelper.config.ABOMINATION_SKELETON_MOVEMENT_SPEED);
	}
}
