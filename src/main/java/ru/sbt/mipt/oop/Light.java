package ru.sbt.mipt.oop;

public class Light {
    private String id;
    private boolean state;

    public Light(boolean state, String id) {
        this.id = id;
        this.state = state;
    }

    String getId() {
        return id;
    }

    void setState(boolean state) {
        this.state = state;
    }

    boolean getState() {
        return state;
    }
}
