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
		return 0;
	}

	@Override
	public float getMiningSpeedMultiplier () {
		return 0;
	}

	@Override
	public float getAttackDamage () {
		return 0;
	}

	@Override
	public int getMiningLevel () {
		return 0;
	}

	@Override
	public int getEnchantability () {
		return 0;
	}

	@Override
	public Ingredient getRepairIngredient () {
		return null;
	}
}
