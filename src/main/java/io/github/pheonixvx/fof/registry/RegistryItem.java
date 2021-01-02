package io.github.pheonixvx.fof.registry;

import io.github.pheonixvx.fof.items.BombItem;
import io.github.pheonixvx.fof.items.BoomerangItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class RegistryItem {

	// Items
	public static final Item FOF_BOOMERANG = new BoomerangItem(
		new FabricItemSettings()
			.group(RegistryHandler.ITEM_GROUP)
			.maxDamage(251));

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

	// Initialize Item Registry.
	public static void initializeItems() {
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
