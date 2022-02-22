package com.test.renov.calculator;


import com.test.renov.model.Room;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class SurfaceCalculatorTest {

    private Calculator testCalculator;

    @BeforeClass
    public void setUp() {
        testCalculator = new SurfaceCalculator();
    }

    @DataProvider()
    public static Object[][] testRooms() {
        return new Object[][] {
                {new Room(1, 1, 1), 1},
                {new Room(1, 2, 2), 2},
                {new Room(3, 2, 2), 4},
                {new Room(3, 2, 1), 2}
        };
    }

    @Test(dataProvider = "testRooms")
    public void shouldReturnTheCorrectSideSizeofTheRoom(Room room, int expectedSideSize) {
        int smallestSide = testCalculator.calculateSmallestSide(room);
        assertEquals(smallestSide, expectedSideSize);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionOnWrongInput() {
        int smallestSide = testCalculator.calculateSmallestSide(new Room(0, 1, 1));
    }

    @DataProvider()
    public static Object[][] testRoomsForSurfaceCalculations() {
        return new Object[][] {
                {new Room(1, 1, 1), 6},
                {new Room(1, 2, 2), 16},
                {new Room(3, 2, 2), 32},
                {new Room(3, 2, 1), 22}
        };
    }

    @Test(dataProvider = "testRoomsForSurfaceCalculations")
    public void shouldReturnTheCorrectSurfaceSizeofTheRoom(Room room, int expectedSurfaceSize) {
        int surfaceSize = testCalculator.calculateRoomSurface(room);
        assertEquals(surfaceSize, expectedSurfaceSize);
    }

    @Test
    public void shouldReturnTheCorrectSumOfSurfacesForRooms() {
        Map<Room, Integer> rooms = new HashMap<>();
        rooms.put(new Room(1, 1, 1), 2);
        rooms.put(new Room(2, 2 , 3), 1);
        rooms.put(new Room(3, 2, 1), 3);

        int allSurface = testCalculator.calculateAllSurface(rooms);

        assertEquals(allSurface, 122);
    }
}