package io.github.pheonixvx.fof.entity.abominationskeleton;

import io.github.pheonixvx.fof.registry.RegistryHelper;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class AbominationSkeletonRenderer extends GeoEntityRenderer<AbominationSkeletonEntity> {

	Identifier texture = new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_abomination_skeleton.png");

	public AbominationSkeletonRenderer (EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new AbominationSkeletonModel(false));
	}

	@Override
	public Identifier getTexture (AbominationSkeletonEntity entity) {
		return texture;
	}

	@Override
	public RenderLayer getRenderType (AbominationSkeletonEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
		return RenderLayer.getEntityCutoutNoCull(texture);
	}
}
