package net.petersil98.utilcraft.blocks.custom;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum SideSlabType implements IStringSerializable {
    EAST("east"),
    NORTH("north"),
    WEST("west"),
    SOUTH("south"),
    DOUBLE("double");

    private final String name;

    SideSlabType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Nonnull
    public String getSerializedName() {
        return this.name;
    }

    @Nullable
    public Direction getFacingDirection() {
        switch (this){
            case EAST: {
                return Direction.EAST;
            }
            case NORTH: {
                return Direction.NORTH;
            }
            case WEST: {
                return Direction.WEST;
            }
            case SOUTH: {
                return Direction.SOUTH;
            }
            default: {
                return null;
            }
        }
    }
}