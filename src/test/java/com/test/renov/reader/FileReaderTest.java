package com.test.renov.reader;

import com.test.renov.model.Room;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class FileReaderTest {

    private static final String THREE_ROOM_FILE = "src/test/resources/3rooms.txt";
    private static final String TEN_ROOM_FILE = "src/test/resources/10rooms.txt";
    private static final String TWELVE_NO_IDENTICAL_ROOMS = "src/test/resources/10identicalRooms.txt";
    private static final String EMPTY_ROOM_FILE = "src/test/resources/emptyFile.txt";
    private static final String INCORRECT_ROOM_FILE = "src/test/resources/noX.txt";
    private static final String WRONG_PATH = "thisFileDoesntExist.txt";

    private Reader testReader;

    @BeforeClass
    public void setUp() {
        testReader = new FileReader();
    }

    @DataProvider()
    public static Object[][] testFiles() {
        return new Object[][] {
                {THREE_ROOM_FILE, 3},
                {TEN_ROOM_FILE, 10},
                {EMPTY_ROOM_FILE, 0}
        };
    }

    @Test(dataProvider = "testFiles")
    public void shouldReturnWithCorrectNumberOfUnIdenticalRooms(String path, int expectedNumberOfRooms) {
        //given

        //when call read
        Map<Room, Integer> rooms = testReader.read(path);

        //then should return correct number of identical rooms
        assertEquals(rooms.size(), expectedNumberOfRooms);
    }

    @Test
    public void shouldReturnWithCorrectNumberOfRooms() {
        //given a nonIdentical room
        Room nonIdenticalRoom = new Room(9, 23, 15);

        //when call read
        Map<Room, Integer> rooms = testReader.read(TWELVE_NO_IDENTICAL_ROOMS);

        //then should return correct number of identical and nonIdentical rooms
        assertEquals(rooms.size(), 10);
        assertEquals(rooms.get(nonIdenticalRoom), 3);
    }

    @Test
    public void shouldReturnWithNullWhenWrongPathIsGiven() {
        //given

        //when call read with wrong path
        Map<Room, Integer> rooms = testReader.read(WRONG_PATH);

        //then return null as response
        assertNull(rooms);
    }

    @Test
    public void shouldReturnWithNullWhenFileContainsWrongLine() {
        //given

        //when call read with a corrupted file
        Map<Room, Integer> rooms = testReader.read(INCORRECT_ROOM_FILE);

        //then return null as response
        assertNull(rooms);
    }
}