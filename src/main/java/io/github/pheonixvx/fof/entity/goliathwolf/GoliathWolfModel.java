package io.github.pheonixvx.fof.entity.goliathwolf;

import io.github.pheonixvx.fof.registry.RegistryHelper;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoliathWolfModel extends AnimatedGeoModel<GoliathWolfEntity> {

	@Override
	public Identifier getModelLocation (GoliathWolfEntity wolfEntity) {
		return new Identifier(RegistryHelper.MOD_ID, "geo/fof_goliath_wolf.json");
	}

	@Override
	public Identifier getTextureLocation (GoliathWolfEntity wolfEntity) {
		return new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_goliath_wolf.png");
	}

	@Override
	public Identifier getAnimationFileLocation (GoliathWolfEntity wolfEntity) {
		return new Identifier(RegistryHelper.MOD_ID, "animations/fof_goliath_wolf.animation.json");
	}
}
