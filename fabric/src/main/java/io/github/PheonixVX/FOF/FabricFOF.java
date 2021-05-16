package io.github.PheonixVX.FOF;

import net.fabricmc.api.ModInitializer;
import software.bernie.geckolib3.GeckoLib;

public class FabricFOF implements ModInitializer {
    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        FOF.init();
    }
}
