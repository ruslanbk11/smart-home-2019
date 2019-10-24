package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) {
        Reader reader = new JsonReader("smart-home-1.js");

        // считываем состояние дома из файла
        SmartHome smartHome = reader.read();

        // начинаем цикл обработки событий
        EventRunner.runEvents(smartHome);
    }

}
