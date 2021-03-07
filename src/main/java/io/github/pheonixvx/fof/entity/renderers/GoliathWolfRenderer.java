package io.github.pheonixvx.fof.entity.renderers;

import io.github.pheonixvx.fof.entity.GoliathWolfEntity;
import io.github.pheonixvx.fof.entity.models.GoliathWolfModel;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class GoliathWolfRenderer extends GeoEntityRenderer<GoliathWolfEntity> {

	public GoliathWolfRenderer (EntityRenderDispatcher renderManager) {
		super(renderManager, new GoliathWolfModel());
	}

	@Override
	public Identifier getTexture (GoliathWolfEntity entity) {
		return new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_goliath_wolf.png");
	}
}
