package net.petersil98.utilcraft.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.container.EntropyTableContainer;

import javax.annotation.Nonnull;

public class EntropyTableScreen extends ContainerScreen<EntropyTableContainer> {
    private static final ResourceLocation SUSHI_MAKER_GUI_TEXTURES = new ResourceLocation(Utilcraft.MOD_ID, "textures/gui/sushi_maker_gui.png");
    private boolean widthTooNarrow;

    public EntropyTableScreen(EntropyTableContainer screenContainer, PlayerInventory inv, ITextComponent title) {
        super(screenContainer, inv, title);
    }

    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.titleX = 11;
        this.playerInventoryTitleY += 18;
        this.height += 18;
        this.ySize += 18;
    }

    public void tick() {
        super.tick();
    }

    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        if (this.widthTooNarrow) {
            this.drawGuiContainerBackgroundLayer(matrixStack, partialTicks, mouseX, mouseY);
        } else {
            super.render(matrixStack, mouseX, mouseY, partialTicks);
        }

        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(SUSHI_MAKER_GUI_TEXTURES);
        int i = this.guiLeft;
        int j = (this.height - this.ySize) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }

    protected boolean isPointInRegion(int x, int y, int width, int height, double mouseX, double mouseY) {
        return !this.widthTooNarrow && super.isPointInRegion(x, y, width, height, mouseX, mouseY);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return this.widthTooNarrow || super.mouseClicked(mouseX, mouseY, button);
    }

    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
        return mouseX < (double)guiLeft || mouseY < (double)guiTop || mouseX >= (double)(guiLeft + this.xSize) || mouseY >= (double)(guiTop + this.ySize);
    }

    /**
     * Called when the mouse is clicked over a slot or outside the gui.
     */
    protected void handleMouseClick(@Nonnull Slot slot, int slotId, int mouseButton, @Nonnull ClickType type) {
        super.handleMouseClick(slot, slotId, mouseButton, type);
    }

    public void onClose() {
        super.onClose();
    }
}
