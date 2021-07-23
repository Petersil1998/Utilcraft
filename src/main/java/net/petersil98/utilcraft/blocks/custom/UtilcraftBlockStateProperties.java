package net.petersil98.utilcraft.blocks.custom;

import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class UtilcraftBlockStateProperties {
    public static final DirectionProperty<SideSlabType> SIDE_SLAB_TYPE = DirectionProperty.create("type", SideSlabType.class);
}
