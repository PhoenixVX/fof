package io.github.PheonixVX.FOF.registry;

import me.shedaniel.architectury.hooks.biome.BiomeProperties;
import me.shedaniel.architectury.registry.BiomeModifications;
import me.shedaniel.architectury.registry.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class RegistryBlock {

    // Blocks
    public static final Block FOF_ZUUBEE_MOUND = new Block(AbstractBlock.Settings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block FOF_ZUUBEE_BLOCK = new Block(AbstractBlock.Settings.of(Material.METAL).strength(4.0f));
    public static final Block FOF_LOOT_GRASS = new GrassBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC).nonOpaque().ticksRandomly().noCollision().sounds(BlockSoundGroup.GRASS).strength(0.6F));

    // Ores
    public static ConfiguredFeature<?, ?> FOF_ZUUBEE_MOUND_FEATURE;

    public static void initializeBlocks() {
        RegistryHelper.BLOCKS.register("fof_zuubee_mound", () -> FOF_ZUUBEE_MOUND);
        RegistryHelper.BLOCKS.register("fof_zuubee_block", () -> FOF_ZUUBEE_BLOCK);
        RegistryHelper.BLOCKS.register("fof_loot_grass", () -> FOF_LOOT_GRASS);
    }

    public static void initializeOres() {
        //BiomeModifications.addProperties(RegistryBlock::initProperties);
    }

    private static void initProperties(BiomeModifications.BiomeContext biomeContext, BiomeProperties.Mutable mutable) {
        FOF_ZUUBEE_MOUND_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(RegistryHelper.MOD_ID, "zuubee_mound"), Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, RegistryBlock.FOF_ZUUBEE_MOUND.getDefaultState(), 8)).rangeOf(16).spreadHorizontally());

        mutable.getGenerationProperties().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, FOF_ZUUBEE_MOUND_FEATURE);
    }
}
