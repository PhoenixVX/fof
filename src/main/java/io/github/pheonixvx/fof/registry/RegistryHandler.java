package io.github.pheonixvx.fof.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class RegistryHandler {

	public static String MOD_ID = "fof";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
		new Identifier(MOD_ID, "item_group"))
		.icon(() -> new ItemStack(RegistryItem.FOF_BOOMERANG))
		.build();

	public static void initializeRegistries() {
		RegistryItem.initializeItems();
		RegistryBlock.initializeBlocks();
		RegistryEntity.initializeEntities();
	}
}
