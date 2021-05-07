package io.github.pheonixvx.fof.entity.abominationskeleton;

import io.github.pheonixvx.fof.entity.abominationskeleton.AbstractAbominationSkeletonEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class AbominationSkeletonEntity extends AbstractAbominationSkeletonEntity {
	public AbominationSkeletonEntity (EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
}
