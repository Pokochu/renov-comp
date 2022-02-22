package com.test.renov.calculator;

import com.test.renov.model.Room;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SurfaceCalculator implements Calculator {


    @Override
    public int calculateRoomSurface(Room room) {
        return (2 * room.getLength() * room.getWidth()) +
                (2 * room.getWidth() * room.getHeight()) +
                (2 * room.getHeight() * room.getLength());
    }

    @Override
    public int calculateSmallestSide(Room room) {
        List<Integer> sizes = Arrays.asList(room.getHeight(), room.getLength(), room.getWidth());
        sizes.sort(Integer::compareTo);
        return (sizes.get(0) * sizes.get(1));
    }

    @Override
    public int calculateAllSurface(Map<Room, Integer> rooms) {
        return rooms.entrySet().stream()
                .map(entry -> (entry.getValue() * (calculateRoomSurface(entry.getKey()) + calculateSmallestSide(entry.getKey()))))
                .mapToInt(Integer::intValue)
                .sum();
    }
}
