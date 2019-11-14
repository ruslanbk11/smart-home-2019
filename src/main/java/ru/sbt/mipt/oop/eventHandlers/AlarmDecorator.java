package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.alarm.*;

import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmDecorator implements EventHandler {

    private Alarm alarm;
    private List<EventHandler> eventHandlers;

    public AlarmDecorator(Alarm alarm, List<EventHandler> eventHandlers) {
        this.alarm = alarm;
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void handle(SensorEvent event) {
        AlarmState alarmState = alarm.getState();
        if (alarmState instanceof DeactivatedState) {
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handle(event);
            }
        } else if (alarmState instanceof ActivatedState) {
            if (event.getType() == ALARM_DEACTIVATE) {
                System.out.println("trying to deactivate alert with password: " + event.getCode());
                for (EventHandler eventHandler : eventHandlers) {
                    eventHandler.handle(event);
                }
            } else {
                alarm.setState(new AlertState(alarm));
                System.out.println("Sending ALERT sms");
            }
        } else {
            if (event.getType() == ALARM_DEACTIVATE) {
                System.out.println("trying to deactivate alert with password: " + event.getCode());
                for (EventHandler eventHandler : eventHandlers) {
                    eventHandler.handle(event);
                }
            } else {
                System.out.println("Sending ALERT sms");
            }
        }
    }
}
