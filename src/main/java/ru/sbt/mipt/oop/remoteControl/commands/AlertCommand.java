package ru.sbt.mipt.oop.remoteControl.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;

public class AlertCommand implements RemoteControlCommand {
    private SmartHome smartHome;

    public AlertCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(o -> {
            if (o instanceof Alarm){
                ((Alarm) o).alert();
                System.out.println("Alert activated by remote control");
            }
        });
    }
}
