package net.petersil98.utilcraft.items;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;
import net.petersil98.utilcraft.render.SecureChestItemBlockEntityRenderer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

public class SecureChestItem extends BlockItem {

    public SecureChestItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return SecureChestItemBlockEntityRenderer.SECURE_CHEST_ITEM_RENDERER;
            }
        });
    }
}
