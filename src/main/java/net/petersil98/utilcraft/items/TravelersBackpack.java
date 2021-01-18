package net.petersil98.utilcraft.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.container.TravelersBackpackContainer;
import net.petersil98.utilcraft.data.capabilities.inventory.InventoryProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TravelersBackpack extends Item {

    public TravelersBackpack() {
        super(new Item.Properties().group(Utilcraft.ITEM_GROUP).maxStackSize(1));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull PlayerEntity playerIn, @Nonnull Hand handIn) {
        playerIn.getHeldItem(handIn).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iInventory -> {
            if(!playerIn.world.isRemote) {
                INamedContainerProvider containerProvider = new SimpleNamedContainerProvider((id, inventory, player) -> new TravelersBackpackContainer(id, player.inventory, iInventory, player.inventory.currentItem), new StringTextComponent("Bag"));
                NetworkHooks.openGui((ServerPlayerEntity)playerIn, containerProvider, packetBuffer -> {
                    packetBuffer.writeInt(iInventory.getSlots());
                    packetBuffer.writeInt(playerIn.inventory.currentItem);
                });
            }
        });
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new InventoryProvider(36);
    }
}
