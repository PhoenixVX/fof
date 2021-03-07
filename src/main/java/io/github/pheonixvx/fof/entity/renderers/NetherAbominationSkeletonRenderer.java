package io.github.pheonixvx.fof.entity.renderers;

import io.github.pheonixvx.fof.entity.NetherAbominationSkeletonEntity;
import io.github.pheonixvx.fof.entity.models.NetherAbominationSkeletonEntityModel;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class NetherAbominationSkeletonRenderer extends GeoEntityRenderer<NetherAbominationSkeletonEntity> {

	Identifier texture = new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_nether_abomination_skeleton.png");

	public NetherAbominationSkeletonRenderer (EntityRenderDispatcher renderManager) {
		super(renderManager, new NetherAbominationSkeletonEntityModel());
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
