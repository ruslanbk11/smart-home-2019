package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DeactivatedState;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable{
    Collection<Room> rooms;
    Alarm alarm = new Alarm("parol_ot_signalizacii");

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
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
