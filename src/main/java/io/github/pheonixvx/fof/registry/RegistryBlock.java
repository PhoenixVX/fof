package io.github.pheonixvx.fof.registry;

import io.github.pheonixvx.fof.blocks.LootGrassBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegistryBlock {

	public static final Block FOF_ZUUBEE_MOUND = new Block(
		FabricBlockSettings.of(Material.METAL).hardness(4.0f).nonOpaque()
	);

	public static final Block FOF_ZUUBEE_BLOCK = new Block(
		FabricBlockSettings.of(Material.METAL).hardness(4.0f)
	);

	public static final Block FOF_LOOT_GRASS = new LootGrassBlock(
		FabricBlockSettings.of(Material.SOLID_ORGANIC).breakByHand(true).nonOpaque().ticksRandomly().noCollision().sounds(BlockSoundGroup.GRASS).strength(0.6F)
	);

	public static void initializeBlocks () {
		Registry.register(
			Registry.BLOCK,
			new Identifier(RegistryHelper.MOD_ID, "fof_zuubee_mound"),
			FOF_ZUUBEE_MOUND
		);
		Registry.register(
			Registry.BLOCK,
			new Identifier(RegistryHelper.MOD_ID, "fof_zuubee_block"),
			FOF_ZUUBEE_BLOCK
		);
		Registry.register(
			Registry.BLOCK,
			new Identifier(RegistryHelper.MOD_ID, "fof_loot_grass"),
			FOF_LOOT_GRASS
		);
	}
}
