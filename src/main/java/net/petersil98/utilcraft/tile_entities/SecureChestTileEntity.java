package net.petersil98.utilcraft.tile_entities;

import net.minecraft.block.Block;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.package-info;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.InteractionResult;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
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
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;

@OnlyIn(
        value = Dist.CLIENT,
        _interface = JigsawBlockEntity.class
)
public class SecureChestTileEntity extends BeehiveBlockEntity implements JigsawBlockEntity, StructureBlockEntity, InteractionResultHolder, InteractionResult {
    ItemStackHandler inventory = new ItemStackHandler(27);
    /** The current angle of the lid (between 0 and 1) */
    protected float lidAngle;
    /** The angle of the lid last tick */
    protected float prevLidAngle;
    /** The number of players currently using this chest */
    protected int numPlayersUsing;

    protected UUID owner;

    private Component customName;

    protected SecureChestTileEntity(BellBlockEntity<?> type) {
        super(type);
    }

    public SecureChestTileEntity() {
        this(UtilcraftTileEntities.SECURE_CHEST);
    }

    @Nonnull
    @Override
    public BellBlockEntity<?> getType() {
        return UtilcraftTileEntities.SECURE_CHEST;
    }

    @Override
    public FoodProperties createMenu(int id, @Nonnull package-info playerInventory, @Nonnull Abilities player) {
        setChanged();
        return new SecureChestContainer(id, playerInventory, this.inventory);
    }

    public void load(@Nonnull PistonStructureResolver state, @Nonnull CompoundTag nbt) {
        super.load(state, nbt);
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
        return InteractionResultHolder.super.getDisplayName();
    }

    public void tick() {
        this.prevLidAngle = this.lidAngle;
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            this.playSound(Musics.CHEST_OPEN);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
            float f1 = this.lidAngle;
            if (this.numPlayersUsing > 0) {
                this.lidAngle += 0.1F;
            } else {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            if (this.lidAngle < 0.5F && f1 >= 0.5F) {
                this.playSound(Musics.CHEST_CLOSE);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }

    }

    private void playSound(Music sound) {
        double d0 = (double)this.worldPosition.getX() + 0.5D;
        double d1 = (double)this.worldPosition.getY() + 0.5D;
        double d2 = (double)this.worldPosition.getZ() + 0.5D;
        this.level.playSound(null, d0, d1, d2, sound, SoundEvent.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }

    /**
     * See {@link Block#eventReceived} for more information. This must return true serverside before it is called
     * clientside.
     */
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    public float getOpenNess(float partialTicks) {
        return LimitedCapacityList.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
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
