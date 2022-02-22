package com.test.renov.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Room {

    private final int length;
    private final int width;
    private final int height;

    public Room(int length, int width, int height) {
        if( length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Room cannot have side with size equal or smaller than zero!");
        }
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return new EqualsBuilder().append(length, room.length).append(width, room.width).append(height, room.height).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(length).append(width).append(height).toHashCode();
    }

    @Override
    public String toString() {
        return length + "x" + width + "x" + height;
    }
}
