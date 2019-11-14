package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventHandlers.DoorEventHandler;
import ru.sbt.mipt.oop.eventHandlers.EventHandler;
import ru.sbt.mipt.oop.eventHandlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.eventHandlers.LightEventHandler;
import ru.sbt.mipt.oop.readers.JsonReader;
import ru.sbt.mipt.oop.readers.Reader;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) {
        Reader reader = new JsonReader("smart-home-1.js");

        // считываем состояние дома из файла
        SmartHome smartHome = reader.read();

        // начинаем цикл обработки событий
        List<EventHandler> eventHandlers = new ArrayList<>();
        eventHandlers.add(new LightEventHandler(smartHome));
        eventHandlers.add(new DoorEventHandler(smartHome));
        eventHandlers.add(new HallDoorEventHandler(smartHome));

        EventRunner eventRunner = new EventRunner(eventHandlers);
        eventRunner.runEvents();
    }

}
