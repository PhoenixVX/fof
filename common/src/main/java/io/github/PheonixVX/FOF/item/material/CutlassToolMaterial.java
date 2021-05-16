package io.github.PheonixVX.FOF.item.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CutlassToolMaterial implements ToolMaterial {
    final int durability;
    final float miningSpeedMultiplier;
    final float attackDamage;

    final int enchantingLevel;
    final Ingredient repairIngredient;

    public CutlassToolMaterial (int durability, float miningSpeedMultiplier, float attackDamage, int enchantingLevel, Ingredient repairIngredient) {
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.enchantingLevel = enchantingLevel;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability () {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier () {
        return this.miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage () {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel () {
        return 0;
    }

    @Override
    public int getEnchantability () {
        return this.enchantingLevel;
    }

    @Override
    public Ingredient getRepairIngredient () {
        return this.repairIngredient;
    }
}