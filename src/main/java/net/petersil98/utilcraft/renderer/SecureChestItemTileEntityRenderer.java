package net.petersil98.utilcraft.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.*;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.petersil98.utilcraft.blocks.SecureChest;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;

import javax.annotation.Nonnull;

public class SecureChestItemTileEntityRenderer extends ItemStackTileEntityRenderer {
    private final SecureChestTileEntity chest = new SecureChestTileEntity();


    public void func_239207_a_(@Nonnull ItemStack stack, @Nonnull ItemCameraTransforms.TransformType p_239207_2_, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            TileEntity tileentity;
            if (block instanceof SecureChest) {
                tileentity = this.chest;
            } else {
                return;
            }
            TileEntityRendererDispatcher.instance.renderItem(tileentity, matrixStack, buffer, combinedLight, combinedOverlay);
        }
    }
}
