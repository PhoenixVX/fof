package io.github.pheonixvx.fof.entity.renderers;

import io.github.pheonixvx.fof.entity.EldritchGownEntity;
import io.github.pheonixvx.fof.entity.models.EldritchGownModel;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class EldritchGownRenderer extends GeoEntityRenderer<EldritchGownEntity> {

	private static final Identifier texture = new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_eldritch_gown.png");

	public EldritchGownRenderer (EntityRenderDispatcher renderManager) {
		super(renderManager, new EldritchGownModel());
	}

	@Override
	public Identifier getTexture (EldritchGownEntity entity) {
		return texture;
	}

	@Override
	public RenderLayer getRenderType (EldritchGownEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
		return RenderLayer.getEntityCutoutNoCull(texture);
	}
}
