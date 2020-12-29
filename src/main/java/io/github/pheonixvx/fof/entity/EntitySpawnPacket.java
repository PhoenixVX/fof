package io.github.pheonixvx.fof.entity;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

public class EntitySpawnPacket {

	public static Packet<?> create(Entity entity, Identifier packetId) {
		if (entity.world.isClient)
			throw new IllegalStateException("Called entity 'create' on the logical client!");

		PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
		byteBuf.writeVarInt(Registry.ENTITY_TYPE.getRawId(entity.getType()));
		byteBuf.writeUuid(entity.getUuid());
		byteBuf.writeVarInt(entity.getEntityId());

		PacketBufUtil.writeVec3d(byteBuf, entity.getPos());
		PacketBufUtil.writeAngle(byteBuf, entity.pitch);
		PacketBufUtil.writeAngle(byteBuf, entity.yaw);
		return ServerSidePacketRegistry.INSTANCE.toPacket(packetId, byteBuf);
	}

	public static final class PacketBufUtil {

		public static byte packAngle(float angle) {
			return (byte) MathHelper.floor(angle * 256 / 360);
		}

		public static float unpackAngle(byte angleByte) {
			return (angleByte * 360) / 256f;
		}

		public static void writeAngle(PacketByteBuf byteBuf, float angle) {
			byteBuf.writeByte(packAngle(angle));
		}

		public static float readAngle(PacketByteBuf byteBuf) {
			return unpackAngle(byteBuf.readByte());
		}

		public static void writeVec3d(PacketByteBuf byteBuf, Vec3d vec3d) {
			byteBuf.writeDouble(vec3d.x);
			byteBuf.writeDouble(vec3d.y);
			byteBuf.writeDouble(vec3d.z);
		}

		public static Vec3d readVec3d(PacketByteBuf byteBuf) {
			double x = byteBuf.readDouble();
			double y = byteBuf.readDouble();
			double z = byteBuf.readDouble();
			return new Vec3d(x, y, z);
		}
	}
}
