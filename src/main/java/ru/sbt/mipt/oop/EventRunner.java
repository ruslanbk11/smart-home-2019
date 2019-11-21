package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventHandlers.EventHandler;

import java.util.List;

class EventRunner {

    private List<EventHandler> eventHandlers;

    public EventRunner(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    void runEvents(){

        SensorEvent event = SensorEventGetter.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handle(event);
            }
            event = SensorEventGetter.getNextSensorEvent();
        }
    }
}
