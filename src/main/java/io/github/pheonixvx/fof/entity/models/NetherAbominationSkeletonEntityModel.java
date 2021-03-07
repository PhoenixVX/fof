package io.github.pheonixvx.fof.entity.models;

import io.github.pheonixvx.fof.entity.NetherAbominationSkeletonEntity;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NetherAbominationSkeletonEntityModel extends AnimatedGeoModel<NetherAbominationSkeletonEntity> {
	@Override
	public Identifier getModelLocation (NetherAbominationSkeletonEntity object) {
		return new Identifier(RegistryHandler.MOD_ID, "geo/fof_abomination_skeleton.json");
	}

	@Override
	public Identifier getTextureLocation (NetherAbominationSkeletonEntity object) {
		return new Identifier(RegistryHandler.MOD_ID, "textures/entity/fof_nether_abomination_skeleton.png");
	}

	@Override
	public Identifier getAnimationFileLocation (NetherAbominationSkeletonEntity animatable) {
		return new Identifier(RegistryHandler.MOD_ID, "animations/fof_abomination_skeleton.animation.json");
	}
}
