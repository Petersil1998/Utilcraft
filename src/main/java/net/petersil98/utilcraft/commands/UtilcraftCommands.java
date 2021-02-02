package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;

public class UtilcraftCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        TrustedPlayersCommand.register(dispatcher);
        HomeCommand.register(dispatcher);
    }
}
