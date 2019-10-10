package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static SmartHome readHome(String filename) throws IOException {
        Reader reader = new JsonReader();
        return reader.read(filename);
    }

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = readHome("smart-home-1.js");

        // начинаем цикл обработки событий
        EventRunner.runEvents(smartHome);
    }

}
