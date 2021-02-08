package io.github.pheonixvx.fof.registry;

import io.github.pheonixvx.fof.entity.*;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

public class RegistryEntity {

	public static final EntityType<BoomerangEntity> BOOMERANG_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "boomerang_entity"),
		FabricEntityTypeBuilder.<BoomerangEntity>create(SpawnGroup.MISC, BoomerangEntity::new)
			.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
			.trackRangeBlocks(4)
			.trackedUpdateRate(10)
			.build()
	);

	public static final EntityType<BombEntity> BOMB_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "bomb_entity"),
		FabricEntityTypeBuilder.<BombEntity>create(SpawnGroup.MISC, BombEntity::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.trackRangeBlocks(4)
			.trackedUpdateRate(10)
			.build()
	);

	public static final EntityType<DwellerBugEntity> DWELLER_BUG_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "fof_dweller_bug"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DwellerBugEntity::new)
			.dimensions(EntityDimensions.fixed(2.0f, 2.0f))
			.specificSpawnBlocks(Blocks.STONE)
			.build()
	);

	public static final EntityType<AbominationSkeletonEntity> ABOMINATION_SKELETON_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "fof_abomination_skeleton"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AbominationSkeletonEntity::new)
			.dimensions(EntityDimensions.fixed(1.25f, 2.75f))
			.build()
	);

	public static final EntityType<GoliathWolfEntity> GOLIATH_WOLF_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHandler.MOD_ID, "fof_goliath_wolf"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GoliathWolfEntity::new)
			.dimensions(EntityDimensions.fixed(1.75f, 2.50f))
			.build()
	);

	public static void initializeEntities() {
		FabricDefaultAttributeRegistry.register(
			DWELLER_BUG_ENTITY_TYPE,
			DwellerBugEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7)
		);

		FabricDefaultAttributeRegistry.register(
			ABOMINATION_SKELETON_ENTITY_TYPE,
			AbominationSkeletonEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
		);

		FabricDefaultAttributeRegistry.register(
			GOLIATH_WOLF_ENTITY_TYPE,
			GoliathWolfEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.10)
		);

		// Spawning Restrictions
		SpawnRestrictionAccessor.callRegister(
			DWELLER_BUG_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			MobEntity::canMobSpawn
		);

		SpawnRestrictionAccessor.callRegister(
			ABOMINATION_SKELETON_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			MobEntity::canMobSpawn
		);

		SpawnRestrictionAccessor.callRegister(
			GOLIATH_WOLF_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			MobEntity::canMobSpawn
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			DWELLER_BUG_ENTITY_TYPE,
			10,
			3,
			7
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			ABOMINATION_SKELETON_ENTITY_TYPE,
			15,
			4,
			6
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			GOLIATH_WOLF_ENTITY_TYPE,
			2,
			2,
			4
		);
	}
}
