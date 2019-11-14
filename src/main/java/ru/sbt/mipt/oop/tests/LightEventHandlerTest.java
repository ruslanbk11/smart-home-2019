package ru.sbt.mipt.oop.tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.eventHandlers.EventHandler;
import ru.sbt.mipt.oop.eventHandlers.LightEventHandler;

import java.util.Arrays;
import java.util.Collections;

class LightEventHandlerTest {
    private static EventHandler lightEventHandler;
    private static Light turnedOffLight;
    private static Light turnedOnLight;

    @BeforeAll
    static void setUp() {
        SmartHome smartHome = new SmartHome();
        turnedOffLight = new Light(false, "1", "bedroom");
        turnedOnLight = new Light(true, "2", "kitchen");
        smartHome.addRoom(new Room(Arrays.asList(turnedOffLight), Collections.emptyList(), "bedroom"));
        smartHome.addRoom(new Room(Arrays.asList(turnedOnLight), Collections.emptyList(), "kitchen"));
        lightEventHandler = new LightEventHandler(smartHome);
    }

    @Test
    void turnOnTheLights() {
        SensorEvent turnOnTurnedOffLight = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        SensorEvent turnOnTurnedOnLight = new SensorEvent(SensorEventType.LIGHT_ON, "2");
        lightEventHandler.handle(turnOnTurnedOffLight);
        lightEventHandler.handle(turnOnTurnedOnLight);
        Assert.assertTrue(turnedOffLight.getState());
        Assert.assertTrue(turnedOnLight.getState());
    }

    @Test
    void turnOffTheLights() {
        SensorEvent turnOffTurnedOffLight = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        SensorEvent turnOffTurnedOnLight = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        lightEventHandler.handle(turnOffTurnedOffLight);
        lightEventHandler.handle(turnOffTurnedOnLight);
        Assert.assertFalse(turnedOffLight.getState());
        Assert.assertFalse(turnedOnLight.getState());
    }
}