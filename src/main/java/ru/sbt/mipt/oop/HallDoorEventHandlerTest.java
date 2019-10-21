package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventHandlerTest {
    @Test
    void closeHallDoorAndTurnOffLights() {
        SmartHome smartHome = new SmartHome();
        Door openedHallDoor = new Door(true, "1");
        Light hallLight = new Light(true, "2");
        Light bathroomLights = new Light(true, "3");
        smartHome.addRoom(new Room(Arrays.asList(hallLight), Arrays.asList(openedHallDoor), "hall"));
        smartHome.addRoom(new Room(Arrays.asList(bathroomLights),  Collections.emptyList(), "bathroom"));

        SensorEvent closeHallDoorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        DoorEventHandler.handle(closeHallDoorEvent, smartHome);
        HallDoorEventHandler.handle(closeHallDoorEvent, smartHome);
        Assert.assertFalse(hallLight.getState());
        Assert.assertFalse(bathroomLights.getState());
        Assert.assertFalse(openedHallDoor.getState());
    }
}