package net.petersil98.utilcraft.tile_entities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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

@OnlyIn(
        value = Dist.CLIENT,
        _interface = IChestLid.class
)
public class SecureChestTileEntity extends TileEntity implements IChestLid, ITickableTileEntity, INameable, INamedContainerProvider {
    ItemStackHandler inventory = new ItemStackHandler(27);
    /** The current angle of the lid (between 0 and 1) */
    protected float lidAngle;
    /** The angle of the lid last tick */
    protected float prevLidAngle;
    /** The number of players currently using this chest */
    protected int numPlayersUsing;

    protected UUID owner;

    private ITextComponent customName;

    protected SecureChestTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public SecureChestTileEntity() {
        this(UtilcraftTileEntities.SECURE_CHEST);
    }

    @Nonnull
    @Override
    public TileEntityType<?> getType() {
        return UtilcraftTileEntities.SECURE_CHEST;
    }

    @Override
    public Container createMenu(int id, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity player) {
        markDirty();
        return new SecureChestContainer(id, playerInventory, inventory);
    }

    public void read(@Nonnull BlockState state, @Nonnull CompoundNBT nbt) {
        super.read(state, nbt);
        if(nbt.contains("items")) {
            inventory.deserializeNBT((CompoundNBT) nbt.get("items"));
        }
        if(nbt.hasUniqueId("owner")) {
            owner = nbt.getUniqueId("owner");
        }
        if (nbt.contains("CustomName", 8)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(nbt.getString("CustomName"));
        }
    }

    @Nonnull
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        super.write(compound);
        compound.put("items", inventory.serializeNBT());
        if(owner != null) {
            compound.putUniqueId("owner", owner);
        }
        if (this.hasCustomName()) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }
        return compound;
    }

    @Nonnull
    public ITextComponent getName() {
        return this.customName != null ? this.customName : new TranslationTextComponent(String.format("screen.%s.secure_chest", Utilcraft.MOD_ID));
    }

    @Nullable
    @Override
    public ITextComponent getCustomName()
    {
        return this.customName;
    }

    public void setCustomName(@Nullable ITextComponent customName)
    {
        this.customName = customName;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName()
    {
        return INameable.super.getDisplayName();
    }

    public void tick() {
        this.prevLidAngle = this.lidAngle;
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            this.playSound(SoundEvents.BLOCK_CHEST_OPEN);
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
                this.playSound(SoundEvents.BLOCK_CHEST_CLOSE);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }

    }

    private void playSound(SoundEvent soundIn) {
        double d0 = (double)this.pos.getX() + 0.5D;
        double d1 = (double)this.pos.getY() + 0.5D;
        double d2 = (double)this.pos.getZ() + 0.5D;
        this.world.playSound(null, d0, d1, d2, soundIn, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    }

    /**
     * See {@link Block#eventReceived} for more information. This must return true serverside before it is called
     * clientside.
     */
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }

    public float getLidAngle(float partialTicks) {
        return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
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
        return owner;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public int getInventorySize() {
        return inventory.getSlots();
    }
}
