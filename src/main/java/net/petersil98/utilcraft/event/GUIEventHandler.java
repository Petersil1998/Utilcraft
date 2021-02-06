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
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.config.Config;
import net.petersil98.utilcraft.data.capabilities.last_death.CapabilityLastDeath;
import net.petersil98.utilcraft.data.capabilities.last_death.ILastDeath;
import net.petersil98.utilcraft.data.capabilities.last_death.LastDeathProvider;
import net.petersil98.utilcraft.utils.PlayerUtils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber(modid = Utilcraft.MOD_ID, value = Dist.CLIENT)
public class GUIEventHandler {

    @SubscribeEvent
    public static void addElementsToGUI(RenderGameOverlayEvent event) {
        if(!Minecraft.getInstance().gameSettings.showDebugInfo && event.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            addVeinMinerOverlay(event.getMatrixStack(), 10, event.getWindow().getScaledHeight()-20);
            addDeathsOverlay(event.getMatrixStack(), event.getWindow());
        }
    }

    private static void addVeinMinerOverlay(MatrixStack matrixStack, int x, int y) {
        TextFormatting format = PlayerUtils.isVeinMinerActive() ? TextFormatting.GREEN : TextFormatting.RED;
        AbstractGui.drawString(
                matrixStack,
                Minecraft.getInstance().fontRenderer,
                new TranslationTextComponent(String.format("vein_miner.%s.%s", Utilcraft.MOD_ID, PlayerUtils.isVeinMinerActive() ? "active" : "inactive")).mergeStyle(format),
                x, y, 0xffffff);
    }

    private static void addDeathsOverlay(MatrixStack matrixStack, MainWindow window) {
        Map<String, Integer> playerDeaths = PlayerUtils.getPlayerDeaths();
        int size = Math.min(playerDeaths.size(), Config.DEATHS_OVERLAY_PLAYERS.get());
        if(size > 0) {
            FontRenderer renderer = Minecraft.getInstance().fontRenderer;
            int height = window.getScaledHeight() / 2;
            int lineHeight = renderer.FONT_HEIGHT + 2;
            int offset = size / 2 * lineHeight;
            int i = 0;
            for (Map.Entry<String, Integer> playerDeath : playerDeaths.entrySet()) {
                if (i >= size) return;
                String message = String.format("%s: %d", playerDeath.getKey(), playerDeath.getValue());
                int y = height - offset + i * lineHeight;
                int x = window.getScaledWidth() - renderer.getStringWidth(message) - 10;
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
    public static void test(RenderWorldLastEvent event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        AtomicReference<ILastDeath> atomicReference = new AtomicReference<>();
        player.getCapability(CapabilityLastDeath.LAST_DEATH_CAPABILITY).ifPresent(atomicReference::set);
        if (atomicReference.get() != null && atomicReference.get().getDeathPoint() != null && atomicReference.get().getDeathDimension() != null) {
            ResourceLocation TEXTURE_BEACON_BEAM = new ResourceLocation("textures/entity/beacon_beam.png");
            MatrixStack matrixStack = event.getMatrixStack();
            matrixStack.push();
            //matrixStack.translate();
            renderBeamSegment(event.getMatrixStack(), Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(), TEXTURE_BEACON_BEAM, event.getPartialTicks(), 1.0F, Minecraft.getInstance().world.getGameTime(), 0, 1024, new float[]{255, 255, 255}, 0.2F, 0.25F);
            matrixStack.pop();
        }
    }

    public static void renderBeamSegment(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, ResourceLocation textureLocation, float partialTicks, float textureScale, long totalWorldTime, int yOffset, int height, float[] colors, float beamRadius, float glowRadius) {
        int yMax = yOffset + height;
        matrixStackIn.push();
        matrixStackIn.translate(0.5D, 0.0D, 0.5D);
        float f = (float)Math.floorMod(totalWorldTime, 40L) + partialTicks;
        float f1 = height < 0 ? f : -f;
        float f2 = MathHelper.frac(f1 * 0.2F - (float)MathHelper.floor(f1 * 0.1F));
        float red = colors[0];
        float green = colors[1];
        float blue = colors[2];
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f * 2.25F - 45.0F));
        float f15 = -1.0F + f2;
        float f16 = (float)height * textureScale * (0.5F / beamRadius) + f15;
        renderPart(matrixStackIn, bufferIn.getBuffer(RenderType.getBeaconBeam(textureLocation, false)), red, green, blue, 1.0F, yOffset, yMax, 0.0F, beamRadius, beamRadius, 0.0F, -beamRadius, 0.0F, 0.0F, -beamRadius, 0.0F, 1.0F, f16, f15);
        matrixStackIn.pop();
        renderPart(matrixStackIn, bufferIn.getBuffer(RenderType.getBeaconBeam(textureLocation, true)), red, green, blue, 0.125F, yOffset, yMax, -glowRadius, -glowRadius, glowRadius, -glowRadius, -glowRadius, glowRadius, glowRadius, glowRadius, 0.0F, 1.0F, f16, f15);
        matrixStackIn.pop();
    }

    private static void renderPart(MatrixStack matrixStackIn, IVertexBuilder bufferIn, float red, float green, float blue, float alpha, int yMin, int yMax, float p_228840_8_, float p_228840_9_, float p_228840_10_, float p_228840_11_, float p_228840_12_, float p_228840_13_, float p_228840_14_, float p_228840_15_, float u1, float u2, float v1, float v2) {
        MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();
        Matrix4f matrix4f = matrixstack$entry.getMatrix();
        Matrix3f matrix3f = matrixstack$entry.getNormal();
        addQuad(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_8_, p_228840_9_, p_228840_10_, p_228840_11_, u1, u2, v1, v2);
        addQuad(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_14_, p_228840_15_, p_228840_12_, p_228840_13_, u1, u2, v1, v2);
        addQuad(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_10_, p_228840_11_, p_228840_14_, p_228840_15_, u1, u2, v1, v2);
        addQuad(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_12_, p_228840_13_, p_228840_8_, p_228840_9_, u1, u2, v1, v2);
    }

    private static void addQuad(Matrix4f matrixPos, Matrix3f matrixNormal, IVertexBuilder bufferIn, float red, float green, float blue, float alpha, int yMin, int yMax, float x1, float z1, float x2, float z2, float u1, float u2, float v1, float v2) {
        addVertex(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMax, x1, z1, u2, v1);
        addVertex(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMin, x1, z1, u2, v2);
        addVertex(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMin, x2, z2, u1, v2);
        addVertex(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMax, x2, z2, u1, v1);
    }

    private static void addVertex(Matrix4f matrixPos, Matrix3f matrixNormal, IVertexBuilder bufferIn, float red, float green, float blue, float alpha, int y, float x, float z, float texU, float texV) {
        bufferIn.pos(matrixPos, x, (float)y, z).color(red, green, blue, alpha).tex(texU, texV).overlay(OverlayTexture.NO_OVERLAY).lightmap(15728880).normal(matrixNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }
}
