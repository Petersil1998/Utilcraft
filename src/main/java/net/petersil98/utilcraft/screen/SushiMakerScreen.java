package net.petersil98.utilcraft.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.container.SushiMakerContainer;

import javax.annotation.Nonnull;

public class SushiMakerScreen extends AbstractContainerScreen<SushiMakerContainer> {
    private static final ResourceLocation SUSHI_MAKER_GUI_TEXTURES = new ResourceLocation(Utilcraft.MOD_ID, "textures/gui/sushi_maker_gui.png");
    private boolean widthTooNarrow;

    public SushiMakerScreen(SushiMakerContainer screenContainer, Inventory inv, Component title) {
        super(screenContainer, inv, title);
    }

    @Override
    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.titleLabelX = 11;
        this.inventoryLabelY += 18;
        this.height += 18;
        this.imageHeight += 18;
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    public void render(@Nonnull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        if (this.widthTooNarrow) {
            this.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        } else {
            super.render(matrixStack, mouseX, mouseY, partialTicks);
        }

        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@Nonnull PoseStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, SUSHI_MAKER_GUI_TEXTURES);
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
        return !this.widthTooNarrow && super.isHovering(x, y, width, height, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return this.widthTooNarrow || super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
        return mouseX < (double)guiLeft || mouseY < (double)guiTop || mouseX >= (double)(guiLeft + this.imageWidth) || mouseY >= (double)(guiTop + this.imageHeight);
    }
}
