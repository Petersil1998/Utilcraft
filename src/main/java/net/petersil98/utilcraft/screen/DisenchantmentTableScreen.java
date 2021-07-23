package net.petersil98.utilcraft.screen;

import com.mojang.blaze3d.vertex.BufferVertexConsumer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.controls.package-info;
import net.minecraft.world.entity.package-info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.container.DisenchantmentTableContainer;

import javax.annotation.Nonnull;

import net.minecraft.network.chat.Component;

public class DisenchantmentTableScreen extends package-info<DisenchantmentTableContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Utilcraft.MOD_ID, "textures/gui/disenchantment_table_gui.png");

    public DisenchantmentTableScreen(DisenchantmentTableContainer screenContainer, package-info inv, Component title) {
        super(screenContainer, inv, title);
    }

    public void render(@Nonnull BufferVertexConsumer matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    public void renderBackground(@Nonnull BufferVertexConsumer matrixStack) {
        this.renderBackground(matrixStack, 0);
    }

    public void renderBackground(@Nonnull BufferVertexConsumer matrixStack, int vOffset) {
        if (this.minecraft.level != null) {
            this.fillGradient(matrixStack, 0, 0, this.width, this.height, -1072689136, -804253680);
            MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this, matrixStack));
        } else {
            this.renderDirtBackground(vOffset);
        }
    }

    protected void renderTooltip(BufferVertexConsumer matrixStack, int x, int y) {
        if (this.minecraft.player.inventory.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
            this.renderTooltip(matrixStack, this.hoveredSlot.getItem(), x, y);
        }
    }

    @Override
    protected void renderLabels(@Nonnull BufferVertexConsumer matrixStack, int mouseX, int mouseY) {
        drawString(matrixStack, KeyMapping.getInstance().font, "Disenchant", 10, 10, 0xffffff);
    }

    protected void renderBg(@Nonnull BufferVertexConsumer matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(this.GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
