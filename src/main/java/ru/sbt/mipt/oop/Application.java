package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventHandlers.*;
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
        eventHandlers.add(new AlarmEventHandler(smartHome));


        EventRunnable eventRunner = new AlarmDecorator(smartHome.alarm, new EventRunner(eventHandlers));
        HomeRunner homeRunner = new HomeRunner(eventRunner);
        homeRunner.run();
    }

}
