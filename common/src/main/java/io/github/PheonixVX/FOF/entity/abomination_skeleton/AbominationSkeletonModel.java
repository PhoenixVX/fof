package io.github.PheonixVX.FOF.entity.abomination_skeleton;

import io.github.PheonixVX.FOF.registry.RegistryHelper;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AbominationSkeletonModel extends AnimatedGeoModel {

    private final boolean isNetherSkeleton;

    public AbominationSkeletonModel(boolean isNetherSkeleton) {
        this.isNetherSkeleton = isNetherSkeleton;
    }

    @Override
    public Identifier getModelLocation (Object abominationSkeletonEntity) {
        return this.isNetherSkeleton ? new Identifier(RegistryHelper.MOD_ID, "geo/fof_abomination_skeleton.json") : new Identifier(RegistryHelper.MOD_ID, "geo/fof_abomination_skeleton.json");
    }

    @Override
    public Identifier getTextureLocation (Object abominationSkeletonEntity) {
        return this.isNetherSkeleton ? new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_nether_abomination_skeleton.png") : new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_abomination_skeleton.png");
    }

    @Override
    public Identifier getAnimationFileLocation (Object abominationSkeletonEntity) {
        return this.isNetherSkeleton ? new Identifier(RegistryHelper.MOD_ID, "animations/fof_abomination_skeleton.animation.json") : new Identifier(RegistryHelper.MOD_ID, "animations/fof_abomination_skeleton.animation.json");
    }
}
