package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.BeforeAll;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventHandlers.AlarmEventHandler;

class AlarmTest {

    private static AlarmEventHandler alarmEventHandler;

    @BeforeAll
    static void setUp() {
        SmartHome smartHome = new SmartHome();
        alarmEventHandler = new AlarmEventHandler(smartHome);
    }
}