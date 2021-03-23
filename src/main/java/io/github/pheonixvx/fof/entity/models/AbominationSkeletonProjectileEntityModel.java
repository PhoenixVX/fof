package io.github.pheonixvx.fof.entity.models;

import io.github.pheonixvx.fof.entity.projectiles.AbominationSkeletonProjectileEntity;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AbominationSkeletonProjectileEntityModel extends AnimatedGeoModel<AbominationSkeletonProjectileEntity> {
	@Override
	public Identifier getModelLocation (AbominationSkeletonProjectileEntity object) {
		return new Identifier(RegistryHandler.MOD_ID, "geo/fof_nether_abomination_skeleton_arm.json");
	}

	@Override
	public Identifier getTextureLocation (AbominationSkeletonProjectileEntity object) {
		return new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_nether_abomination_skeleton.png");
	}

	@Override
	public Identifier getAnimationFileLocation (AbominationSkeletonProjectileEntity animatable) {
		return new Identifier(RegistryHandler.MOD_ID, "animations/fof_abomination_skeleton.animation.json");
	}
}
