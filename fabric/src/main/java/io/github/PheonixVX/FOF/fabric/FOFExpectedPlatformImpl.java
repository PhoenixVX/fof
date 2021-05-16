package io.github.PheonixVX.FOF.fabric;

import io.github.PheonixVX.FOF.SpawnEntityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.util.Identifier;

public class FOFExpectedPlatformImpl {

    public static Packet<?> createSpawnPacket(Entity entity, Identifier identifier) {
        return SpawnEntityPacket.create(entity, identifier);
    }
}
