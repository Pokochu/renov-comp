package com.test.renov;

import com.test.renov.analyze.Analyzer;
import com.test.renov.analyze.RoomAnalyzer;
import com.test.renov.calculator.Calculator;
import com.test.renov.calculator.SurfaceCalculator;
import com.test.renov.model.Room;
import com.test.renov.reader.FileReader;
import com.test.renov.reader.Reader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class E2ETest {

    private Reader reader;
    private Calculator calculator;
    private Analyzer analyzer;

    @BeforeClass
    public void setUp() {
        reader = new FileReader();
        calculator = new SurfaceCalculator();
        analyzer = new RoomAnalyzer(calculator);
    }

    @Test
    public void givenAValidInputFileWhenProcessedThenReturnsCorrectWallpaperNeededToOrderAndCubicShapedAndDuplicatedRooms() {
        //given valid test input file and non-identical and cubic shaped room
        String testInput = "src/test/resources/10identicalRooms.txt";
        Room nonIdenticalRoom = new Room(9, 23, 15);
        Room cubicShapedRoom = new Room(10, 10, 10);

        Map<Room, Integer> rooms = reader.read(testInput);

        assertFalse(rooms.isEmpty());
        assertEquals(rooms.size(), 10);
        assertEquals(rooms.get(nonIdenticalRoom), 3);

        int allSurfaceToOrder = calculator.calculateAllSurface(rooms);

        assertEquals(allSurfaceToOrder, 13894);

        List<Room> cubicShapedRooms = analyzer.listAllCubicShapedRooms(rooms);

        assertFalse(cubicShapedRooms.isEmpty());
        assertEquals(cubicShapedRooms.size(), 1);
        assertEquals(cubicShapedRooms.get(0), cubicShapedRoom);

        List<Room> duplicatedRooms = analyzer.listAllDuplicatedRooms(rooms);

        assertFalse(duplicatedRooms.isEmpty());
        assertEquals(duplicatedRooms.size(), 1);
        assertEquals(duplicatedRooms.get(0), nonIdenticalRoom);
    }
}
