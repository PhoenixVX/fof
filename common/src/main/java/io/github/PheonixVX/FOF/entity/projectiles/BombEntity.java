package io.github.PheonixVX.FOF.entity.projectiles;

import io.github.PheonixVX.FOF.registry.RegistryItem;
import me.shedaniel.architectury.networking.NetworkManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

@SuppressWarnings("EntityConstructor")
public class BombEntity extends SnowballEntity {
    public static EntityType<BombEntity> TYPE = EntityType.Builder.create(BombEntity::new, SpawnGroup.MISC).setDimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10).build("fof_bomb_entity");

    public BombEntity(EntityType<? extends SnowballEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return RegistryItem.FOF_BOMB;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        Vec3d position = hitResult.getPos();

        if (!this.world.isClient) {
            world.createExplosion(this, position.getX(), position.getY(), position.getZ(), 1.0f, Explosion.DestructionType.BREAK);
        }
        this.remove();
    }

    /*@Override
    public Packet<?> createSpawnPacket() {
        return NetworkManager.createAddEntityPacket(this);
    }*/
}
