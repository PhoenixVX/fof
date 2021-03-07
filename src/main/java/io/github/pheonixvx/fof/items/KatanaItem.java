package io.github.pheonixvx.fof.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class KatanaItem extends SwordItem {

	public KatanaItem (ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}

	@Override
	public boolean postHit (ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (attacker.getArmorItems().spliterator().getExactSizeIfKnown() == 0) {
			attacker.damage(DamageSource.GENERIC, 5);
		}
		return super.postHit(stack, target, attacker);
	}
}
