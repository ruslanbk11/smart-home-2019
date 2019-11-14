package ru.sbt.mipt.oop.tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.eventHandlers.DoorEventHandler;
import ru.sbt.mipt.oop.eventHandlers.EventHandler;
import ru.sbt.mipt.oop.eventHandlers.HallDoorEventHandler;

import java.util.Arrays;
import java.util.Collections;

class HallDoorEventHandlerTest {

    private static Door openedHallDoor;
    private static EventHandler doorEventHandler;
    private static EventHandler hallDoorEventHandler;
    private static Light hallLight;
    private static Light bathroomLights;

    @BeforeAll
    static void setUp() {
        SmartHome smartHome = new SmartHome();
        openedHallDoor = new Door(true, "1", "hall");
        hallLight = new Light(true, "2", "hall");
        bathroomLights = new Light(true, "3", "bathroom");
        smartHome.addRoom(new Room(Arrays.asList(hallLight), Arrays.asList(openedHallDoor), "hall"));
        smartHome.addRoom(new Room(Arrays.asList(bathroomLights),  Collections.emptyList(), "bathroom"));
        doorEventHandler = new DoorEventHandler(smartHome);
        hallDoorEventHandler = new HallDoorEventHandler(smartHome);
    }

    @Test
    void closeHallDoorAndTurnOffLights() {
        SensorEvent closeHallDoorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        doorEventHandler.handle(closeHallDoorEvent);
        hallDoorEventHandler.handle(closeHallDoorEvent);
        Assert.assertFalse(hallLight.getState());
        Assert.assertFalse(bathroomLights.getState());
        Assert.assertFalse(openedHallDoor.getState());
    }
}