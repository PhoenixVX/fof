package io.github.pheonixvx.fof.registry;

import io.github.pheonixvx.fof.FOF;
import io.github.pheonixvx.fof.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class RegistryHelper {

	public static final String MOD_ID = "fof";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
		new Identifier(MOD_ID, "item_group"))
		    .icon(() -> new ItemStack(RegistryItem.FOF_ZUUBEE))
		    .build();
	public static ModConfig config;

	public static void initializeRegistries () {
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
		FOF.LOGGER.info("Initializing Items");
		RegistryItem.initializeItems();
		FOF.LOGGER.info("Initializing Blocks");
		RegistryBlock.initializeBlocks();
		FOF.LOGGER.info("Initializing Entities");
		RegistryEntity.initializeEntities();
		FOF.LOGGER.info("Initializing Ores");
		RegistryOres.initializeOres();
	}
}
