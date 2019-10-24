package ru.sbt.mipt.oop;


class EventRunner {
    static void runEvents(SmartHome smartHome){
        SensorEvent event = SensorEventGetter.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            LightEventHandler.handle(event, smartHome);
            DoorEventHandler.handle(event, smartHome);
            HallDoorEventHandler.handle(event, smartHome);
            event = SensorEventGetter.getNextSensorEvent();
        }
    }
}
