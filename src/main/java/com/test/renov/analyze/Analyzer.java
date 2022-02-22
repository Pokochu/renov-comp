package com.test.renov.analyze;

import com.test.renov.model.Room;

import java.util.List;
import java.util.Map;

public interface Analyzer {

    List<Room> listAllCubicShapedRooms(Map<Room, Integer> rooms);

    List<Room> listAllDuplicatedRooms(Map<Room, Integer> rooms);
}
