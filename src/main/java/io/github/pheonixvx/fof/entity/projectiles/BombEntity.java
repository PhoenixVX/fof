package io.github.pheonixvx.fof.entity.projectiles;

import io.github.pheonixvx.fof.client.FOFClient;
import io.github.pheonixvx.fof.entity.EntitySpawnPacket;
import io.github.pheonixvx.fof.registry.RegistryEntity;
import io.github.pheonixvx.fof.registry.RegistryItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

@SuppressWarnings("EntityConstructor")
public class BombEntity extends ThrownItemEntity {
	public BombEntity (EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}

	public BombEntity (World world, LivingEntity owner) {
		super(RegistryEntity.BOMB_ENTITY_TYPE, owner, world);
	}

	@Override
	protected Item getDefaultItem () {
		return RegistryItem.FOF_BOMB;
	}

	@Override
	public Packet<?> createSpawnPacket () {
		return EntitySpawnPacket.create(this, FOFClient.PACKET_ID_BOMB);
	}

	@Override
	protected void onCollision (HitResult hitResult) {
		super.onCollision(hitResult);

		Vec3d position = hitResult.getPos();

		if (!this.world.isClient) {
			world.createExplosion(
				this,
				position.getX(),
				position.getY(),
				position.getZ(),
				1.0f,
				Explosion.DestructionType.BREAK
			);
		}
		this.remove();
	}
}
