package ru.sbt.mipt.oop.remoteControl.commands;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class HallLightsOnCommand implements RemoteControlCommand {
    private SmartHome smartHome;

    public HallLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute( o -> {
            if (o instanceof Room && ((Room) o).getName().equals("hall")) {
                ((Room) o).execute(o1 -> {
                    if (o1 instanceof Light) {
                        ((Light) o1).setState(true);
                        System.out.println("Hall lights were turned on by remote control");
                    }
                });
            }
        });
    }
}
