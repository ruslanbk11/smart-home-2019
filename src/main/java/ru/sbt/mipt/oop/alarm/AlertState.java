package ru.sbt.mipt.oop.alarm;

public class AlertState implements AlarmState {
    private Alarm alarm;

    public AlertState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        if (alarm.checkCode(code)) {
            alarm.setState(new ActivatedState(alarm));
            System.out.println("Alert canceled. Alarm activated.");
        }
    }

    @Override
    public void deactivate(String code) {
        if(alarm.checkCode(code)) {
            alarm.setState(new DeactivatedState(alarm));
            System.out.println("Alert canceled. Alarm deactivated.");
        } else {
            System.out.println("WRONG DEACTIVATION CODE ALERT !!!");
        }
    }

    @Override
    public void alert() {
    }
}
