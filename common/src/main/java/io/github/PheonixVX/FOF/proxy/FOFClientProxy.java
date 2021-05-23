package io.github.PheonixVX.FOF.proxy;

import io.github.PheonixVX.FOF.entity.projectiles.BombEntity;
import io.github.PheonixVX.FOF.entity.projectiles.BoomerangEntity;
import io.github.PheonixVX.FOF.registry.RegistryBlock;
import io.github.PheonixVX.FOF.registry.RegistryHelper;
import me.shedaniel.architectury.registry.RenderTypes;
import me.shedaniel.architectury.registry.entity.EntityRenderers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

public class FOFClientProxy implements Runnable {

    public static final Identifier PACKET_ID_BOOMERANG = new Identifier(RegistryHelper.MOD_ID, "boomerang_packet");
    public static final Identifier PACKET_ID_BOMB = new Identifier(RegistryHelper.MOD_ID, "bomb_packet");
    public static final Identifier PACKET_ID_NETHER_ABOMINATION_SKELETON_ARM = new Identifier(RegistryHelper.MOD_ID, "nether_abomination_skeleton_arm");

    @Override
    public void run() {
        // Blocks
        RenderTypes.register(RenderLayer.getCutout(), RegistryBlock.FOF_ZUUBEE_MOUND);
        RenderTypes.register(RenderLayer.getCutout(), RegistryBlock.FOF_LOOT_GRASS);

        // Projectile Entity Renderers
        EntityRenderers.register(BoomerangEntity.TYPE, (dispatcher) -> new FlyingItemEntityRenderer<>(dispatcher, MinecraftClient.getInstance().getItemRenderer()));
        EntityRenderers.register(BombEntity.TYPE, (dispatcher) -> new FlyingItemEntityRenderer<>(dispatcher, MinecraftClient.getInstance().getItemRenderer()));

        // Due to GeckoLib (Forge), entity renderers must be in both sides and registered appropriately.
        // Entity Renderers
        // EntityRenderers.register(DwellerBugEntity.TYPE, DwellerBugRenderer::new);
        // EntityRenderers.register(AbominationSkeletonEntity.TYPE, AbominationSkeletonRenderer::new);
        // EntityRenderers.register(NetherAbominationSkeletonEntity.TYPE, NetherAbominationSkeletonRenderer::new);
        // EntityRenderers.register(GoliathWolfEntity.TYPE, GoliathWolfRenderer::new);
        // EntityRenderers.register(EldritchGownEntity.TYPE, EldritchGownRenderer::new);
        // EntityRenderers.register(AbominationSkeletonProjectileEntity.TYPE, AbominationSkeletonProjectileRenderer::new);
    }
}
