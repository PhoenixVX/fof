package io.github.PheonixVX.FOF.fabric;

import io.github.PheonixVX.FOF.registry.RegistryBlock;
import io.github.PheonixVX.FOF.registry.RegistryHelper;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class FOFExpectedPlatformImpl {
    private static final ConfiguredFeature<?, ?> FOF_ZUUBEE_MOUND = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, RegistryBlock.FOF_ZUUBEE_MOUND.getDefaultState(), 8)).rangeOf(16).spreadHorizontally();

    @SuppressWarnings("deprecation")
    public static void initializeOres () {
        RegistryKey<ConfiguredFeature<?, ?>> ZUUBEE_MOUND = RegistryKey.of(
                Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(RegistryHelper.MOD_ID, "zuubee_mound")
        );
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ZUUBEE_MOUND.getValue(), FOF_ZUUBEE_MOUND);
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ZUUBEE_MOUND
        );
    }
}
