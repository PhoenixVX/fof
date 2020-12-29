package io.github.pheonixvx.fof.registry;

import io.github.pheonixvx.fof.entity.BombEntity;
import io.github.pheonixvx.fof.entity.BoomerangEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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

	public static void initializeEntities() {

	}
}
