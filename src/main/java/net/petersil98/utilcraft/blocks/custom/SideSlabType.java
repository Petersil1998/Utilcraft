package net.petersil98.utilcraft.blocks.custom;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public enum SideSlabType implements IStringSerializable {
    EAST("east"),
    NORTH("north"),
    WEST("west"),
    SOUTH("south"),
    DOUBLE("double");

    private final String name;

    private SideSlabType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getString() {
        return this.name;
    }

    public static SideSlabType forFacings(Direction clickedSide, Direction entityFacing) {
        if(clickedSide == Direction.UP || clickedSide == Direction.DOWN) {
            if(entityFacing == Direction.NORTH)
                return SideSlabType.NORTH;
            if(entityFacing == Direction.EAST)
                return SideSlabType.EAST;
            if(entityFacing == Direction.SOUTH)
                return SideSlabType.SOUTH;
            if(entityFacing == Direction.WEST)
                return SideSlabType.WEST;
        }
        if(clickedSide == Direction.NORTH) {
            return SideSlabType.SOUTH;
        }
        if(clickedSide == Direction.EAST) {
            return SideSlabType.WEST;
        }
        if(clickedSide == Direction.SOUTH) {
            return SideSlabType.NORTH;
        }
        if(clickedSide == Direction.WEST) {
            return SideSlabType.EAST;
        }
        return SideSlabType.NORTH;
    }
}