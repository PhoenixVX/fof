package io.github.pheonixvx.fof.entity.renderers;

import io.github.pheonixvx.fof.entity.AbominationSkeletonEntity;
import io.github.pheonixvx.fof.entity.models.AbominationSkeletonModel;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class AbominationSkeletonRenderer extends GeoEntityRenderer<AbominationSkeletonEntity> {

	public AbominationSkeletonRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new AbominationSkeletonModel());
	}

	@Override
	public Identifier getTexture (AbominationSkeletonEntity entity) {
		return new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_abomination_skeleton.png");
	}
}
