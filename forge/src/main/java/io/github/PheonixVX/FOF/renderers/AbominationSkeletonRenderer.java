package io.github.PheonixVX.FOF.renderers;

import io.github.PheonixVX.FOF.entity.abomination_skeleton.AbominationSkeletonEntity;
import io.github.PheonixVX.FOF.entity.abomination_skeleton.AbominationSkeletonModel;
import io.github.PheonixVX.FOF.registry.RegistryHelper;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AbominationSkeletonRenderer extends GeoEntityRenderer<AbominationSkeletonEntity> {

    private final Identifier texture = new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_abomination_skeleton.png");

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