package io.github.pheonixvx.fof.entity.models;

import io.github.pheonixvx.fof.entity.EldritchGownEntity;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EldritchGownModel extends AnimatedGeoModel<EldritchGownEntity> {

	@Override
	public Identifier getModelLocation (EldritchGownEntity object) {
		return new Identifier(RegistryHandler.MOD_ID, "geo/fof_eldritch_gown.json");
	}

	@Override
	public Identifier getTextureLocation (EldritchGownEntity object) {
		return new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_eldritch_gown.png");
	}

	@Override
	public Identifier getAnimationFileLocation (EldritchGownEntity animatable) {
		return new Identifier(RegistryHandler.MOD_ID, "animations/fof_eldritch_gown.animation.json");
	}

}
