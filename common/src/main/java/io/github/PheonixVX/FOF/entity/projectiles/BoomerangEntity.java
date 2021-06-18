package io.github.PheonixVX.FOF.entity.projectiles;

import io.github.PheonixVX.FOF.FOFExpectedPlatform;
import io.github.PheonixVX.FOF.proxy.FOFClientProxy;
import io.github.PheonixVX.FOF.registry.RegistryItem;
import me.shedaniel.architectury.networking.NetworkManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings("EntityConstructor")
public class BoomerangEntity extends ThrownItemEntity {

    public static final EntityType<BoomerangEntity> TYPE = EntityType.Builder.create(BoomerangEntity::new, SpawnGroup.MISC).setDimensions(0.25F, 0.25F).trackingTickInterval(10).maxTrackingRange(4).build("fof_boomerang_entity");

    public BoomerangEntity (EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem () {
        return RegistryItem.FOF_BOOMERANG;
    }

    // Called when the Boomerang hits an Entity
    @Override
    protected void onEntityHit (EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        Entity entity = entityHitResult.getEntity();

        // For minecarts and boats.
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40));
            Random random = new Random();
            if (random.nextFloat() < 0.95f) {
                livingEntity.damage(DamageSource.GENERIC, 7);
            } else {
                livingEntity.damage(DamageSource.GENERIC, 5);
            }
        }
    }

    @Override
    protected void onCollision (HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.remove();
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return NetworkManager.createAddEntityPacket(this);
    }
}