package io.github.PheonixVX.FOF.renderers;

import io.github.PheonixVX.FOF.entity.dwellerbug.DwellerBugEntity;
import io.github.PheonixVX.FOF.entity.dwellerbug.DwellerBugModel;
import io.github.PheonixVX.FOF.registry.RegistryHelper;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class DwellerBugRenderer extends GeoEntityRenderer<DwellerBugEntity> {
    public DwellerBugRenderer (EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new DwellerBugModel());
    }

    @Override
    public Identifier getTexture (DwellerBugEntity entity) {
        return new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_dwellerbug.png");
    }
}
