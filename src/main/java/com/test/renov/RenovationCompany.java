package com.test.renov;

import com.test.renov.model.Room;
import com.test.renov.reader.FileReader;

import java.util.Map;

public class RenovationCompany {

    public static void main(String[] args) {
        FileReader reader = new FileReader();

        Map<Room, Integer> rooms = reader.read("src/main/resources/input1.txt");

        System.out.println(rooms.size());
    }
}
