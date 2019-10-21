package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class DoorEventHandlerTest {

    @Test
    void openOpenedAndClosedDoors() {
        SmartHome smartHome = new SmartHome();
        Door openedDoor = new Door(true, "1");
        Door closedDoor = new Door(false, "2");
        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(openedDoor), "dvushka"));
        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(closedDoor), "treshka"));

        SensorEvent openOpenedDoorEvent = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        SensorEvent openClosedDoorEvent = new SensorEvent(SensorEventType.DOOR_OPEN, "2");
        DoorEventHandler.handle(openOpenedDoorEvent, smartHome);
        DoorEventHandler.handle(openClosedDoorEvent, smartHome);
        Assert.assertTrue(openedDoor.getState());
        Assert.assertTrue(closedDoor.getState());
    }

    @Test
    void closeOpenedAndClosedDoors() {
        SmartHome smartHome = new SmartHome();
        Door openedDoor = new Door(true, "1");
        Door closedDoor = new Door(false, "2");
        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(openedDoor), "dvushka"));
        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(closedDoor), "treshka"));

        SensorEvent closeOpenedDoorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        SensorEvent closeClosedDoorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "2");
        DoorEventHandler.handle(closeOpenedDoorEvent, smartHome);
        DoorEventHandler.handle(closeClosedDoorEvent, smartHome);
        Assert.assertFalse(openedDoor.getState());
        Assert.assertFalse(closedDoor.getState());
    }
}