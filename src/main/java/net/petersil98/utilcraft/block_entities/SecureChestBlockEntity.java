package net.petersil98.utilcraft.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.container.SecureChestContainer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

import net.minecraft.core.Direction;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;

@OnlyIn(
        value = Dist.CLIENT,
        _interface = LidBlockEntity.class
)
public class SecureChestBlockEntity extends BlockEntity implements LidBlockEntity, Nameable, MenuProvider {
    ItemStackHandler inventory = new ItemStackHandler(27);
    /** The current angle of the lid (between 0 and 1) */
    protected float lidAngle;
    /** The angle of the lid last tick */
    protected float prevLidAngle;
    /** The number of players currently using this chest */
    protected int numPlayersUsing;

    protected UUID owner;

    private Component customName;

    protected SecureChestBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState) {
        super(type, blockPos, blockState);
    }

    public SecureChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(UtilcraftBlockEntities.SECURE_CHEST, blockPos, blockState);
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return UtilcraftBlockEntities.SECURE_CHEST;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, @Nonnull Inventory playerInventory, @Nonnull Player player) {
        setChanged();
        return new SecureChestContainer(id, playerInventory, this.inventory);
    }

    @Override
    public void load(@Nonnull CompoundTag nbt) {
        super.load(nbt);
        if(nbt.contains("items")) {
            this.inventory.deserializeNBT((CompoundTag) nbt.get("items"));
        }
        if(nbt.hasUUID("owner")) {
            this.owner = nbt.getUUID("owner");
        }
        if (nbt.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
        }
    }

    @Nonnull
    public CompoundTag save(@Nonnull CompoundTag compound) {
        super.save(compound);
        compound.put("items", this.inventory.serializeNBT());
        if(this.owner != null) {
            compound.putUUID("owner", this.owner);
        }
        if (this.hasCustomName()) {
            compound.putString("CustomName", Component.Serializer.toJson(this.customName));
        }
        return compound;
    }

    @Nonnull
    public Component getName() {
        return this.customName != null ? this.customName : new TranslatableComponent(String.format("screen.%s.secure_chest", Utilcraft.MOD_ID));
    }

    @Nullable
    @Override
    public Component getCustomName()
    {
        return this.customName;
    }

    public void setCustomName(@Nullable Component customName)
    {
        this.customName = customName;
    }

    @Nonnull
    @Override
    public Component getDisplayName()
    {
        return Nameable.super.getDisplayName();
    }

    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    public float getOpenNess(float partialTicks) {
        return Mth.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        return LazyOptional.empty();
    }

    public void setOwner(UUID owner) {
        if(this.owner == null)
            this.owner = owner;
    }

    public UUID getOwner(){
        return this.owner;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public int getInventorySize() {
        return this.inventory.getSlots();
    }
}
