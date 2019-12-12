package ru.sbt.mipt.oop.remoteControl.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;

public class ActivateAlarmCommand implements RemoteControlCommand {
    private SmartHome smartHome;

    public ActivateAlarmCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(o -> {
            if (o instanceof Alarm){
                ((Alarm) o).activate("secretnyi_kod_nikomu_ne_govori");
                System.out.println("Alarm activated by remote control");
            }
        });
    }
}
