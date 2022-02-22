package com.test.renov.reader;

import com.test.renov.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileReader implements Reader {

    private static final Logger logger = LoggerFactory.getLogger(FileReader.class);

    private static final String DELIMITER = "x";

    @Override
    public Map<Room, Integer> read(String path) {
        Map<Room, Integer> rooms = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            List<Room> roomList = reader.lines().map(str -> str.split(DELIMITER))
                    .map(array -> new Room(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2])))
                    .collect(Collectors.toList());

            roomList.forEach(room -> {
                Integer roomCount = rooms.get(room);
                if(roomCount == null) {
                    rooms.put(room, 1);
                    return;
                }
                rooms.put(room, roomCount + 1);
            });
        } catch (IOException e) {
            logger.error("Can't read file from the given path: {}!", e.getMessage(), e);
            return null;
        } catch (NumberFormatException ex) {
            logger.error("Corrupted file format, some of the lines contains wrong limiter: {}!", ex.getMessage(), ex);
            return null;
        }

        return rooms;
    }
}
