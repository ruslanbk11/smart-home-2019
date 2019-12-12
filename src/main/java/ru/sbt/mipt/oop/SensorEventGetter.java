package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

class SensorEventGetter {
    static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (6 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        SensorEvent sensorEvent = new SensorEvent(sensorEventType, objectId);
        List<String> codes = Arrays.asList("parol_ot_signalizacii", "nepravillno");
        if (sensorEventType == SensorEventType.values()[4] || sensorEventType == SensorEventType.values()[5]) {
            sensorEvent.setCode(codes.get((int) (2 * Math.random())));
        }
        return sensorEvent;
    }
}
