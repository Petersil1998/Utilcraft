package net.petersil98.utilcraft.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.petersil98.utilcraft.Utilcraft;

public class Config {

    public static final String CATEGORY_GUI = "GUI";
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue DEATHS_OVERLAY_PLAYERS;
    public static ForgeConfigSpec.IntValue DEATH_RAY_COLOR;

    public static int DEATHS_OVERLAY_PLAYERS_MAX = 20;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        CLIENT_BUILDER.comment("GUI settings").push(CATEGORY_GUI);
        DEATHS_OVERLAY_PLAYERS = CLIENT_BUILDER.comment("First x Players are shown in the Death-Scoreboard (0 to disable feature)").defineInRange("deathsOverlayNumberOfPlayers", 3, 0, DEATHS_OVERLAY_PLAYERS_MAX);
        DEATH_RAY_COLOR = CLIENT_BUILDER.comment("The color, with which the \"last death point\"-ray is rendered").defineInRange("deathRayColor",0xFFFFFF00,Integer.MIN_VALUE,Integer.MAX_VALUE);
        CLIENT_BUILDER.pop();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}