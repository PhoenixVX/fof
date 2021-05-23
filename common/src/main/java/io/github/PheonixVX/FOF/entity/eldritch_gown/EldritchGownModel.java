package io.github.PheonixVX.FOF.entity.eldritch_gown;

import io.github.PheonixVX.FOF.registry.RegistryHelper;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EldritchGownModel extends AnimatedGeoModel<EldritchGownEntity> {

    @Override
    public Identifier getModelLocation (EldritchGownEntity entity) {
        return new Identifier(RegistryHelper.MOD_ID, "geo/fof_eldritch_gown.json");
    }

    @Override
    public Identifier getTextureLocation (EldritchGownEntity entity) {
        return new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_eldritch_gown.png");
    }

    @Override
    public Identifier getAnimationFileLocation (EldritchGownEntity entity) {
        return new Identifier(RegistryHelper.MOD_ID, "animations/fof_eldritch_gown.animation.json");
    }

}
