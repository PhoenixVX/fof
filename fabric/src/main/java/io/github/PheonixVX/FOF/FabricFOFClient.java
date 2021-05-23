package io.github.PheonixVX.FOF;

import io.github.PheonixVX.FOF.entity.abomination_skeleton.AbominationSkeletonEntity;
import io.github.PheonixVX.FOF.entity.abomination_skeleton.NetherAbominationSkeletonEntity;
import io.github.PheonixVX.FOF.entity.dwellerbug.DwellerBugEntity;
import io.github.PheonixVX.FOF.entity.eldritch_gown.EldritchGownEntity;
import io.github.PheonixVX.FOF.entity.goliath_wolf.GoliathWolfEntity;
import io.github.PheonixVX.FOF.renderers.*;
import me.shedaniel.architectury.registry.entity.EntityRenderers;
import net.fabricmc.api.ClientModInitializer;

public class FabricFOFClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRenderers.register(AbominationSkeletonEntity.TYPE, AbominationSkeletonRenderer::new);
        EntityRenderers.register(NetherAbominationSkeletonEntity.TYPE, NetherAbominationSkeletonRenderer::new);
        EntityRenderers.register(DwellerBugEntity.TYPE, DwellerBugRenderer::new);
        EntityRenderers.register(EldritchGownEntity.TYPE, EldritchGownRenderer::new);
        EntityRenderers.register(GoliathWolfEntity.TYPE, GoliathWolfRenderer::new);
    }
}
