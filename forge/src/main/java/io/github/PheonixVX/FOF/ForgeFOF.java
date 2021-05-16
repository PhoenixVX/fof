package io.github.PheonixVX.FOF;

import io.github.PheonixVX.FOF.registry.RegistryHelper;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(RegistryHelper.MOD_ID)
public class ForgeFOF {
    public ForgeFOF() {
        GeckoLib.initialize();
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(RegistryHelper.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FOF.init();
    }
}
