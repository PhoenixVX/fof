package io.github.PheonixVX.FOF.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@SuppressWarnings("CanBeFinal") // final makes the values not change in AutoConfig.
@Config(name = "fof")
public class ModConfig implements ConfigData {
    public int DWELLER_BUG_ATTACK_DAMAGE = 7;
    public double DWELLER_BUG_MOVEMENT_SPEED = 0.425D;

    public int ABOMINATION_SKELETON_ATTACK_DAMAGE = 5;
    public double ABOMINATION_SKELETON_MOVEMENT_SPEED = 0.2625D;

    public int NETHER_ABOMINATION_SKELETON_ATTACK_DAMAGE = 5;
    public double NETHER_ABOMINATION_SKELETON_MOVEMENT_SPEED = 0.2625D;

    public int GOLIATH_WOLF_MAX_HEALTH = 50;
    public int GOLIATH_WOLF_ATTACK_DAMAGE = 8;
    public double GOLIATH_WOLF_MOVEMENT_SPEED = 0.35D;

    public int ELDRITCH_GOWN_MAX_HEALTH = 30;
    public int ELDRITCH_GOWN_ATTACK_DAMAGE = 3;

    // Spawning parameters (Weights, minimum group size, maximum group size)
    public int DWELLER_BUG_WEIGHT = 10;
    public int DWELLER_BUG_MIN_SIZE = 2;
    public int DWELLER_BUG_MAX_SIZE = 5;

    public int ABOMINATION_SKELETON_WEIGHT = 15;
    public int ABOMINATION_SKELETON_MIN_SIZE = 4;
    public int ABOMINATION_SKELETON_MAX_SIZE = 6;

    public int NETHER_ABOMINATION_SKELETON_WEIGHT = 15;
    public int NETHER_ABOMINATION_SKELETON_MIN_SIZE = 4;
    public int NETHER_ABOMINATION_SKELETON_MAX_SIZE = 6;

    public int GOLIATH_WOLF_WEIGHT = 1;
    public int GOLIATH_WOLF_MIN_SIZE = 2;
    public int GOLIATH_WOLF_MAX_SIZE = 4;

    public int ELDRITCH_GOWN_WEIGHT = 1;
    public int ELDRITCH_GOWN_MIN_SIZE = 3;
    public int ELDRITCH_GOWN_MAX_SIZE = 4;
}