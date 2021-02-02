package net.petersil98.utilcraft.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.petersil98.utilcraft.Utilcraft;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID)
public class Config {

    public static final String CATEGORY_GUI = "GUI";
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue DEATHS_OVERLAY_PLAYERS;
    public static int DEATHS_OVERLAY_PLAYERS_MAX = 20;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        CLIENT_BUILDER.comment("GUI settings").push(CATEGORY_GUI);
        DEATHS_OVERLAY_PLAYERS = CLIENT_BUILDER.comment("First x Players are shown in the Death-Scoreboard (0 to disable feature)").defineInRange("deathsOverlayNumberOfPlayers", 3, 0, DEATHS_OVERLAY_PLAYERS_MAX);
        CLIENT_BUILDER.pop();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }
}