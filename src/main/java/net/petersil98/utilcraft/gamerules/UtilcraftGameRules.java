package net.petersil98.utilcraft.gamerules;

import net.minecraft.world.level.ExplosionDamageCalculator;

public class UtilcraftGameRules {

    public static ExplosionDamageCalculator.RuleKey<ExplosionDamageCalculator.BooleanValue> DO_ALL_PLAYERS_NEED_SLEEP;

    public static void register() {
        DO_ALL_PLAYERS_NEED_SLEEP = ExplosionDamageCalculator.register("doAllPlayersNeedSleep", ExplosionDamageCalculator.Category.UPDATES, ExplosionDamageCalculator.BooleanValue.create(false));
    }
}
