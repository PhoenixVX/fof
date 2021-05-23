package io.github.PheonixVX.FOF.renderers;

import io.github.PheonixVX.FOF.entity.goliath_wolf.GoliathWolfEntity;
import io.github.PheonixVX.FOF.entity.goliath_wolf.GoliathWolfModel;
import io.github.PheonixVX.FOF.registry.RegistryHelper;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GoliathWolfRenderer extends GeoEntityRenderer<GoliathWolfEntity> {

    public GoliathWolfRenderer(EntityRenderDispatcher renderManager) {
        super(renderManager, new GoliathWolfModel());
    }

    @Override
    public Identifier getTexture (GoliathWolfEntity entity) {
        return new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_goliath_wolf.png");
    }
}
