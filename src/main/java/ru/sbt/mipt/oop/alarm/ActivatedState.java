package ru.sbt.mipt.oop.alarm;

public class ActivatedState implements AlarmState {
    private Alarm alarm;

    public ActivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (alarm.checkCode(code)) {
            alarm.setState(new DeactivatedState(alarm));
            System.out.println("Alarm successfully deactivated");
        } else {
            alarm.setState(new AlertState(alarm));
            System.out.println("Wrong code, ALERT!!!");
        }
    }

    @Override
    public void alert() {
        alarm.setState(new AlertState(alarm));
        System.out.println("ALERT!!!");
    }
}
