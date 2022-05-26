package net.petersil98.utilcraft.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.Nameable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.state.BlockState;
import net.petersil98.utilcraft.Utilcraft;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DisenchantmentTableBlockEntity extends BlockEntity implements Nameable {

    private Component customName;

    public DisenchantmentTableBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(UtilcraftBlockEntities.DISENCHANTMENT_BLOCK.get(), blockPos, blockState);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound);
        if (this.hasCustomName()) {
            compound.putString("CustomName", Component.Serializer.toJson(this.customName));
        }
    }

    @Override
    public void load(@Nonnull CompoundTag nbt) {
        super.load(nbt);
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
