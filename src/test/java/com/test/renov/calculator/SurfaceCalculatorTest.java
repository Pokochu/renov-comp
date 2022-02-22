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
        //given

        //when call calculateSmallestSide
        int smallestSide = testCalculator.calculateSmallestSide(room);

        //then it should return the correct size
        assertEquals(smallestSide, expectedSideSize);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionOnWrongInput() {
        //given

        //when calculateSmallestSidewith invalid Room
        int smallestSide = testCalculator.calculateSmallestSide(new Room(0, 1, 1));

        //then should throw Exception
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
        //given

        //when calculateRoomSurface
        int surfaceSize = testCalculator.calculateRoomSurface(room);

        //then should return correct surface size of the room
        assertEquals(surfaceSize, expectedSurfaceSize);
    }

    @Test
    public void shouldReturnTheCorrectSumOfSurfacesForRooms() {
        //given test dataset of rooms
        Map<Room, Integer> rooms = new HashMap<>();
        rooms.put(new Room(1, 1, 1), 2);
        rooms.put(new Room(2, 2 , 3), 1);
        rooms.put(new Room(3, 2, 1), 3);

        //when call calculateAllSurface
        int allSurface = testCalculator.calculateAllSurface(rooms);

        //then should return the sum of all room surface sizes and room's smallest side sizes
        assertEquals(allSurface, 122);
    }
}