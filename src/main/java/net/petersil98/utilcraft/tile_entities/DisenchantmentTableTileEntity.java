package net.petersil98.utilcraft.tile_entities;

import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.petersil98.utilcraft.Utilcraft;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DisenchantmentTableTileEntity extends BeehiveBlockEntity implements InteractionResultHolder {

    private Component customName;

    public DisenchantmentTableTileEntity() {
        super(UtilcraftTileEntities.DISENCHANTMENT_BLOCK);
    }

    @Nonnull
    public CompoundTag save(@Nonnull CompoundTag compound) {
        super.save(compound);
        if (this.hasCustomName()) {
            compound.putString("CustomName", Component.Serializer.toJson(this.customName));
        }

        return compound;
    }

    public void load(@Nonnull PistonStructureResolver state, @Nonnull CompoundTag nbt) {
        super.load(state, nbt);
        if (nbt.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
        }

    }

    @Nonnull
    public Component getName() {
        return this.customName != null ? this.customName : new TranslatableComponent(String.format("screen.%s.disenchantment_table", Utilcraft.MOD_ID));
    }

    public void setCustomName(@Nullable Component name) {
        this.customName = name;
    }

    @Nullable
    public Component getCustomName() {
        return this.customName;
    }
}
