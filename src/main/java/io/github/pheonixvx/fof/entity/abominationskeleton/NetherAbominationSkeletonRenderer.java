package io.github.pheonixvx.fof.entity.abominationskeleton;

import io.github.pheonixvx.fof.registry.RegistryHelper;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class NetherAbominationSkeletonRenderer extends GeoEntityRenderer<NetherAbominationSkeletonEntity> {

	Identifier texture = new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_nether_abomination_skeleton.png");

	public NetherAbominationSkeletonRenderer (EntityRenderDispatcher renderManager) {
		super(renderManager, new AbominationSkeletonModel(true));
	}

	@Override
	public Identifier getTexture (NetherAbominationSkeletonEntity entity) {
		return texture;
	}

	@Override
	public RenderLayer getRenderType (NetherAbominationSkeletonEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
		return RenderLayer.getEntityCutoutNoCull(texture);
	}
}
