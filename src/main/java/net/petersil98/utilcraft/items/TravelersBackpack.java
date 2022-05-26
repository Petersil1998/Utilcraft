package net.petersil98.utilcraft.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.petersil98.utilcraft.container.TravelersBackpackContainer;
import net.petersil98.utilcraft.data.capabilities.inventory.InventoryProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TravelersBackpack extends Item {

    public TravelersBackpack(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level world, @Nonnull Player player, @Nonnull InteractionHand hand) {
        player.getItemInHand(hand).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iInventory -> {
            if(!player.level.isClientSide) {
                MenuProvider containerProvider = new SimpleMenuProvider((id, inventory, playerEntity) -> new TravelersBackpackContainer(id, playerEntity.getInventory(), iInventory, playerEntity.getInventory().selected), new TextComponent("Bag"));
                NetworkHooks.openGui((ServerPlayer)player, containerProvider, packetBuffer -> {
                    packetBuffer.writeInt(iInventory.getSlots());
                    packetBuffer.writeInt(player.getInventory().selected);
                });
            }
        });
        return super.use(world, player, hand);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new InventoryProvider(36);
    }
}
