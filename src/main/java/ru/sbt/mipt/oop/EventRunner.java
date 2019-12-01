package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventHandlers.EventHandler;

import java.util.List;

class EventRunner implements EventRunnable{
    private List<EventHandler> eventHandlers;

    public EventRunner(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    public void runEvents(SensorEvent event){
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handle(event);
        }
    }
}
