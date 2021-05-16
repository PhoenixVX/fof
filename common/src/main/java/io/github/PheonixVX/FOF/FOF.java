package io.github.PheonixVX.FOF;

import io.github.PheonixVX.FOF.config.ModConfig;
import io.github.PheonixVX.FOF.proxy.FOFClientProxy;
import io.github.PheonixVX.FOF.registry.RegistryHelper;
import me.shedaniel.architectury.utils.EnvExecutor;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.EnvType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FOF {
    public static final Logger LOGGER = LogManager.getLogger(RegistryHelper.MOD_ID);

    // Registering a new creative tab
    /*public static final ItemGroup EXAMPLE_TAB = CreativeTabs.create(new Identifier(MOD_ID, "example_tab"), new Supplier<ItemStack>() {
        @Override
        public ItemStack get() {
            return new ItemStack(EXAMPLE_ITEM.get());
        }
    });
    
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_KEY);
    public static final RegistrySupplier<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () ->
            new Item(new Item.Settings().group(FOF.EXAMPLE_TAB)));*/
    
    public static void init() {
        //ITEMS.register();
        EnvExecutor.runInEnv(EnvType.CLIENT, FOFClientProxy::new);

        LOGGER.info("Forge of Fiends is loading...");
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        RegistryHelper.initializeRegistries();
    }
}
