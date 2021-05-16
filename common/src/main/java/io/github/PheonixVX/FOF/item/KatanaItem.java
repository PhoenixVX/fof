package io.github.PheonixVX.FOF.item;

import net.minecraft.entity.Entity;
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
        if (isNaked(target)) {
            target.damage(DamageSource.GENERIC, 5);
        }
        return super.postHit(stack, target, attacker);
    }

    private boolean isNaked(Entity entity) {
        for (ItemStack stack : entity.getArmorItems()) {
            if (!stack.isEmpty()) return false;
        }

        return true;
    }
}