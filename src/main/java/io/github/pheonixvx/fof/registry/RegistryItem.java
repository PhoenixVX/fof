package io.github.pheonixvx.fof.registry;

import io.github.pheonixvx.fof.items.BombItem;
import io.github.pheonixvx.fof.items.BoomerangItem;
import io.github.pheonixvx.fof.items.KatanaItem;
import io.github.pheonixvx.fof.items.materials.CutlassToolMaterial;
import io.github.pheonixvx.fof.items.materials.KatanaToolMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class RegistryItem {

	// Items
	public static final Item FOF_BOOMERANG = new BoomerangItem(
		new FabricItemSettings()
			.group(RegistryHandler.ITEM_GROUP)
			.maxDamage(9));

	public static final Item FOF_BOMB = new BombItem(
		new FabricItemSettings()
			.group(RegistryHandler.ITEM_GROUP)
			.maxCount(16)
	);

	public static final Item FOF_ZUUBEE = new Item(
		new FabricItemSettings()
			.group(RegistryHandler.ITEM_GROUP)
			.rarity(Rarity.EPIC)
	);

	// Swords
	public static final Item FOF_IRON_CUTLASS = new SwordItem(
		new CutlassToolMaterial(325, 0F, 0F, 0, Ingredient.ofItems(Items.IRON_INGOT)),
		5,
		-2.4F,
		new FabricItemSettings()
			.group(RegistryHandler.ITEM_GROUP)
			.rarity(Rarity.UNCOMMON)
			.maxCount(1)
	);

	public static final Item FOF_DIAMOND_CUTLASS = new SwordItem(
		new CutlassToolMaterial(1635, 0F, 0F, 0, Ingredient.ofItems(Items.DIAMOND)),
		6,
		-2.4F,
		new FabricItemSettings()
			.group(RegistryHandler.ITEM_GROUP)
			.rarity(Rarity.UNCOMMON)
			.maxCount(1)
	);

	public static final Item FOF_NETHERITE_CUTLASS = new SwordItem(
		new CutlassToolMaterial(2105, 0F, 0F, 0, Ingredient.ofItems(Items.NETHER_GOLD_ORE)),
		7,
		-2.4F,
		new FabricItemSettings()
			.group(RegistryHandler.ITEM_GROUP)
			.rarity(Rarity.UNCOMMON)
			.maxCount(1)
	);

	public static final Item FOF_KATANA = new KatanaItem(
		new KatanaToolMaterial(1562, 0F, 0F, 0, 0, Ingredient.ofItems(Items.IRON_INGOT)),
		5,
		-2F,
		new FabricItemSettings()
			.group(RegistryHandler.ITEM_GROUP)
			.rarity(Rarity.UNCOMMON)
			.maxCount(1)
	);

	// Initialize Item Registry.
	public static void initializeItems () {
		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_boomerang"),
			FOF_BOOMERANG
		);
		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_bomb"),
			FOF_BOMB
		);
		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_zuubee"),
			FOF_ZUUBEE
		);

		// Swords
		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_iron_cutlass"),
			FOF_IRON_CUTLASS
		);

		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_diamond_cutlass"),
			FOF_DIAMOND_CUTLASS
		);

		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_netherite_cutlass"),
			FOF_NETHERITE_CUTLASS
		);

		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_katana"),
			FOF_KATANA
		);

		// Spawn eggs
		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_spawn_abomination_skeleton"),
			new SpawnEggItem(RegistryEntity.ABOMINATION_SKELETON_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHandler.ITEM_GROUP).maxCount(16))
		);

		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_spawn_nether_abomination_skeleton"),
			new SpawnEggItem(RegistryEntity.NETHER_ABOMINATION_SKELETON_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHandler.ITEM_GROUP).maxCount(16))
		);

		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_spawn_goliath_wolf"),
			new SpawnEggItem(RegistryEntity.GOLIATH_WOLF_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHandler.ITEM_GROUP).maxCount(16))
		);

		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_spawn_dweller_bug"),
			new SpawnEggItem(RegistryEntity.DWELLER_BUG_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHandler.ITEM_GROUP).maxCount(16))
		);

		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_spawn_eldritch_gown"),
			new SpawnEggItem(RegistryEntity.ELDRITCH_GOWN_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHandler.ITEM_GROUP).maxCount(16))
		);


		// Block Items
		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_zuubee_mound"),
			new BlockItem(RegistryBlock.FOF_ZUUBEE_MOUND,
				new Item.Settings().group(RegistryHandler.ITEM_GROUP))
		);
		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_zuubee_block"),
			new BlockItem(RegistryBlock.FOF_ZUUBEE_BLOCK,
				new Item.Settings().group(RegistryHandler.ITEM_GROUP))
		);
		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_loot_grass"),
			new BlockItem(RegistryBlock.FOF_LOOT_GRASS,
				new Item.Settings().group(RegistryHandler.ITEM_GROUP))
		);
	}
}
