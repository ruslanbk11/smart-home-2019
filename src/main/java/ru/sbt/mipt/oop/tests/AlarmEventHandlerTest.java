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
        alarmEventHandler.handleEvent(activateDeactivatedAlarm);
        Assert.assertTrue(alarm.getState() instanceof ActivatedState);
        Assert.assertTrue(alarm.checkCode("pass"));
    }

    @Test
    void deactivateActivatedAlarm() {
        SensorEvent activateDeactivatedAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "0");
        activateDeactivatedAlarm.setCode("pass");
        alarmEventHandler.handleEvent(activateDeactivatedAlarm);

        SensorEvent deactivateActivatedAlarm = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "0");
        deactivateActivatedAlarm.setCode("pass");
        alarmEventHandler.handleEvent(deactivateActivatedAlarm);
        Assert.assertTrue(alarm.getState() instanceof DeactivatedState);
    }

    @Test
    void unsuccessfulDeactivationOfActivatedAlarm() {
        SensorEvent activateDeactivatedAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "0");
        activateDeactivatedAlarm.setCode("pass");
        alarmEventHandler.handleEvent(activateDeactivatedAlarm);

        SensorEvent wrongDeactivationActivatedAlarm = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "0");
        wrongDeactivationActivatedAlarm.setCode("you_shall_not");
        alarmEventHandler.handleEvent(wrongDeactivationActivatedAlarm);
        Assert.assertTrue(alarm.getState() instanceof AlertState);
    }

    @Test
    void activateAlertOnDeactivatedAlarm() {
        alarm.alert();
        Assert.assertTrue(alarm.getState() instanceof AlertState);
    }

    @Test
    void activateAlertOnActiveAlarm() {
        SensorEvent activateDeactivatedAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "0");
        activateDeactivatedAlarm.setCode("pass");
        alarmEventHandler.handleEvent(activateDeactivatedAlarm);

        alarm.alert();
        Assert.assertTrue(alarm.getState() instanceof AlertState);
    }

    @Test
    void deactivateAlertOnInitiallyActivatedAlarm() {
        SensorEvent activateDeactivatedAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "0");
        activateDeactivatedAlarm.setCode("pass");
        alarmEventHandler.handleEvent(activateDeactivatedAlarm);
        alarm.alert();

        SensorEvent deactivateAlertedAlarm = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "0");
        deactivateAlertedAlarm.setCode("pass");
        alarmEventHandler.handleEvent(deactivateAlertedAlarm);
        Assert.assertTrue(alarm.getState() instanceof DeactivatedState);
    }
}