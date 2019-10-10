package ru.sbt.mipt.oop;

public class HomeObject {
    private boolean state;
    private final String id;

    public HomeObject(boolean state, String id) {
        this.state = state;
        this.id = id;
    }

    public boolean getState() {
        return state;
    }

    public String getId() {
        return id;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
