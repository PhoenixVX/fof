package io.github.PheonixVX.FOF.item;

import io.github.PheonixVX.FOF.entity.projectiles.BombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BombItem extends Item {

    public BombItem (Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        playerEntity.getItemCooldownManager().set(this, 15);

        if (!world.isClient) {
            BombEntity bombEntity = new BombEntity(BombEntity.TYPE, world);
            bombEntity.setItem(itemStack);
            bombEntity.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0f, 1.0f, 1.5f);
            world.spawnEntity(bombEntity);
        }

        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!playerEntity.abilities.creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}