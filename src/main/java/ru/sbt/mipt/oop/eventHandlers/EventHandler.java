package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventHandler {
    void handle(SensorEvent event);
}
