package net.petersil98.utilcraft.tile_entities;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DisenchantmentTableTileEntity extends TileEntity implements INameable {

    private ITextComponent customname;

    public DisenchantmentTableTileEntity() {
        super(ModTileEntities.DISENCHANTMENT_BLOCK);
    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.hasCustomName()) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customname));
        }

        return compound;
    }

    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        if (nbt.contains("CustomName", 8)) {
            this.customname = ITextComponent.Serializer.getComponentFromJson(nbt.getString("CustomName"));
        }

    }

    @Nonnull
    public ITextComponent getName() {
        return this.customname != null ? this.customname : new TranslationTextComponent("screen.utilcraft.disenchantment_table");
    }

    public void setCustomName(@Nullable ITextComponent name) {
        this.customname = name;
    }

    @Nullable
    public ITextComponent getCustomName() {
        return this.customname;
    }
}
