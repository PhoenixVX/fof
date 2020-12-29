package io.github.pheonixvx.fof.items;

import io.github.pheonixvx.fof.client.FOFClient;
import io.github.pheonixvx.fof.entity.EntitySpawnPacket;
import io.github.pheonixvx.fof.registry.RegistryEntity;
import io.github.pheonixvx.fof.registry.RegistryItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BoomerangEntity extends ThrownItemEntity {

	public BoomerangEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}

	public BoomerangEntity(World world, LivingEntity owner) {
		super(RegistryEntity.BOOMERANG_ENTITY_TYPE, owner, world); // null will be changed later
	}

	public BoomerangEntity(World world, double x, double y, double z) {
		super(RegistryEntity.BOOMERANG_ENTITY_TYPE, x, y, z, world); // null will be changed later
	}

	@Override
	protected Item getDefaultItem () {
		return RegistryItem.FOF_BOOMERANG;
	}

	@Override
	public Packet createSpawnPacket() {
		return EntitySpawnPacket.create(this, FOFClient.PACKET_ID_BOOMERANG);
	}

	// Called when the Boomerang hits an Entity
	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);

		Entity entity = entityHitResult.getEntity();

		// For minecarts and boats.
		if (entity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) entity;
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40));
		}
	}

	@Override
	protected void onCollision (HitResult hitResult) {
		super.onCollision(hitResult);
		if (!this.world.isClient) {
			this.remove();
		}
	}
}
