package com.test.renov;

import com.test.renov.analyze.Analyzer;
import com.test.renov.analyze.RoomAnalyzer;
import com.test.renov.calculator.Calculator;
import com.test.renov.calculator.SurfaceCalculator;
import com.test.renov.model.Room;
import com.test.renov.reader.FileReader;
import com.test.renov.reader.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class RenovationCompany {

    public static void main(String[] args) {

        Reader reader = new FileReader();
        String path = args.length > 0 ? args[0] : "src/main/resources/input1.txt";

        System.out.println("Reading files from input: " + path);
        Map<Room, Integer> rooms = reader.read(path);
        System.out.println("Successfully read all rooms from input file!");

        Calculator calculator = new SurfaceCalculator();
        int calculatedAllSurface = calculator.calculateAllSurface(rooms);
        System.out.println("Company should order " + calculatedAllSurface + " square feet of wallpaper for the rooms.");

        Analyzer analyzer = new RoomAnalyzer(calculator);
        List<Room> cubicShapedRooms = analyzer.listAllCubicShapedRooms(rooms);
        System.out.println("Cubic shaped rooms in descending order:");
        cubicShapedRooms.forEach(room -> {
            System.out.println(room.toString());
        });

        List<Room> duplicatedRooms = analyzer.listAllDuplicatedRooms(rooms);
        System.out.println("Duplicated rooms:");
        duplicatedRooms.forEach(room -> {
            System.out.println(room.toString());
        });

        System.out.println("End of processing input data!");
    }
}
