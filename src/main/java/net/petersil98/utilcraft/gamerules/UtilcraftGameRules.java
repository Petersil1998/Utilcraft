package net.petersil98.utilcraft.gamerules;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

import java.util.function.BiConsumer;

public class UtilcraftGameRules {

    public static final GameRules.RuleKey<GameRules.BooleanValue> DO_ALL_PLAYERS_NEED_SLEEP = GameRules.register("doAllPlayersNeedSleep", GameRules.Category.UPDATES, create(true, (server, value) -> {}));

    private static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue, BiConsumer<MinecraftServer, GameRules.BooleanValue> changeListener) {
        return new GameRules.RuleType<>(BoolArgumentType::bool, (type) -> new GameRules.BooleanValue(type, defaultValue), changeListener, GameRules.IRuleEntryVisitor::changeBoolean);
    }
}
