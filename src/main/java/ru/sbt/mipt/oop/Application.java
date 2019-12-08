package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.eventHandlers.*;
import ru.sbt.mipt.oop.readers.JsonReader;
import ru.sbt.mipt.oop.readers.Reader;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) {
       /* Reader reader = new JsonReader("smart-home-1.js");

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
        homeRunner.run();*/

        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }

}
