package io.github.pheonixvx.fof.registry;

import io.github.pheonixvx.fof.entity.abominationskeleton.AbominationSkeletonEntity;
import io.github.pheonixvx.fof.entity.abominationskeleton.NetherAbominationSkeletonEntity;
import io.github.pheonixvx.fof.entity.dwellerbug.DwellerBugEntity;
import io.github.pheonixvx.fof.entity.eldritchgown.EldritchGownEntity;
import io.github.pheonixvx.fof.entity.goliathwolf.GoliathWolfEntity;
import io.github.pheonixvx.fof.entity.projectiles.AbominationSkeletonProjectileEntity;
import io.github.pheonixvx.fof.entity.projectiles.BombEntity;
import io.github.pheonixvx.fof.entity.projectiles.BoomerangEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;

@SuppressWarnings("deprecation")
public class RegistryEntity {

	// Projectiles
	public static final EntityType<BoomerangEntity> BOOMERANG_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHelper.MOD_ID, "boomerang_entity"),
		FabricEntityTypeBuilder.<BoomerangEntity>create(SpawnGroup.MISC, BoomerangEntity::new)
			.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
			.trackRangeBlocks(4)
			.trackedUpdateRate(10)
			.build()
	);

	public static final EntityType<BombEntity> BOMB_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHelper.MOD_ID, "bomb_entity"),
		FabricEntityTypeBuilder.<BombEntity>create(SpawnGroup.MISC, BombEntity::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.trackRangeBlocks(4)
			.trackedUpdateRate(10)
			.build()
	);
	
	public static final EntityType<AbominationSkeletonProjectileEntity> NETHER_ABOMINATION_SKELETON_PROJECTILE_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHelper.MOD_ID, "fof_nether_abomination_skeleton_arm"),
		FabricEntityTypeBuilder.<AbominationSkeletonProjectileEntity>create(SpawnGroup.MISC, AbominationSkeletonProjectileEntity::new)
			.trackRangeBlocks(4)
			.trackedUpdateRate(10)
			.build()
	);

	/// Mobs
	public static final EntityType<DwellerBugEntity> DWELLER_BUG_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHelper.MOD_ID, "fof_dweller_bug"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DwellerBugEntity::new)
			.dimensions(EntityDimensions.fixed(2.0f, 2.0f))
			.specificSpawnBlocks(Blocks.STONE)
			.build()
	);

	public static final EntityType<AbominationSkeletonEntity> ABOMINATION_SKELETON_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHelper.MOD_ID, "fof_abomination_skeleton"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AbominationSkeletonEntity::new)
			.dimensions(EntityDimensions.fixed(1.25f, 2.75f))
			.build()
	);

	public static final EntityType<NetherAbominationSkeletonEntity> NETHER_ABOMINATION_SKELETON_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHelper.MOD_ID, "fof_nether_abomination_skeleton"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, NetherAbominationSkeletonEntity::new)
			.dimensions(EntityDimensions.fixed(1.25f, 2.75f))
			.build()
	);

	public static final EntityType<GoliathWolfEntity> GOLIATH_WOLF_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHelper.MOD_ID, "fof_goliath_wolf"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GoliathWolfEntity::new)
			.dimensions(EntityDimensions.fixed(1.75f, 2.50f))
			.build()
	);

	public static final EntityType<EldritchGownEntity> ELDRITCH_GOWN_ENTITY_TYPE = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(RegistryHelper.MOD_ID, "fof_eldritch_gown"),
		FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EldritchGownEntity::new)
			.dimensions(EntityDimensions.fixed(1.40F, 2.75F))
			.build()
	);

	public static void initializeEntities () {
		FabricDefaultAttributeRegistry.register(DWELLER_BUG_ENTITY_TYPE, DwellerBugEntity.createDwellerBugAttributes());

		// TO-DO: Simplify Abomination Skeleton Attributes
		FabricDefaultAttributeRegistry.register(ABOMINATION_SKELETON_ENTITY_TYPE, AbominationSkeletonEntity.createAbominationSkeletonEntityAttributes());
		FabricDefaultAttributeRegistry.register(NETHER_ABOMINATION_SKELETON_ENTITY_TYPE, NetherAbominationSkeletonEntity.createNetherAbominationSkeletonEntityAttributes());

		FabricDefaultAttributeRegistry.register(GOLIATH_WOLF_ENTITY_TYPE, GoliathWolfEntity.createGoliathWolfAttributes());
		FabricDefaultAttributeRegistry.register(ELDRITCH_GOWN_ENTITY_TYPE, EldritchGownEntity.createEldritchGownAttributes());

		// Spawning Restrictions
		SpawnRestrictionAccessor.callRegister(
			DWELLER_BUG_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			RegistryEntity::canEntitySpawn
		);

		SpawnRestrictionAccessor.callRegister(
			ABOMINATION_SKELETON_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			RegistryEntity::canEntitySpawn
		);

		SpawnRestrictionAccessor.callRegister(
			NETHER_ABOMINATION_SKELETON_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			RegistryEntity::canEntitySpawn
		);

		SpawnRestrictionAccessor.callRegister(
			GOLIATH_WOLF_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			RegistryEntity::canEntitySpawn
		);

		SpawnRestrictionAccessor.callRegister(
			ELDRITCH_GOWN_ENTITY_TYPE,
			SpawnRestriction.Location.ON_GROUND,
			Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
			RegistryEntity::canEntitySpawn
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			DWELLER_BUG_ENTITY_TYPE,
			RegistryHelper.config.DWELLER_BUG_WEIGHT,
			RegistryHelper.config.DWELLER_BUG_MIN_SIZE,
			RegistryHelper.config.DWELLER_BUG_MAX_SIZE
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			ABOMINATION_SKELETON_ENTITY_TYPE,
			RegistryHelper.config.ABOMINATION_SKELETON_WEIGHT,
			RegistryHelper.config.ABOMINATION_SKELETON_MIN_SIZE,
			RegistryHelper.config.ABOMINATION_SKELETON_MAX_SIZE
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			NETHER_ABOMINATION_SKELETON_ENTITY_TYPE,
			RegistryHelper.config.NETHER_ABOMINATION_SKELETON_WEIGHT,
			RegistryHelper.config.NETHER_ABOMINATION_SKELETON_MIN_SIZE,
			RegistryHelper.config.NETHER_ABOMINATION_SKELETON_MAX_SIZE
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			GOLIATH_WOLF_ENTITY_TYPE,
			RegistryHelper.config.GOLIATH_WOLF_WEIGHT,
			RegistryHelper.config.GOLIATH_WOLF_MIN_SIZE,
			RegistryHelper.config.GOLIATH_WOLF_MAX_SIZE
		);

		BiomeModifications.addSpawn(
			biomeSelectionContext -> true,
			SpawnGroup.MONSTER,
			ELDRITCH_GOWN_ENTITY_TYPE,
			RegistryHelper.config.ELDRITCH_GOWN_WEIGHT,
			RegistryHelper.config.ELDRITCH_GOWN_MIN_SIZE,
			RegistryHelper.config.ELDRITCH_GOWN_MAX_SIZE
		);
	}

	public static boolean canEntitySpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		// && !world.equals(MCUtils.getServer().getWorld(DOFDimensions.INFINITY_DIM)
		return world.getBlockState(pos.down()).equals(Blocks.CAVE_AIR.getDefaultState()) && !((World) world).isDay();
	}
}
