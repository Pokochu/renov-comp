package com.test.renov.reader;

import com.test.renov.model.Room;

import java.util.Map;

public interface Reader {

    Map<Room, Integer> read(String path);
}
