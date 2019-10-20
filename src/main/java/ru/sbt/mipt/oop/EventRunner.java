package ru.sbt.mipt.oop;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

class EventRunner {
    static void runEvents(SmartHome smartHome){
        SensorEvent event = SensorEvent.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            SensorEventType type = event.getType();
            if (type == LIGHT_OFF || type == LIGHT_ON){
                LightEventHandler.handle(event, smartHome);
            } else if (type == DOOR_OPEN || type == DOOR_CLOSED){
                DoorEventHandler.handle(event, smartHome);
                HallDoorEventHandler.handle(event, smartHome);
            }
            event = SensorEvent.getNextSensorEvent();
        }
    }
}