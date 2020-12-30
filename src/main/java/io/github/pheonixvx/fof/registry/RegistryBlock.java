package io.github.pheonixvx.fof.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegistryBlock {

	public static final Block FOF_ZUUBEE_MOUND = new Block(
		FabricBlockSettings.of(Material.METAL).hardness(4.0f).nonOpaque()
	);

	public static final Block FOF_ZUUBEE_BLOCK = new Block(
		FabricBlockSettings.of(Material.METAL).hardness(4.0f)
	);

	public static void initializeBlocks() {
		Registry.register(
			Registry.BLOCK,
			new Identifier(RegistryHandler.MOD_ID, "fof_zuubee_mound"),
			FOF_ZUUBEE_MOUND
		);
		Registry.register(
			Registry.BLOCK,
			new Identifier(RegistryHandler.MOD_ID, "fof_zuubee_block"),
			FOF_ZUUBEE_BLOCK
		);
	}
}
