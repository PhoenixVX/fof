package io.github.PheonixVX.FOF.registry;

import io.github.PheonixVX.FOF.FOF;
import io.github.PheonixVX.FOF.config.ModConfig;
import me.shedaniel.architectury.registry.CreativeTabs;
import me.shedaniel.architectury.registry.DeferredRegister;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

public class RegistryHelper {
    public static final String MOD_ID = "fof";

    public static final ItemGroup ITEM_GROUP = CreativeTabs.create(new Identifier(MOD_ID, "item_group"), () -> new ItemStack(RegistryItem.FOF_ZUUBEE));

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_KEY);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_KEY);
    public static final @NotNull DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(MOD_ID, Registry.ENTITY_TYPE_KEY);

    public static ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public static void initializeRegistries() {
        ITEMS.register();
        BLOCKS.register();
        ENTITIES.register();


        FOF.LOGGER.info("Initializing Items");
        RegistryItem.initializeItems();
        FOF.LOGGER.info("Initializing Blocks");
        RegistryBlock.initializeBlocks();
        FOF.LOGGER.info("Initializing Entities");
        RegistryEntity.initializeEntities();
        FOF.LOGGER.info("Initializing Ores");
        //RegistryOres.initializeOres();
    }
}
