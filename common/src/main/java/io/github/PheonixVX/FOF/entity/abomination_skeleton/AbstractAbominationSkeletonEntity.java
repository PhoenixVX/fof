package io.github.PheonixVX.FOF.entity.abomination_skeleton;

import io.github.PheonixVX.FOF.entity.goals.EntityMeleeAttack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

@SuppressWarnings("EntityConstructor")
public class AbstractAbominationSkeletonEntity extends HostileEntity implements Monster, IAnimatable {
    private final AnimationFactory animationFactory = new AnimationFactory(this);
    private boolean lowHealth;
    private int ticks;

    public AbstractAbominationSkeletonEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
    }

    @Override
    protected void initGoals() {
        // Entity will walk around.
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.5D, 0.0F));
        // Entity will look at Player.
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 12.0F));
        // Entity will look around
        this.goalSelector.add(5, new LookAroundGoal(this));
        // Entity will melee-attack
        this.goalSelector.add(4, new EntityMeleeAttack(this, 1.0D, false, 8.0D));
        // Entity will follow player
        this.targetSelector.add(5, new FollowTargetGoal<>(this, PlayerEntity.class, true));
        // Entity will attempt revenge
        this.targetSelector.add(3, new RevengeGoal(this));

        // Flee and avoid sunlight
        this.goalSelector.add(2, new AvoidSunlightGoal(this));
        this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0D));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!(lastLimbDistance > -0.15F && lastLimbDistance < 0.15F) && !this.isAttacking()) {
            // Assume they are walking
            event.getController().setAnimation(
                    new AnimationBuilder().addAnimation("walking", true)
            );
            return PlayState.CONTINUE;
        } else if (lowHealth) {
            event.getController().setAnimation(
                    new AnimationBuilder().addAnimation("head_bounce", false)
            );
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }
    }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }

    @Override
    public void tick() {
        super.tick();
        this.lowHealth = this.getHealth() <= 5;
        ticks++;
        // Heal automatically every 3 seconds when low on health
        if (lowHealth && this.ticks == 60) {
            ticks = 0;
            this.setHealth(this.getHealth() + 5);
        }
    }

    /*
     * Set skeleton on fire
     */
    @Override
    public void tickMovement() {
        boolean isAffectedByDaylight = this.isAffectedByDaylight();
        if (isAffectedByDaylight) {
            // Check if the Entity has a helmet of some kind to avoid burning.
            ItemStack itemStack = this.getEquippedStack(EquipmentSlot.HEAD);
            if (!itemStack.isEmpty()) {
                if (itemStack.isDamageable()) {
                    itemStack.setDamage(itemStack.getDamage() + this.random.nextInt(2));
                    if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                        this.sendEquipmentBreakStatus(EquipmentSlot.HEAD);
                        this.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
                    }
                }

                isAffectedByDaylight = false;
            }

            // Burn the Entity if it doesn't have a helmet.
            if (isAffectedByDaylight) {
                this.setOnFireFor(8);
            }
        }
        super.tickMovement();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }
}