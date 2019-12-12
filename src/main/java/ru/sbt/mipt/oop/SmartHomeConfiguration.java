package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.eventAdapters.CCDoorEventAdapter;
import ru.sbt.mipt.oop.eventAdapters.CCEventAdapter;
import ru.sbt.mipt.oop.eventAdapters.CCLightEventAdapter;
import ru.sbt.mipt.oop.eventAdapters.CCSensorEventAdapter;
import ru.sbt.mipt.oop.eventHandlers.*;
import ru.sbt.mipt.oop.readers.JsonReader;
import ru.sbt.mipt.oop.readers.Reader;
import ru.sbt.mipt.oop.remoteControl.RemoteControlConfiguration;

import java.util.Collection;

@Configuration
@Import(RemoteControlConfiguration.class)
public class SmartHomeConfiguration {

    @Bean
    Reader reader() {
        return new JsonReader("smart-home-1.js");
    }

    @Bean
    SmartHome smartHome() {
        return reader().read();
    }

    @Bean
    SensorEventsManager sensorEventsManager(Collection<EventHandler> handlers,
                                            Collection<CCEventAdapter> adapters) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new AlarmDecorator(
                smartHome().getAlarm(), new EventRunner(handlers, eventAdapter(adapters))));
        return sensorEventsManager;
    }


    @Bean
    CCEventAdapter eventAdapter(Collection<CCEventAdapter> adapters) {
        return new CCSensorEventAdapter(adapters);
    }

    @Bean
    CCEventAdapter doorEventAdapter() {
        return new CCDoorEventAdapter();
    }

    @Bean
    CCEventAdapter lightEventAdapter() {
        return new CCLightEventAdapter();
    }

    @Bean
    EventHandler alarmHandler() {
        return new AlarmEventHandler(smartHome());
    }

    @Bean
    EventHandler lightHandler() {
        return new LightEventHandler(smartHome());
    }

    @Bean
    EventHandler doorHandler() {
        return new DoorEventHandler(smartHome());
    }

    @Bean
    EventHandler hallDoorHandler() {
        return new HallDoorEventHandler(smartHome());
    }
}
