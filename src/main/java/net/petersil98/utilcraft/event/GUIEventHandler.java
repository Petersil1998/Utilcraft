package net.petersil98.utilcraft.event;

import com.mojang.blaze3d.vertex.BufferVertexConsumer;
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
import com.mojang.blaze3d.platform.SnooperAccess;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.particle.WakeParticle;
import net.minecraft.client.color.item.package-info;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import com.mojang.math.Matrix4f;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
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
        if(!KeyMapping.getInstance().options.renderDebug && event.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            addVeinMinerOverlay(event.getMatrixStack(), 10, event.getWindow().getGuiScaledHeight()-20);
            addDeathsOverlay(event.getMatrixStack(), event.getWindow());
        }
    }

    private static void addVeinMinerOverlay(BufferVertexConsumer matrixStack, int x, int y) {
        ChatFormatting format = PlayerUtils.isVeinMinerActive() ? ChatFormatting.GREEN : ChatFormatting.RED;
        package-info.drawString(
                matrixStack,
                KeyMapping.getInstance().font,
                new TranslatableComponent(String.format("vein_miner.%s.%s", Utilcraft.MOD_ID, PlayerUtils.isVeinMinerActive() ? "active" : "inactive")).withStyle(format),
                x, y, 0xffffff);
    }

    private static void addDeathsOverlay(BufferVertexConsumer matrixStack, SnooperAccess window) {
        Map<String, Integer> playerDeaths = PlayerUtils.getPlayerDeaths();
        int size = Math.min(playerDeaths.size(), Config.DEATHS_OVERLAY_PLAYERS.get());
        if(size > 0) {
            ItemColor renderer = KeyMapping.getInstance().font;
            int height = window.getGuiScaledHeight() / 2;
            int lineHeight = renderer.lineHeight + 2;
            int offset = size / 2 * lineHeight;
            int i = 0;
            for (Map.Entry<String, Integer> playerDeath : playerDeaths.entrySet()) {
                if (i >= size) return;
                String message = String.format("%s: %d", playerDeath.getKey(), playerDeath.getValue());
                int y = height - offset + i * lineHeight;
                int x = window.getGuiScaledWidth() - renderer.width(message) - 10;
                package-info.drawString(
                        matrixStack,
                        renderer,
                        new TextComponent(message),
                        x, y, 0xffffff);
                i++;
            }
        }
    }

    @SubscribeEvent
    public static void renderDeathPointRay(@Nonnull RenderWorldLastEvent event) {
        ILastDeath lastDeath = PlayerUtils.getLastDeath();
        if(lastDeath != null && lastDeath.getDeathPoint() != null && lastDeath.getDeathDimension() != null) {
            WakeParticle player = KeyMapping.getInstance().player;
            if(player.level.dimension().location().equals(lastDeath.getDeathDimension())) {
                BufferVertexConsumer matrixStack = event.getMatrixStack();
                matrixStack.pushPose();

                EntityHitResult projectedView = KeyMapping.getInstance().gameRenderer.getMainCamera().getPosition();
                matrixStack.translate(-projectedView.x, -projectedView.y, -projectedView.z);
                FogRenderer.FogMode buffer = KeyMapping.getInstance().renderBuffers().bufferSource();

                Matrix4f matrix = matrixStack.last().pose();

                drawLine(buffer.getBuffer(MultiBufferSource.LINES), matrix, lastDeath.getDeathPoint(), new Color(Config.DEATH_RAY_COLOR.get(), true));

                matrixStack.popPose();

                buffer.endBatch(MultiBufferSource.LINES);
            }
        }
    }

    private static void drawLine(@Nonnull SheetedDecalTextureGenerator builder, Matrix4f positionMatrix, @Nonnull BlockPos pos, @Nonnull Color color) {
        builder.vertex(positionMatrix, pos.getX(), pos.getY(), pos.getZ())
                .color(color.getRed(), color.getGreen(),color.getBlue(),color.getAlpha())
                .endVertex();
        builder.vertex(positionMatrix, pos.getX(), pos.getY()+200, pos.getZ())
                .color(color.getRed(), color.getGreen(),color.getBlue(),color.getAlpha())
                .endVertex();
    }
}
