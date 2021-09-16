package net.petersil98.utilcraft.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.Lazy;
import net.petersil98.utilcraft.blocks.SecureChest;
import net.petersil98.utilcraft.block_entities.SecureChestBlockEntity;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class SecureChestItemBlockEntityRenderer extends BlockEntityWithoutLevelRenderer {

    public static final BlockEntityWithoutLevelRenderer SECURE_CHEST_ITEM_RENDERER = new SecureChestItemBlockEntityRenderer(() -> Minecraft.getInstance().getBlockEntityRenderDispatcher(), () -> Minecraft.getInstance().getEntityModels());

    private final Lazy<SecureChestBlockEntity> chest = Lazy.of(() -> new SecureChestBlockEntity(BlockPos.ZERO, UtilcraftBlocks.SECURE_CHEST.get().defaultBlockState()));
    private final Supplier<BlockEntityRenderDispatcher> renderDispatcher;

    private SecureChestItemBlockEntityRenderer(Supplier<BlockEntityRenderDispatcher> renderDispatcher, Supplier<EntityModelSet> modelSet) {
        super(renderDispatcher.get(), modelSet.get());
        this.renderDispatcher = renderDispatcher;
    }

    public void renderByItem(@Nonnull ItemStack stack, @Nonnull ItemTransforms.TransformType p_239207_2_, @Nonnull PoseStack matrixStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            BlockEntity blockEntity;
            if (block instanceof SecureChest) {
                blockEntity = this.chest.get();
            } else {
                return;
            }
            this.renderDispatcher.get().renderItem(blockEntity, matrixStack, buffer, combinedLight, combinedOverlay);
        }
    }
}
