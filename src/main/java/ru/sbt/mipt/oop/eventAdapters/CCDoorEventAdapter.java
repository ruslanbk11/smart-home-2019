package ru.sbt.mipt.oop.eventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

public class CCDoorEventAdapter implements CCEventAdapter {
    @Override
    public SensorEvent adaptee(CCSensorEvent event) {
        if (!(event.getEventType().equals("DoorIsOpen")) &&
                !(event.getEventType().equals("DoorIsClosed"))) {
            return null;
        }

        if (event.getEventType().equals("DoorIsOpen")) {
            return new SensorEvent(SensorEventType.DOOR_OPEN, event.getObjectId());
        } else {
            return new SensorEvent(SensorEventType.DOOR_CLOSED, event.getObjectId());
        }
    }
}
