package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable{
    Collection<Room> rooms;
    Alarm alarm = new Alarm();

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        alarm.execute(action);
        for (Actionable room : rooms) {
            room.execute(action);
        }
    }
}
