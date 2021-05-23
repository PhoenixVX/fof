package io.github.PheonixVX.FOF.entity.dwellerbug;

import io.github.PheonixVX.FOF.registry.RegistryHelper;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DwellerBugModel extends AnimatedGeoModel<DwellerBugEntity> {
    @Override
    public Identifier getModelLocation (DwellerBugEntity dwellerBugEntity) {
        return new Identifier(RegistryHelper.MOD_ID, "geo/fof_dwellerbug.json");
    }

    @Override
    public Identifier getTextureLocation (DwellerBugEntity dwellerBugEntity) {
        return new Identifier(RegistryHelper.MOD_ID, "textures/entity/fof_dwellerbug.png");
    }

    @Override
    public Identifier getAnimationFileLocation (DwellerBugEntity dwellerBugEntity) {
        return new Identifier(RegistryHelper.MOD_ID, "animations/fof_dwellerbug.animation.json");
    }
}
