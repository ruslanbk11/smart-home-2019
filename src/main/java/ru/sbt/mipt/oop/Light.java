package ru.sbt.mipt.oop;

public class Light implements Actionable {
    private final String id;
    private boolean state;
    private final String roomName;

    public Light(boolean state, String id, String roomName) {
        this.id = id;
        this.state = state;
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getId() {
        return id;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
