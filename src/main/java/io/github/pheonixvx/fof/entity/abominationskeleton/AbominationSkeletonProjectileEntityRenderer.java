package io.github.pheonixvx.fof.entity.abominationskeleton;

import io.github.pheonixvx.fof.client.GeoProjectilesRenderer;
import io.github.pheonixvx.fof.entity.models.AbominationSkeletonProjectileEntityModel;
import io.github.pheonixvx.fof.entity.projectiles.AbominationSkeletonProjectileEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;

public class AbominationSkeletonProjectileEntityRenderer extends GeoProjectilesRenderer<AbominationSkeletonProjectileEntity> {

	public AbominationSkeletonProjectileEntityRenderer (EntityRenderDispatcher renderManager) {
		super(renderManager, new AbominationSkeletonProjectileEntityModel());
	}
}
