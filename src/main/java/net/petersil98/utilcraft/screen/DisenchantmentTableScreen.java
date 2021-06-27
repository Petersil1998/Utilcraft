package net.petersil98.utilcraft.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.container.DisenchantmentTableContainer;

import javax.annotation.Nonnull;

public class DisenchantmentTableScreen extends ContainerScreen<DisenchantmentTableContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Utilcraft.MOD_ID, "textures/gui/disenchantment_table_gui.png");

    public DisenchantmentTableScreen(DisenchantmentTableContainer screenContainer, PlayerInventory inv, ITextComponent title) {
        super(screenContainer, inv, title);
    }

    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrixStack, mouseX, mouseY);
    }

    public void renderBackground(@Nonnull MatrixStack matrixStack) {
        this.renderBackground(matrixStack, 0);
    }

    public void renderBackground(@Nonnull MatrixStack matrixStack, int vOffset) {
        if (this.minecraft.world != null) {
            this.fillGradient(matrixStack, 0, 0, this.width, this.height, -1072689136, -804253680);
            MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.BackgroundDrawnEvent(this, matrixStack));
        } else {
            this.renderDirtBackground(vOffset);
        }
    }

    protected void func_230459_a_(MatrixStack matrixStack, int x, int y) {
        if (this.minecraft.player.inventory.getItemStack().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.getHasStack()) {
            this.renderTooltip(matrixStack, this.hoveredSlot.getStack(), x, y);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY) {
        drawString(matrixStack, Minecraft.getInstance().fontRenderer, "Disenchant", 10, 10, 0xffffff);
    }

    protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(this.GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
    }
}
