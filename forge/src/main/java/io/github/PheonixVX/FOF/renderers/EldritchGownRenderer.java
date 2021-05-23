package io.github.PheonixVX.FOF.renderers;

import io.github.PheonixVX.FOF.entity.eldritch_gown.EldritchGownEntity;
import io.github.PheonixVX.FOF.entity.eldritch_gown.EldritchGownModel;
import io.github.PheonixVX.FOF.registry.RegistryHelper;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EldritchGownRenderer extends GeoEntityRenderer<EldritchGownEntity> {

	private static final Identifier texture = new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_eldritch_gown.png");

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