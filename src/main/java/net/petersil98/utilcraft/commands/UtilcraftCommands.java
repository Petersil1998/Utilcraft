package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;

public class UtilcraftCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        TrustedPlayersCommand.register(dispatcher);
        HomeCommand.register(dispatcher);
    }
}
