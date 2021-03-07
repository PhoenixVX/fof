package io.github.pheonixvx.fof.client;

import io.github.pheonixvx.fof.entity.EntitySpawnPacket;
import io.github.pheonixvx.fof.entity.renderers.*;
import io.github.pheonixvx.fof.registry.RegistryBlock;
import io.github.pheonixvx.fof.registry.RegistryEntity;
import io.github.pheonixvx.fof.registry.RegistryHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public class FOFClient implements ClientModInitializer {

	public static final Identifier PACKET_ID_BOOMERANG = new Identifier(RegistryHandler.MOD_ID, "boomerang_packet");
	public static final Identifier PACKET_ID_BOMB = new Identifier(RegistryHandler.MOD_ID, "bomb_packet");

	@Override
	public void onInitializeClient () {
		// Blocks
		BlockRenderLayerMap.INSTANCE.putBlock(RegistryBlock.FOF_ZUUBEE_MOUND, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(RegistryBlock.FOF_LOOT_GRASS, RenderLayer.getCutout());

		// Entities
		EntityRendererRegistry.INSTANCE.register(
			RegistryEntity.BOOMERANG_ENTITY_TYPE, (dispatcher, context) ->
				                                      new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
		EntityRendererRegistry.INSTANCE.register(
			RegistryEntity.BOMB_ENTITY_TYPE, (dispatcher, context) ->
				                                 new FlyingItemEntityRenderer<>(dispatcher, context.getItemRenderer()));
		EntityRendererRegistry.INSTANCE.register(
			RegistryEntity.DWELLER_BUG_ENTITY_TYPE,
			(dispatcher, context) -> new DwellerBugRenderer(dispatcher)
		);
		EntityRendererRegistry.INSTANCE.register(
			RegistryEntity.ABOMINATION_SKELETON_ENTITY_TYPE,
			(dispatcher, context) -> new AbominationSkeletonRenderer(dispatcher)
		);
		EntityRendererRegistry.INSTANCE.register(
			RegistryEntity.NETHER_ABOMINATION_SKELETON_ENTITY_TYPE,
			(dispatcher, context) -> new NetherAbominationSkeletonRenderer(dispatcher)
		);
		EntityRendererRegistry.INSTANCE.register(
			RegistryEntity.GOLIATH_WOLF_ENTITY_TYPE,
			(dispatcher, context) -> new GoliathWolfRenderer(dispatcher)
		);
		EntityRendererRegistry.INSTANCE.register(
			RegistryEntity.ELDRITCH_GOWN_ENTITY_TYPE,
			(dispatcher, context) -> new EldritchGownRenderer(dispatcher)
		);
		receiveEntityPacket();
	}

	@SuppressWarnings("deprecation")
	public void receiveEntityPacket () {
		ClientSidePacketRegistry.INSTANCE.register(PACKET_ID_BOOMERANG, (ctx, byteBuf) -> {
			EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
			UUID uuid = byteBuf.readUuid();
			int entityId = byteBuf.readVarInt();
			Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
			float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
			float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
			ctx.getTaskQueue().execute(() -> {
				if (MinecraftClient.getInstance().world == null)
					throw new IllegalStateException("Tried to spawn entity in a null world!");
				Entity e = et.create(MinecraftClient.getInstance().world);
				if (e == null)
					throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
				e.updateTrackedPosition(pos);
				e.setPos(pos.x, pos.y, pos.z);
				e.pitch = pitch;
				e.yaw = yaw;
				e.setEntityId(entityId);
				e.setUuid(uuid);
				MinecraftClient.getInstance().world.addEntity(entityId, e);
			});
		});

		ClientSidePacketRegistry.INSTANCE.register(PACKET_ID_BOMB, (ctx, byteBuf) -> {
			EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
			UUID uuid = byteBuf.readUuid();
			int entityId = byteBuf.readVarInt();
			Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
			float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
			float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
			ctx.getTaskQueue().execute(() -> {
				if (MinecraftClient.getInstance().world == null)
					throw new IllegalStateException("Tried to spawn entity in a null world!");
				Entity e = et.create(MinecraftClient.getInstance().world);
				if (e == null)
					throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
				e.updateTrackedPosition(pos);
				e.setPos(pos.x, pos.y, pos.z);
				e.pitch = pitch;
				e.yaw = yaw;
				e.setEntityId(entityId);
				e.setUuid(uuid);
				MinecraftClient.getInstance().world.addEntity(entityId, e);
			});
		});
	}
}
