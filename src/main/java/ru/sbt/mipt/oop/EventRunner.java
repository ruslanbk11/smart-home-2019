package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.eventAdapters.CCDoorEventAdapter;
import ru.sbt.mipt.oop.eventAdapters.CCEventAdapter;
import ru.sbt.mipt.oop.eventAdapters.CCLightEventAdapter;
import ru.sbt.mipt.oop.eventAdapters.CCSensorEventAdapter;
import ru.sbt.mipt.oop.eventHandlers.EventHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class EventRunner implements com.coolcompany.smarthome.events.EventHandler {
    private Collection<EventHandler> eventHandlers;

    public EventRunner(Collection<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        Collection<CCEventAdapter> adapters = new ArrayList<>();
        adapters.add(new CCLightEventAdapter());
        adapters.add(new CCDoorEventAdapter());
        CCEventAdapter adapter = new CCSensorEventAdapter(adapters);

        SensorEvent adaptedEvent = adapter.adaptee(event);

        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(adaptedEvent);
        }
    }
}
