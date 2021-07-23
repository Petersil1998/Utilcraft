package net.petersil98.utilcraft.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
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

    private static void addVeinMinerOverlay(MatrixStack matrixStack, int x, int y) {
        TextFormatting format = PlayerUtils.isVeinMinerActive() ? TextFormatting.GREEN : TextFormatting.RED;
        AbstractGui.drawString(
                matrixStack,
                Minecraft.getInstance().font,
                new TranslationTextComponent(String.format("vein_miner.%s.%s", Utilcraft.MOD_ID, PlayerUtils.isVeinMinerActive() ? "active" : "inactive")).withStyle(format),
                x, y, 0xffffff);
    }

    private static void addDeathsOverlay(MatrixStack matrixStack, MainWindow window) {
        Map<String, Integer> playerDeaths = PlayerUtils.getPlayerDeaths();
        int size = Math.min(playerDeaths.size(), Config.DEATHS_OVERLAY_PLAYERS.get());
        if(size > 0) {
            FontRenderer renderer = Minecraft.getInstance().font;
            int height = window.getGuiScaledHeight() / 2;
            int lineHeight = renderer.lineHeight + 2;
            int offset = size / 2 * lineHeight;
            int i = 0;
            for (Map.Entry<String, Integer> playerDeath : playerDeaths.entrySet()) {
                if (i >= size) return;
                String message = String.format("%s: %d", playerDeath.getKey(), playerDeath.getValue());
                int y = height - offset + i * lineHeight;
                int x = window.getGuiScaledWidth() - renderer.width(message) - 10;
                AbstractGui.drawString(
                        matrixStack,
                        renderer,
                        new StringTextComponent(message),
                        x, y, 0xffffff);
                i++;
            }
        }
    }

    @SubscribeEvent
    public static void renderDeathPointRay(@Nonnull RenderWorldLastEvent event) {
        ILastDeath lastDeath = PlayerUtils.getLastDeath();
        if(lastDeath != null && lastDeath.getDeathPoint() != null && lastDeath.getDeathDimension() != null) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            if(player.level.dimension().location().equals(lastDeath.getDeathDimension())) {
                MatrixStack matrixStack = event.getMatrixStack();
                matrixStack.pushPose();

                Vector3d projectedView = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
                matrixStack.translate(-projectedView.x, -projectedView.y, -projectedView.z);
                IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().renderBuffers().bufferSource();

                Matrix4f matrix = matrixStack.last().pose();

                drawLine(buffer.getBuffer(RenderType.LINES), matrix, lastDeath.getDeathPoint(), new Color(Config.DEATH_RAY_COLOR.get(), true));

                matrixStack.popPose();

                buffer.endBatch(RenderType.LINES);
            }
        }
    }

    private static void drawLine(@Nonnull IVertexBuilder builder, Matrix4f positionMatrix, @Nonnull BlockPos pos, @Nonnull Color color) {
        builder.vertex(positionMatrix, pos.getX(), pos.getY(), pos.getZ())
                .color(color.getRed(), color.getGreen(),color.getBlue(),color.getAlpha())
                .endVertex();
        builder.vertex(positionMatrix, pos.getX(), pos.getY()+200, pos.getZ())
                .color(color.getRed(), color.getGreen(),color.getBlue(),color.getAlpha())
                .endVertex();
    }
}
