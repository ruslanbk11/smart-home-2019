package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.alarm.*;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmDecorator implements EventHandler {

    private Alarm alarm;
    private EventHandler eventHandler;

    AlarmDecorator(Alarm alarm, EventHandler eventHandler) {
        this.alarm = alarm;
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        if (event == null) {
            return;
        }

        AlarmState alarmState = alarm.getState();
        if (alarmState instanceof DeactivatedState) {
            eventHandler.handleEvent(event);
        } else {
            if (event.getEventType().equals(ALARM_DEACTIVATE.toString())) {
                System.out.println("trying to deactivate alert with password");
                eventHandler.handleEvent(event);
            } else {
                if (alarmState instanceof ActivatedState) {
                    alarm.setState(new AlertState(alarm));
                }
                System.out.println("Sending ALERT sms");
            }
        }
    }
}
