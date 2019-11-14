package ru.sbt.mipt.oop.alarm;

public class DeactivatedState implements AlarmState {
    private Alarm alarm;

    public DeactivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.setCode(code);
        alarm.setState(new ActivatedState(alarm));
        System.out.println("Alarm activated");
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void alert() {
        alarm.setState(new AlertState(alarm));
        System.out.println("ALERT!!!");
    }
}
