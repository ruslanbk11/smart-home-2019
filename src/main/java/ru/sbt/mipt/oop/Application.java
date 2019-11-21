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
        eventHandlers.add(new AlarmDecorator(smartHome.alarm, new LightEventHandler(smartHome)));
        eventHandlers.add(new AlarmDecorator(smartHome.alarm, new DoorEventHandler(smartHome)));
        eventHandlers.add(new AlarmDecorator(smartHome.alarm, new HallDoorEventHandler(smartHome)));
        eventHandlers.add(new AlarmDecorator(smartHome.alarm, new AlarmEventHandler(smartHome)));


        EventRunner eventRunner = new EventRunner(eventHandlers);
        eventRunner.runEvents();
    }

}
