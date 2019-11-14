package ru.sbt.mipt.oop.tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.ActivatedState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlertState;
import ru.sbt.mipt.oop.alarm.DeactivatedState;
import ru.sbt.mipt.oop.eventHandlers.AlarmEventHandler;
import ru.sbt.mipt.oop.eventHandlers.EventHandler;

import static org.junit.jupiter.api.Assertions.*;

class AlarmEventHandlerTest {
    private static Alarm alarm;
    private static EventHandler alarmEventHandler;

    @BeforeAll
    static void setUp() {
        SmartHome smartHome = new SmartHome();
        alarm = new Alarm();
        smartHome.setAlarm(alarm);
        alarmEventHandler = new AlarmEventHandler(smartHome);
    }

    @Test
    void activateDeactivatedAlarm() {
        SensorEvent activateDeactivatedAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "0");
        activateDeactivatedAlarm.setCode("pass");
        alarmEventHandler.handle(activateDeactivatedAlarm);
        Assert.assertTrue(alarm.getState() instanceof ActivatedState);
        Assert.assertTrue(alarm.checkCode("pass"));
    }

    @Test
    void deactivateActivatedAlarm() {
        SensorEvent activateDeactivatedAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "0");
        activateDeactivatedAlarm.setCode("pass");
        alarmEventHandler.handle(activateDeactivatedAlarm);

        SensorEvent deactivateActivatedAlarm = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "0");
        deactivateActivatedAlarm.setCode("pass");
        alarmEventHandler.handle(deactivateActivatedAlarm);
        Assert.assertTrue(alarm.getState() instanceof DeactivatedState);
    }

    @Test
    void unsuccessfulDeactivationOfActivatedAlarm() {
        SensorEvent activateDeactivatedAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "0");
        activateDeactivatedAlarm.setCode("pass");
        alarmEventHandler.handle(activateDeactivatedAlarm);

        SensorEvent wrongDeactivationActivatedAlarm = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "0");
        wrongDeactivationActivatedAlarm.setCode("you_shall_not");
        alarmEventHandler.handle(wrongDeactivationActivatedAlarm);
        Assert.assertTrue(alarm.getState() instanceof AlertState);
    }
}