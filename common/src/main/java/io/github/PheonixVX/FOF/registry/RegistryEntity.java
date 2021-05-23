package io.github.PheonixVX.FOF.registry;

import io.github.PheonixVX.FOF.entity.abomination_skeleton.AbominationSkeletonEntity;
import io.github.PheonixVX.FOF.entity.abomination_skeleton.NetherAbominationSkeletonEntity;
import io.github.PheonixVX.FOF.entity.dwellerbug.DwellerBugEntity;
import io.github.PheonixVX.FOF.entity.eldritch_gown.EldritchGownEntity;
import io.github.PheonixVX.FOF.entity.goliath_wolf.GoliathWolfEntity;
import io.github.PheonixVX.FOF.entity.projectiles.BoomerangEntity;

import static me.shedaniel.architectury.registry.entity.EntityAttributes.register;

public class RegistryEntity {

    public static void initializeEntities() {
        // Projectile Entities
        RegistryHelper.ENTITIES.register("fof_boomerang_entity", () -> BoomerangEntity.TYPE);

        // Entities
        RegistryHelper.ENTITIES.register("fof_abomination_skeleton", () -> AbominationSkeletonEntity.TYPE);
        RegistryHelper.ENTITIES.register("fof_nether_abomination_skeleton", () -> NetherAbominationSkeletonEntity.TYPE);
        RegistryHelper.ENTITIES.register("fof_dweller_bug", () -> DwellerBugEntity.TYPE);
        RegistryHelper.ENTITIES.register("fof_eldritch_gown", () -> EldritchGownEntity.TYPE);
        RegistryHelper.ENTITIES.register("fof_goliath_wolf", () -> GoliathWolfEntity.TYPE);

        // Attributes
        register(() -> AbominationSkeletonEntity.TYPE, AbominationSkeletonEntity::createAbominationSkeletonEntityAttributes);
        register(() -> NetherAbominationSkeletonEntity.TYPE, NetherAbominationSkeletonEntity::createNetherAbominationSkeletonEntityAttributes);
        register(() -> DwellerBugEntity.TYPE, DwellerBugEntity::createDwellerBugAttributes);
        register(() -> EldritchGownEntity.TYPE, EldritchGownEntity::createEldritchGownAttributes);
        register(() -> GoliathWolfEntity.TYPE, GoliathWolfEntity::createGoliathWolfAttributes);
    }
}
