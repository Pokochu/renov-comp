package com.test.renov.calculator;

import com.test.renov.model.Room;
import java.util.Map;

public interface Calculator {

    int calculateRoomSurface(Room room);

    int calculateSmallestSide(Room room);

    int calculateAllSurface(Map<Room, Integer> rooms);
}
