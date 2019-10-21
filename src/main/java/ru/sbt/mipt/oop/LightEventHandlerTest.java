package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LightEventHandlerTest {
    @Test
    void turnOnTheLights() {
        SmartHome smartHome = new SmartHome();
        Light turnedOffLight = new Light(false, "1");
        Light turnedOnLight = new Light(true, "2");
        smartHome.addRoom(new Room(Arrays.asList(turnedOffLight), Collections.emptyList(), "bedroom"));
        smartHome.addRoom(new Room(Arrays.asList(turnedOnLight), Collections.emptyList(), "kitchen"));

        SensorEvent turnOnTurnedOffLight = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        SensorEvent turnOnTurnedOnLight = new SensorEvent(SensorEventType.LIGHT_ON, "2");
        LightEventHandler.handle(turnOnTurnedOffLight, smartHome);
        LightEventHandler.handle(turnOnTurnedOnLight, smartHome);
        Assert.assertTrue(turnedOffLight.getState());
        Assert.assertTrue(turnedOnLight.getState());
    }

    @Test
    void turnOffTheLights() {
        SmartHome smartHome = new SmartHome();
        Light turnedOffLight = new Light(false, "1");
        Light turnedOnLight = new Light(true, "2");
        smartHome.addRoom(new Room(Arrays.asList(turnedOffLight), Collections.emptyList(), "bedroom"));
        smartHome.addRoom(new Room(Arrays.asList(turnedOnLight), Collections.emptyList(), "kitchen"));


        SensorEvent turnOffTurnedOffLight = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        SensorEvent turnOffTurnedOnLight = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        LightEventHandler.handle(turnOffTurnedOffLight, smartHome);
        LightEventHandler.handle(turnOffTurnedOnLight, smartHome);
        Assert.assertFalse(turnedOffLight.getState());
        Assert.assertFalse(turnedOnLight.getState());
    }
}