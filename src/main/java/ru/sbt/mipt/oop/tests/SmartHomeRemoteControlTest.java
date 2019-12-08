package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rc.RemoteControl;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeConfiguration;
import ru.sbt.mipt.oop.alarm.ActivatedState;
import ru.sbt.mipt.oop.alarm.AlertState;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeRemoteControlTest {

    private SmartHome smartHome;
    private RemoteControl rc;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        smartHome = context.getBean(SmartHome.class);
        rc = (RemoteControl) context.getBean("remoteControl");
    }

    @Test
    public void checkActivateAlarm() {
        rc.onButtonPressed("A", "1");
        assertTrue(smartHome.getAlarm().getState() instanceof ActivatedState);
    }

    @Test
    public void checkAlertActivation() {
        rc.onButtonPressed("1", "1");
        assertTrue(smartHome.getAlarm().getState() instanceof AlertState);
    }
}