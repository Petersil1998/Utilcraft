package net.petersil98.utilcraft.gamerules;

import net.minecraft.world.GameRules;

public class UtilcraftGameRules {

    public static GameRules.RuleKey<GameRules.BooleanValue> DO_ALL_PLAYERS_NEED_SLEEP;

    public static void register() {
        DO_ALL_PLAYERS_NEED_SLEEP = GameRules.register("doAllPlayersNeedSleep", GameRules.Category.UPDATES, GameRules.BooleanValue.create(false));
    }
}
