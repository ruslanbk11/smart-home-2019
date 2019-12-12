package ru.sbt.mipt.oop.eventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

import java.util.Collection;

public class CCSensorEventAdapter implements CCEventAdapter {
    private Collection<CCEventAdapter> ccEventAdapters;

    public CCSensorEventAdapter(Collection<CCEventAdapter> ccEventAdapters) {
        this.ccEventAdapters = ccEventAdapters;
    }

    @Override
    public SensorEvent adaptee(CCSensorEvent event){
        for (CCEventAdapter ccEventAdapter : ccEventAdapters) {
            SensorEvent adaptedEvent = ccEventAdapter.adaptee(event);
            if (adaptedEvent != null) {
                return adaptedEvent;
            }
        }

        return null;
    }
}
