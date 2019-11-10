package ru.sbt.mipt.oop.tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.eventHandlers.DoorEventHandler;

import java.util.Arrays;
import java.util.Collections;

class DoorEventHandlerTest {
    private static DoorEventHandler doorEventHandler;
    private static Door openedDoor;
    private static Door closedDoor;

    @BeforeAll
    static void setUp() {
        SmartHome smartHome = new SmartHome();
        openedDoor = new Door(true, "1", "dvushka");
        closedDoor = new Door(false, "2", "treshka");
        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(openedDoor), "dvushka"));
        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(closedDoor), "treshka"));
        doorEventHandler = new DoorEventHandler(smartHome);
    }

    @Test
    void openOpenedAndClosedDoors() {
        SensorEvent openOpenedDoorEvent = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        SensorEvent openClosedDoorEvent = new SensorEvent(SensorEventType.DOOR_OPEN, "2");
        doorEventHandler.handle(openOpenedDoorEvent);
        doorEventHandler.handle(openClosedDoorEvent);
        Assert.assertTrue(openedDoor.getState());
        Assert.assertTrue(closedDoor.getState());
    }

    @Test
    void closeOpenedAndClosedDoors() {
        SensorEvent closeOpenedDoorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        SensorEvent closeClosedDoorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "2");
        doorEventHandler.handle(closeOpenedDoorEvent);
        doorEventHandler.handle(closeClosedDoorEvent);
        Assert.assertFalse(openedDoor.getState());
        Assert.assertFalse(closedDoor.getState());
    }
}