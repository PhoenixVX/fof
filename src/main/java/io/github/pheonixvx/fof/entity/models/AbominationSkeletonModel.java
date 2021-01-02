package io.github.pheonixvx.fof.entity.models;

import io.github.pheonixvx.fof.entity.AbominationSkeletonEntity;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AbominationSkeletonModel extends AnimatedGeoModel<AbominationSkeletonEntity> {
	@Override
	public Identifier getModelLocation (AbominationSkeletonEntity abominationSkeletonEntity) {
		return new Identifier(RegistryHandler.MOD_ID, "geo/fof_abomination_skeleton.json");
	}

	@Override
	public Identifier getTextureLocation (AbominationSkeletonEntity abominationSkeletonEntity) {
		return new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_abomination_skeleton.png");
	}

	@Override
	public Identifier getAnimationFileLocation (AbominationSkeletonEntity abominationSkeletonEntity) {
		return new Identifier(RegistryHandler.MOD_ID, "animations/fof_abomination_skeleton.animation.json");
	}
}
