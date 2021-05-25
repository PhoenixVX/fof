package io.github.PheonixVX.FOF;

import io.github.PheonixVX.FOF.entity.abomination_skeleton.AbominationSkeletonEntity;
import io.github.PheonixVX.FOF.entity.abomination_skeleton.NetherAbominationSkeletonEntity;
import io.github.PheonixVX.FOF.entity.dwellerbug.DwellerBugEntity;
import io.github.PheonixVX.FOF.entity.eldritch_gown.EldritchGownEntity;
import io.github.PheonixVX.FOF.entity.goliath_wolf.GoliathWolfEntity;
import io.github.PheonixVX.FOF.registry.RegistryHelper;
import io.github.PheonixVX.FOF.renderers.*;
import me.shedaniel.architectury.platform.forge.EventBuses;
import me.shedaniel.architectury.registry.entity.EntityRenderers;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModSetup {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(AbominationSkeletonEntity.TYPE, AbominationSkeletonRenderer::new);
            EntityRenderers.register(NetherAbominationSkeletonEntity.TYPE, NetherAbominationSkeletonRenderer::new);
            EntityRenderers.register(DwellerBugEntity.TYPE, DwellerBugRenderer::new);
            EntityRenderers.register(EldritchGownEntity.TYPE, EldritchGownRenderer::new);
            EntityRenderers.register(GoliathWolfEntity.TYPE, GoliathWolfRenderer::new);
        }
    }
}
