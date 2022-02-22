package com.test.renov.analyze;

import com.test.renov.calculator.Calculator;
import com.test.renov.model.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomAnalyzer implements Analyzer {

    private final Calculator calculator;

    public RoomAnalyzer(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public List<Room> listAllCubicShapedRooms(Map<Room, Integer> rooms) {
        return rooms.keySet().stream()
                        .filter(this::isCubicRoom)
                        .map(integer -> Map.entry(integer, calculator.calculateRoomSurface(integer)))
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Room> listAllDuplicatedRooms(Map<Room, Integer> rooms) {
        return rooms.entrySet().stream()
                                .filter(entry -> (entry.getValue() > 1))
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toList());
    }

    private boolean isCubicRoom(Room room) {
        return (room.getLength() == room.getWidth())
                && (room.getLength() == room.getHeight())
                && (room.getWidth() == room.getHeight());
    }
}
