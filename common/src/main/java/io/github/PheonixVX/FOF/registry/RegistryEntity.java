package io.github.PheonixVX.FOF.registry;

import io.github.PheonixVX.FOF.entity.projectiles.BoomerangEntity;

public class RegistryEntity {

    public static void initializeEntities() {
        RegistryHelper.ENTITIES.register("fof_boomerang_entity", () -> BoomerangEntity.TYPE);
    }
}
