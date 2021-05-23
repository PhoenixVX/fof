package io.github.PheonixVX.FOF.entity.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;

/*
 * This class mainly exists because :mojank:
 */
public class EntityMeleeAttack extends MeleeAttackGoal {

    private final double range;

    public EntityMeleeAttack (PathAwareEntity mob, double speed, boolean pauseWhenMobIdle, double range) {
        super(mob, speed, pauseWhenMobIdle);
        this.range = range;
    }

    @Override
    protected double getSquaredMaxAttackDistance (LivingEntity entity) {
        return this.range;
    }
}
