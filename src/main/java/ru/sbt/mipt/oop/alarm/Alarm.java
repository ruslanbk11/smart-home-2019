package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Alarm implements Actionable {
    private AlarmState state = new DeactivatedState(this);
    private String code = "";

    public boolean checkCode(String code){
        return code.equals(this.code);
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AlarmState getState() {
        return state;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void alert() {
        state.alert();
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
