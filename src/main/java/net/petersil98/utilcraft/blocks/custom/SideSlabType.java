package net.petersil98.utilcraft.blocks.custom;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum SideSlabType implements StringRepresentable {
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
        return switch (this) {
            case EAST -> Direction.EAST;
            case NORTH -> Direction.NORTH;
            case WEST -> Direction.WEST;
            case SOUTH -> Direction.SOUTH;
            default -> null;
        };
    }
}