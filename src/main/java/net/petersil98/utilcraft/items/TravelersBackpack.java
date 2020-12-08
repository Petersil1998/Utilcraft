package net.petersil98.utilcraft.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.petersil98.utilcraft.Main;
import net.petersil98.utilcraft.container.TravelersBackpackContainer;
import net.petersil98.utilcraft.data.capabilities.inventory.CapabilityInventory;

import javax.annotation.Nonnull;

public class TravelersBackpack extends Item {

    public TravelersBackpack() {
        super(new Item.Properties().group(Main.itemGroup));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull PlayerEntity playerIn, @Nonnull Hand handIn) {
        playerIn.getHeldItem(handIn).getCapability(CapabilityInventory.INVENTORY_CAPABILITY).ifPresent(iInventory -> {
            if(!playerIn.world.isRemote) {
                INamedContainerProvider containerProvider = new SimpleNamedContainerProvider((id, inventory, player) -> new TravelersBackpackContainer(id, player.inventory, iInventory.getInventory()), new StringTextComponent("Bag"));
                NetworkHooks.openGui((ServerPlayerEntity)playerIn, containerProvider);
            }
        });
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
