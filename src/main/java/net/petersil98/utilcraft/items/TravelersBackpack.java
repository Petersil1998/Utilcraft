package net.petersil98.utilcraft.items;

import net.minecraft.world.entity.player.Abilities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Nameable;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Containers;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.GameType;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.container.TravelersBackpackContainer;
import net.petersil98.utilcraft.data.capabilities.inventory.InventoryProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TravelersBackpack extends HoeItem {

    public TravelersBackpack() {
        super(new HoeItem.Properties().tab(Utilcraft.ITEM_GROUP).stacksTo(1));
    }

    @Nonnull
    @Override
    public DifficultyInstance<ItemCooldowns> use(@Nonnull GameType world, @Nonnull Abilities player, @Nonnull Containers hand) {
        player.getItemInHand(hand).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iInventory -> {
            if(!player.level.isClientSide) {
                InteractionResult containerProvider = new Nameable((id, inventory, playerEntity) -> new TravelersBackpackContainer(id, playerEntity.inventory, iInventory, playerEntity.inventory.selected), new TextComponent("Bag"));
                NetworkHooks.openGui((ServerPlayer)player, containerProvider, packetBuffer -> {
                    packetBuffer.writeInt(iInventory.getSlots());
                    packetBuffer.writeInt(player.inventory.selected);
                });
            }
        });
        return super.use(world, player, hand);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemCooldowns stack, @Nullable CompoundTag nbt) {
        return new InventoryProvider(36);
    }
}
