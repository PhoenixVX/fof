package io.github.pheonixvx.fof.entity.models;

import io.github.pheonixvx.fof.entity.GoliathWolfEntity;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoliathWolfModel extends AnimatedGeoModel<GoliathWolfEntity> {

	@Override
	public Identifier getModelLocation (GoliathWolfEntity wolfEntity) {
		return new Identifier(RegistryHandler.MOD_ID, "geo/fof_goliath_wolf.json");
	}

	@Override
	public Identifier getTextureLocation (GoliathWolfEntity wolfEntity) {
		return new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_goliath_wolf.png");
	}

	@Override
	public Identifier getAnimationFileLocation (GoliathWolfEntity wolfEntity) {
		return new Identifier(RegistryHandler.MOD_ID, "animations/fof_goliath_wolf.animation.json");
	}
}
