package com.test.renov.analyze;

import com.test.renov.calculator.Calculator;
import com.test.renov.calculator.SurfaceCalculator;
import com.test.renov.model.Room;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RoomAnalyzerTest {

    private Analyzer testAnalyzer;
    private Calculator calculator;

    @BeforeClass
    public void setUp() {
        calculator = new SurfaceCalculator();
        testAnalyzer = new RoomAnalyzer(calculator);
    }


    @Test
    public void shouldReturnWithEmptyListIfNoDuplicatesFound() {
        //given a test dataset of rooms with no duplicates
        Map<Room, Integer> rooms = new HashMap<>();
        rooms.put(new Room(1, 1, 1), 1);
        rooms.put(new Room(2, 2 , 3), 1);
        rooms.put(new Room(3, 2, 1), 1);

        //when calling listAllDuplicates on dataset
        List<Room> duplicatedRooms = testAnalyzer.listAllDuplicatedRooms(rooms);

        //then should return an empty list
        assertTrue(duplicatedRooms.isEmpty());
    }

    @Test
    public void shouldReturnWithAllDuplicatedRooms() {
        //given a test dataset of rooms with 2 duplicates
        Map<Room, Integer> rooms = new HashMap<>();
        rooms.put(new Room(1, 1, 1), 2);
        rooms.put(new Room(2, 2 , 3), 1);
        rooms.put(new Room(3, 2, 1), 3);

        //when calling listAllDuplicates on dataset
        List<Room> duplicatedRooms = testAnalyzer.listAllDuplicatedRooms(rooms);

        //then should return a non-empty list with correct list of duplicated rooms
        assertFalse(duplicatedRooms.isEmpty());
        assertEquals(duplicatedRooms.size(), 2);
    }

    @Test
    public void shouldReturnWithEmptyListIfNoCubicShapedRoomsFound() {
        //given a test dataset of rooms with no duplicates
        Map<Room, Integer> rooms = new HashMap<>();
        rooms.put(new Room(1, 1, 7), 1);
        rooms.put(new Room(2, 2 , 3), 1);
        rooms.put(new Room(3, 2, 1), 1);

        //when calling listAllDuplicates on dataset
        List<Room> cubicShapedRooms = testAnalyzer.listAllCubicShapedRooms(rooms);

        //then should return an empty list
        assertTrue(cubicShapedRooms.isEmpty());
    }

    @Test
    public void shouldReturnCubicShapedRoomsInCorrectDescendingOrder() {
        //given a test dataset of rooms with 4 cubic shaped
        Map<Room, Integer> rooms = new HashMap<>();
        Room cubicRoom1 = new Room(3, 3, 3);
        Room cubicRoom2 = new Room(11, 11, 11);
        Room cubicRoom3 = new Room(1, 1, 1);
        Room cubicRoom4 = new Room(2, 2, 2);
        rooms.put(cubicRoom1, 1);
        rooms.put(cubicRoom2, 1);
        rooms.put(cubicRoom3, 1);
        rooms.put(new Room(2, 2 , 3), 1);
        rooms.put(cubicRoom4, 1);

        //when calling listAllCubicShapedRooms on dataset
        List<Room> cubicShapedRooms = testAnalyzer.listAllCubicShapedRooms(rooms);

        //then should return a non-empty list with rooms in correct descending order
        assertFalse(cubicShapedRooms.isEmpty());
        assertEquals(cubicShapedRooms.size(), 4);

    }
}