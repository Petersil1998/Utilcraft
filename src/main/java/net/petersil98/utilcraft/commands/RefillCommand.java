package net.petersil98.utilcraft.commands;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class RefillCommand {

    public static ArgumentBuilder<CommandSource, ?> register(){
        return Commands.literal("refill")
                .then(Commands.literal("health").executes((context -> {
                    refillHealth(context.getSource());
                    return 1;
                })))
                .then(Commands.literal("hunger").executes((context -> {
                    refillHunger(context.getSource());
                    return 1;
                }))).executes(context -> {
                    refillHealth(context.getSource());
                    refillHunger(context.getSource());
                    return 1;
                });
    }

    private static void refillHealth(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        player.setHealth(player.getMaxHealth());
        source.sendFeedback(new TranslationTextComponent("command.utilcraft.refill.health"), false);
    }

    private static void refillHunger(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        player.getFoodStats().setFoodLevel(20);
        source.sendFeedback(new TranslationTextComponent("command.utilcraft.refill.hunger"), false);
    }
}
