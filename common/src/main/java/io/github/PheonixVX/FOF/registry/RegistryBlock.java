package io.github.PheonixVX.FOF.registry;

import io.github.PheonixVX.FOF.FOFExpectedPlatform;
import me.shedaniel.architectury.registry.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Arrays;
import java.util.List;

public class RegistryBlock {

    public static final Block FOF_ZUUBEE_MOUND = new Block(AbstractBlock.Settings.of(Material.METAL).strength(4.0f).nonOpaque());
    public static final Block FOF_ZUUBEE_BLOCK = new Block(AbstractBlock.Settings.of(Material.METAL).strength(4.0f));
    public static final Block FOF_LOOT_GRASS = new GrassBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC).nonOpaque().ticksRandomly().noCollision().sounds(BlockSoundGroup.GRASS).strength(0.6F));


    public static void initializeBlocks() {
        RegistryHelper.BLOCKS.register("fof_zuubee_mound", () -> FOF_ZUUBEE_MOUND);
        RegistryHelper.BLOCKS.register("fof_zuubee_block", () -> FOF_ZUUBEE_BLOCK);
        RegistryHelper.BLOCKS.register("fof_loot_grass", () -> FOF_LOOT_GRASS);
    }

    public static void initializeOres() {
    }
}
