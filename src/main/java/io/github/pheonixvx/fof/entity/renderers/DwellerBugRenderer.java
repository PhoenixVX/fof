package io.github.pheonixvx.fof.entity.renderers;

import io.github.pheonixvx.fof.entity.DwellerBugEntity;
import io.github.pheonixvx.fof.entity.models.DwellerBugModel;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class DwellerBugRenderer extends GeoEntityRenderer<DwellerBugEntity> {

	public DwellerBugRenderer (EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new DwellerBugModel());
	}

	@Override
	public Identifier getTexture (DwellerBugEntity entity) {
		return new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_dwellerbug.png");
	}
}
