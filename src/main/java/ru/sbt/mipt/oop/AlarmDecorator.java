package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.*;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmDecorator implements EventRunnable {

    private Alarm alarm;
    private EventRunnable eventRunner;

    public AlarmDecorator(Alarm alarm, EventRunnable eventRunner) {
        this.alarm = alarm;
        this.eventRunner = eventRunner;
    }

    @Override
    public void runEvents(SensorEvent event) {
        if (event == null) {
            return;
        }

        System.out.println("Got event: " + event);

        AlarmState alarmState = alarm.getState();
        if (alarmState instanceof DeactivatedState) {
            eventRunner.runEvents(event);
        } else if (alarmState instanceof ActivatedState) {
            if (event.getType() == ALARM_DEACTIVATE) {
                System.out.println("trying to deactivate alert with password: " + event.getCode());
                eventRunner.runEvents(event);
            } else {
                alarm.setState(new AlertState(alarm));
                System.out.println("Sending ALERT sms");
            }
        } else {
            if (event.getType() == ALARM_DEACTIVATE) {
                System.out.println("trying to deactivate alert with password: " + event.getCode());
                eventRunner.runEvents(event);
            } else {
                System.out.println("Sending ALERT sms");
            }
        }
        this.runEvents(SensorEventGetter.getNextSensorEvent());
    }
}
