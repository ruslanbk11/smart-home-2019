package ru.sbt.mipt.oop.eventAdapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

public interface CCEventAdapter {
    SensorEvent adaptee(CCSensorEvent event);
}
