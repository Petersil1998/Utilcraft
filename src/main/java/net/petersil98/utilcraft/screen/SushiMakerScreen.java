package net.petersil98.utilcraft.screen;

import com.mojang.blaze3d.vertex.BufferVertexConsumer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.controls.package-info;
import net.minecraft.world.entity.package-info;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.container.SushiMakerContainer;

import javax.annotation.Nonnull;

public class SushiMakerScreen extends package-info<SushiMakerContainer> {
    private static final ResourceLocation SUSHI_MAKER_GUI_TEXTURES = new ResourceLocation(Utilcraft.MOD_ID, "textures/gui/sushi_maker_gui.png");
    private boolean widthTooNarrow;

    public SushiMakerScreen(SushiMakerContainer screenContainer, package-info inv, Component title) {
        super(screenContainer, inv, title);
    }

    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.titleLabelX = 11;
        this.inventoryLabelY += 18;
        this.height += 18;
        this.imageHeight += 18;
    }

    public void tick() {
        super.tick();
    }

    public void render(@Nonnull BufferVertexConsumer matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        if (this.widthTooNarrow) {
            this.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        } else {
            super.render(matrixStack, mouseX, mouseY, partialTicks);
        }

        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    protected void renderBg(@Nonnull BufferVertexConsumer matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(this.SUSHI_MAKER_GUI_TEXTURES);
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
        return !this.widthTooNarrow && super.isHovering(x, y, width, height, mouseX, mouseY);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return this.widthTooNarrow || super.mouseClicked(mouseX, mouseY, button);
    }

    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
        return mouseX < (double)guiLeft || mouseY < (double)guiTop || mouseX >= (double)(guiLeft + this.imageWidth) || mouseY >= (double)(guiTop + this.imageHeight);
    }

    /**
     * Called when the mouse is clicked over a slot or outside the gui.
     */
    protected void slotClicked(@Nonnull ShulkerBoxMenu slot, int slotId, int mouseButton, @Nonnull BrewingStandMenu type) {
        super.slotClicked(slot, slotId, mouseButton, type);
    }

    public void removed() {
        super.removed();
    }
}
