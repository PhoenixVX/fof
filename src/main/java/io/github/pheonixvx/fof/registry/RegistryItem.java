package io.github.pheonixvx.fof.registry;

import io.github.pheonixvx.fof.items.BoomerangItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegistryItem {

	// Items
	public static final Item FOF_BOOMERANG = new BoomerangItem(
		new FabricItemSettings()
			.group(RegistryHandler.ITEM_GROUP)
			.maxDamage(9));

	public static void initializeItems() {
		Registry.register(
			Registry.ITEM,
			new Identifier(RegistryHandler.MOD_ID, "fof_boomerang"),
			FOF_BOOMERANG);
	}
}
