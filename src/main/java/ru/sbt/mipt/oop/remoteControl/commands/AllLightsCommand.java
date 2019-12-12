package ru.sbt.mipt.oop.remoteControl.commands;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;

public class AllLightsCommand implements RemoteControlCommand{
    private SmartHome smartHome;
    private boolean turningOn;

    public AllLightsCommand(SmartHome smartHome, boolean turningOn) {
        this.smartHome = smartHome;
        this.turningOn = turningOn;
    }

    @Override
    public void execute() {
        smartHome.execute(o -> {
            if (o instanceof Light) {
                ((Light) o).setState(turningOn);
                String isOn = turningOn ? "on" : "off";
                System.out.println("All lights were turned " + isOn + " by remote control");
            }
        });
    }
}
