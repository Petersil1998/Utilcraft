package net.petersil98.utilcraft.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SignItem;
import net.minecraft.network.play.server.SOpenSignMenuPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.blocks.ModBlocks;

import javax.annotation.Nullable;

public class SakuraSignItem extends SignItem {

    public SakuraSignItem() {
        super(new Item.Properties()
                .maxStackSize(16)
                .group(Main.setup.itemGroup),
                ModBlocks.SAKURASIGN,
                ModBlocks.SAKURAWALLSIGN
        );
    }
}
