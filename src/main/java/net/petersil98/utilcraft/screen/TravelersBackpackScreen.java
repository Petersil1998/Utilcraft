package net.petersil98.utilcraft.screen;

import com.mojang.blaze3d.vertex.BufferVertexConsumer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.controls.package-info;
import net.minecraft.world.entity.package-info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.petersil98.utilcraft.container.TravelersBackpackContainer;

import javax.annotation.Nonnull;

public class TravelersBackpackScreen extends package-info<TravelersBackpackContainer> {
    /**
     * The ResourceLocation containing the chest GUI texture.
     */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    /**
     * Window height is calculated with these values; the more rows, the higher
     */
    private final int inventoryRows;

    public TravelersBackpackScreen(TravelersBackpackContainer container, package-info playerInventory, Component title) {
        super(container, playerInventory, title);
        this.passEvents = false;
        this.inventoryRows = container.getNumRows();
        this.imageHeight = 114 + this.inventoryRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(@Nonnull BufferVertexConsumer matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    protected void renderBg(@Nonnull BufferVertexConsumer matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(this.CHEST_GUI_TEXTURE);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.inventoryRows * 18 + 17);
        this.blit(matrixStack, i, j + this.inventoryRows * 18 + 17, 0, 126, this.imageWidth, 96);
    }
}