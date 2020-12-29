package io.github.pheonixvx.fof.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BoomerangItem extends Item {

	public BoomerangItem (Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		ItemStack itemStack = playerEntity.getStackInHand(hand);
		// Cooldown on item
		playerEntity.getItemCooldownManager().set(this, 5);

		if (!world.isClient) {
			// Spawn the entity only on the server thread.
			BoomerangEntity boomerangEntity = new BoomerangEntity(world, playerEntity);
			boomerangEntity.setItem(itemStack);
			boomerangEntity.setProperties(playerEntity, playerEntity.pitch - 15f, playerEntity.yaw, 0.0F, 1.0F, 0F);
			world.spawnEntity(boomerangEntity);
		}

		playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
		if (!playerEntity.abilities.creativeMode) {
			// Damage the boomerang after throwing
			itemStack.damage(1, world.random, (ServerPlayerEntity) playerEntity);
		}

		return TypedActionResult.success(playerEntity.getStackInHand(hand));
	}

}
