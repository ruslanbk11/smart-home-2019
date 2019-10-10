package ru.sbt.mipt.oop;

import java.io.IOException;

public interface Reader {
    SmartHome read(String fileName) throws IOException;
}
