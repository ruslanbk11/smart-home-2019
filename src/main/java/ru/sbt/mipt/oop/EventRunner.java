package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventHandlers.EventHandler;

import java.util.List;

class EventRunner {

    private EventHandler eventHandler;

    public EventRunner(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    void runEvents(){

        SensorEvent event = SensorEventGetter.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventHandler.handle(event);
            event = SensorEventGetter.getNextSensorEvent();
        }
    }
}
