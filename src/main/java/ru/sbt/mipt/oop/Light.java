package ru.sbt.mipt.oop;

public class Light extends HomeObject{
    private boolean state;

    public Light(boolean state, String id) {
        super(id);
        this.state = state;
    }

    void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }
}
