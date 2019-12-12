package ru.sbt.mipt.oop.eventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

public class CCLightEventAdapter implements CCEventAdapter{
    @Override
    public SensorEvent adaptee(CCSensorEvent event) {
        if (!(event.getEventType().equals("LightIsOn")) &&
                !(event.getEventType().equals("LightIsOff"))) {
            return null;
        }

        if (event.getEventType().equals("LightIsOn")) {
            return new SensorEvent(SensorEventType.LIGHT_ON, event.getObjectId());
        } else {
            return new SensorEvent(SensorEventType.LIGHT_OFF, event.getObjectId());
        }
    }
}
