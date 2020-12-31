package io.github.pheonixvx.fof.entity.models;

import io.github.pheonixvx.fof.entity.DwellerBugEntity;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DwellerBugModel extends AnimatedGeoModel<DwellerBugEntity> {

	@Override
	public Identifier getModelLocation(DwellerBugEntity dwellerBugEntity) {
		return new Identifier(RegistryHandler.MOD_ID, "geo/fof_dwellerbug.json");
	}

	@Override
	public Identifier getTextureLocation(DwellerBugEntity dwellerBugEntity)
	{
		return new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_dwellerbug.png");
	}

	@Override
	public Identifier getAnimationFileLocation(DwellerBugEntity dwellerBugEntity)
	{
		return new Identifier(RegistryHandler.MOD_ID, "animations/fof_dwellerbug.animation.json");
	}
}
