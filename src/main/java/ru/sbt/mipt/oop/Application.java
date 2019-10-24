package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    private static SmartHome readHome(String filename) {
        Reader reader = new JsonReader(filename);
        return reader.read();
    }

    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHome smartHome = readHome("smart-home-1.js");

        // начинаем цикл обработки событий
        EventRunner.runEvents(smartHome);
    }

}
