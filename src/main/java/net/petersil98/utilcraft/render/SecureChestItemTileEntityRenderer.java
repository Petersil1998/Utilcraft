package net.petersil98.utilcraft.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Lazy;
import net.petersil98.utilcraft.blocks.SecureChest;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;

import javax.annotation.Nonnull;

public class SecureChestItemTileEntityRenderer extends ItemStackTileEntityRenderer {

    private final Lazy<SecureChestTileEntity> chest = Lazy.of(SecureChestTileEntity::new);

    public void renderByItem(@Nonnull ItemStack stack, @Nonnull ItemCameraTransforms.TransformType transformType, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            TileEntity tileentity;
            if (block instanceof SecureChest) {
                tileentity = this.chest.get();
            } else {
                return;
            }
            TileEntityRendererDispatcher.instance.renderItem(tileentity, matrixStack, buffer, combinedLight, combinedOverlay);
        }
    }
}
