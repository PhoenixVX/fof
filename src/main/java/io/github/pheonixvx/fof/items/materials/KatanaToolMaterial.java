package io.github.pheonixvx.fof.items.materials;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class KatanaToolMaterial implements ToolMaterial {
	final int durability;
	final float miningSpeed;
	final float attackDamage;
	final int miningLevel;
	final int enchantingLevel;
	final Ingredient repairIngredient;

	public KatanaToolMaterial (int durability, float miningSpeed, float attackDamage, int miningLevel, int enchantingLevel, Ingredient repairIngredient) {
		this.durability = durability;
		this.miningSpeed = miningSpeed;
		this.attackDamage = attackDamage;
		this.miningLevel = miningLevel;
		this.enchantingLevel = enchantingLevel;
		this.repairIngredient = repairIngredient;
	}

	@Override
	public int getDurability () {
		return this.durability;
	}

	@Override
	public float getMiningSpeedMultiplier () {
		return this.miningSpeed;
	}

	@Override
	public float getAttackDamage () {
		return this.attackDamage;
	}

	@Override
	public int getMiningLevel () {
		return this.miningLevel;
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
