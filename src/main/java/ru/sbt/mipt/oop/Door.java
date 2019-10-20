package ru.sbt.mipt.oop;

public class Door extends HomeObject {
    private boolean state;

    public Door(boolean state, String id) {
        super(id);
        this.state = state;
    }

    void setState(boolean state) {
        this.state = state;
    }

    boolean getState() {
        return state;
    }
}

