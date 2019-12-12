package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.eventAdapters.CCEventAdapter;
import ru.sbt.mipt.oop.eventHandlers.EventHandler;

import java.util.Collection;

class EventRunner implements com.coolcompany.smarthome.events.EventHandler {
    private Collection<EventHandler> eventHandlers;
    private CCEventAdapter adapter;

    EventRunner(Collection<EventHandler> eventHandlers, CCEventAdapter adapter) {
        this.eventHandlers = eventHandlers;
        this.adapter = adapter;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {

        System.out.println("Got event: " + event.getEventType() + " for object:" + event.getObjectId());
        SensorEvent adaptedEvent = adapter.adaptee(event);

        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(adaptedEvent);
        }
    }
}
