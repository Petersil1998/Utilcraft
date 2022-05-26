package net.petersil98.utilcraft.event;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.config.Config;
import net.petersil98.utilcraft.data.capabilities.last_death.ILastDeath;
import net.petersil98.utilcraft.utils.PlayerUtils;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID, value = Dist.CLIENT)
public class GUIEventHandler {

    @SubscribeEvent
    public static void addElementsToGUI(@Nonnull RenderGameOverlayEvent event) {
        if(!Minecraft.getInstance().options.renderDebug && event.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            addVeinMinerOverlay(event.getMatrixStack(), 10, event.getWindow().getGuiScaledHeight()-20);
            addDeathsOverlay(event.getMatrixStack(), event.getWindow());
        }
    }

    private static void addVeinMinerOverlay(PoseStack matrixStack, int x, int y) {
        ChatFormatting format = PlayerUtils.isVeinMinerActive() ? ChatFormatting.GREEN : ChatFormatting.RED;
        GuiComponent.drawString(
                matrixStack,
                Minecraft.getInstance().font,
                new TranslatableComponent(String.format("vein_miner.%s.%s", Utilcraft.MOD_ID, PlayerUtils.isVeinMinerActive() ? "active" : "inactive")).withStyle(format),
                x, y, 0xffffff);
    }

    private static void addDeathsOverlay(PoseStack matrixStack, Window window) {
        Map<String, Integer> playerDeaths = PlayerUtils.getPlayerDeaths();
        int size = Math.min(playerDeaths.size(), Config.DEATHS_OVERLAY_PLAYERS.get());
        if(size > 0) {
            Font renderer = Minecraft.getInstance().font;
            int height = window.getGuiScaledHeight() / 2;
            int lineHeight = renderer.lineHeight + 2;
            int offset = size / 2 * lineHeight;
            int i = 0;
            for (Map.Entry<String, Integer> playerDeath : playerDeaths.entrySet()) {
                if (i >= size) return;
                String message = String.format("%s: %d", playerDeath.getKey(), playerDeath.getValue());
                int y = height - offset + i * lineHeight;
                int x = window.getGuiScaledWidth() - renderer.width(message) - 10;
                GuiComponent.drawString(
                        matrixStack,
                        renderer,
                        new TextComponent(message),
                        x, y, 0xffffff);
                i++;
            }
        }
    }

    @SubscribeEvent
    public static void renderDeathPointRay(@Nonnull RenderLevelLastEvent event) {
        ILastDeath lastDeath = PlayerUtils.getLastDeath();
        if(lastDeath != null && lastDeath.getDeathPoint() != null && lastDeath.getDeathDimension() != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if(player.level.dimension().location().equals(lastDeath.getDeathDimension())) {
                PoseStack matrixStack = event.getPoseStack();
                matrixStack.pushPose();

                Vec3 projectedView = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
                matrixStack.translate(-projectedView.x, -projectedView.y, -projectedView.z);
                MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();

                Matrix4f matrix = matrixStack.last().pose();
                Matrix3f normal = matrixStack.last().normal();

                drawLine(buffer.getBuffer(RenderType.LINES), matrix, normal, lastDeath.getDeathPoint(), new Color(Config.DEATH_RAY_COLOR.get(), true));

                matrixStack.popPose();

                buffer.endBatch(RenderType.LINES);
            }
        }
    }

    private static void drawLine(@Nonnull VertexConsumer builder, Matrix4f positionMatrix, Matrix3f normal, @Nonnull BlockPos pos, @Nonnull Color color) {
        builder.vertex(positionMatrix, pos.getX(), pos.getY(), pos.getZ())
                .color(color.getRed(), color.getGreen(),color.getBlue(),color.getAlpha())
                .normal(normal,0, 1,0)
                .endVertex();
        builder.vertex(positionMatrix, pos.getX(), pos.getY()+200, pos.getZ())
                .color(color.getRed(), color.getGreen(),color.getBlue(),color.getAlpha())
                .normal(normal, 0,1,0)
                .endVertex();
    }
}
