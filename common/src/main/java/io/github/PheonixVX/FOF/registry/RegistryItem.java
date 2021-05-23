package io.github.PheonixVX.FOF.registry;

import io.github.PheonixVX.FOF.item.BombItem;
import io.github.PheonixVX.FOF.item.BoomerangItem;
import io.github.PheonixVX.FOF.item.KatanaItem;
import io.github.PheonixVX.FOF.item.material.CutlassToolMaterial;
import io.github.PheonixVX.FOF.item.material.KatanaToolMaterial;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Rarity;

public class RegistryItem {

    public static final Item FOF_BOOMERANG = new BoomerangItem(new Item.Settings().group(RegistryHelper.ITEM_GROUP).maxDamage(9));
    public static final Item FOF_BOMB = new BombItem(new Item.Settings().group(RegistryHelper.ITEM_GROUP).maxCount(16));
    public static final Item FOF_ZUUBEE = new Item(new Item.Settings().group(RegistryHelper.ITEM_GROUP).rarity(Rarity.EPIC));

    // Swords
    public static final Item FOF_IRON_CUTLASS = new SwordItem(new CutlassToolMaterial(325, 0F, 0F, 0, Ingredient.ofItems(Items.IRON_INGOT)), 5, -2.4F, new Item.Settings().group(RegistryHelper.ITEM_GROUP).rarity(Rarity.UNCOMMON).maxCount(1));
    public static final Item FOF_DIAMOND_CUTLASS = new SwordItem(new CutlassToolMaterial(1635, 0F, 0F, 0, Ingredient.ofItems(Items.DIAMOND)), 6, -2.4F, new Item.Settings().group(RegistryHelper.ITEM_GROUP).rarity(Rarity.UNCOMMON).maxCount(1));
    public static final Item FOF_NETHERITE_CUTLASS = new SwordItem(new CutlassToolMaterial(2105, 0F, 0F, 0, Ingredient.ofItems(Items.NETHER_GOLD_ORE)), 7, -2.4F, new Item.Settings().group(RegistryHelper.ITEM_GROUP).rarity(Rarity.UNCOMMON).maxCount(1));
    public static final Item FOF_KATANA = new KatanaItem(new KatanaToolMaterial(1562, 0F, 0F, 0, 0, Ingredient.ofItems(Items.IRON_INGOT)), 5, -2F, new Item.Settings().group(RegistryHelper.ITEM_GROUP).rarity(Rarity.UNCOMMON).maxCount(1));

    public static void initializeItems() {
        // Projectiles
        RegistryHelper.ITEMS.register("fof_boomerang", () -> FOF_BOOMERANG);
        RegistryHelper.ITEMS.register("fof_bomb", () -> FOF_BOMB);

        // Gems
        RegistryHelper.ITEMS.register("fof_zuubee", () -> FOF_ZUUBEE);

        // Swords
        RegistryHelper.ITEMS.register("fof_iron_cutlass", () -> FOF_IRON_CUTLASS);
        RegistryHelper.ITEMS.register("fof_diamond_cutlass", () -> FOF_DIAMOND_CUTLASS);
        RegistryHelper.ITEMS.register("fof_netherite_cutlass", () -> FOF_NETHERITE_CUTLASS);
        RegistryHelper.ITEMS.register("fof_katana", () -> FOF_KATANA);

        // Spawn eggs
        /*RegistryHelper.ITEMS.register("fof_spawn_abomination_skeleton", () -> new SpawnEggItem(RegistryEntity.ABOMINATION_SKELETON_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHelper.ITEM_GROUP).maxCount(16)));
        RegistryHelper.ITEMS.register("fof_spawn_nether_abomination_skeleton", () -> new SpawnEggItem(RegistryEntity.NETHER_ABOMINATION_SKELETON_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHelper.ITEM_GROUP).maxCount(16)));
        RegistryHelper.ITEMS.register("fof_spawn_goliath_wolf", () -> new SpawnEggItem(RegistryEntity.GOLIATH_WOLF_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHelper.ITEM_GROUP).maxCount(16)));
        RegistryHelper.ITEMS.register("fof_spawn_dweller_bug", () -> new SpawnEggItem(RegistryEntity.DWELLER_BUG_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHelper.ITEM_GROUP).maxCount(16)));
        RegistryHelper.ITEMS.register("fof_spawn_eldritch_gown", () ->new SpawnEggItem(RegistryEntity.ELDRITCH_GOWN_ENTITY_TYPE, 0x00FFFFFF, 0x00FFFFFF, new Item.Settings().group(RegistryHelper.ITEM_GROUP).maxCount(16)));
*/
        // Block Items
        RegistryHelper.ITEMS.register("fof_zuubee_mound", () -> new BlockItem(RegistryBlock.FOF_ZUUBEE_MOUND, new Item.Settings().group(RegistryHelper.ITEM_GROUP)));
        RegistryHelper.ITEMS.register("fof_zuubee_block", () -> new BlockItem(RegistryBlock.FOF_ZUUBEE_BLOCK, new Item.Settings().group(RegistryHelper.ITEM_GROUP)));
        RegistryHelper.ITEMS.register("fof_loot_grass", () -> new BlockItem(RegistryBlock.FOF_LOOT_GRASS, new Item.Settings().group(RegistryHelper.ITEM_GROUP)));
    }
}
