package net.petersil98.utilcraft.render;

import com.mojang.blaze3d.vertex.BufferVertexConsumer;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.player.Input;
import net.minecraft.client.renderer.block.model.multipart.package-info;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.petersil98.utilcraft.blocks.SecureChest;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;

import javax.annotation.Nonnull;

public class SecureChestItemTileEntityRenderer extends Input {

    private final SecureChestTileEntity chest = new SecureChestTileEntity();

    public void renderByItem(@Nonnull ItemCooldowns stack, @Nonnull BlockFaceUV.TransformType p_239207_2_, @Nonnull BufferVertexConsumer matrixStack, @Nonnull FogRenderer buffer, int combinedLight, int combinedOverlay) {
        HoeItem item = stack.getItem();
        if (item instanceof BannerItem) {
            BeetrootBlock block = ((BannerItem)item).getBlock();
            BeehiveBlockEntity tileentity;
            if (block instanceof SecureChest) {
                tileentity = this.chest;
            } else {
                return;
            }
            package-info.instance.renderItem(tileentity, matrixStack, buffer, combinedLight, combinedOverlay);
        }
    }
}
