package io.github.pheonixvx.fof.entity.projectiles;

import io.github.pheonixvx.fof.client.FOFClient;
import io.github.pheonixvx.fof.entity.EntitySpawnPacket;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

@SuppressWarnings("EntityConstructor")
public class AbominationSkeletonProjectileEntity extends PersistentProjectileEntity implements IAnimatable {
	private final AnimationFactory factory = new AnimationFactory(this);

	public AbominationSkeletonProjectileEntity (EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	public AbominationSkeletonProjectileEntity (EntityType<? extends PersistentProjectileEntity> entityType, World world, BlockPos pos, Entity target) {
		super(entityType, world);

		this.setPos(pos.getX(), pos.getY(), pos.getZ());
		double vectorX = target.getX() - pos.getX();
		double vectorY = target.getBodyY(0.3333333333333333D) - pos.getY();
		double vectorZ = target.getZ() - pos.getZ();
		double sqrtVector = MathHelper.sqrt(vectorX * vectorX + vectorZ * vectorZ);
		this.setVelocity(vectorX, vectorY + sqrtVector * 0.20000000298023224D, vectorZ, 0.0000000000001F, (float)(14 - this.world.getDifficulty().getId() * 4));
		Random random = new Random();
		this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 0.8F));
		this.world.spawnEntity(this);
	}

	@Override
	protected void onBlockCollision (BlockState state) {
		super.onBlockCollision(state);
		if (!this.world.isClient) {
			this.remove();
		}
	}

	@Override
	protected ItemStack asItemStack () {
		return null;
	}

	@Override
	public Packet<?> createSpawnPacket () {
		return EntitySpawnPacket.create(this, FOFClient.PACKET_ID_NETHER_ABOMINATION_SKELETON_ARM);
	}

	// Rendering
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("arm_throw", true));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers (AnimationData animationData) {
		animationData.addAnimationController(
			new AnimationController<>(this, "controller", 0, this::predicate)
		);
	}

	@Override
	public AnimationFactory getFactory () {
		return this.factory;
	}
}
