package ru.sbt.mipt.oop.remoteControl;

import rc.RemoteControl;
import ru.sbt.mipt.oop.remoteControl.commands.RemoteControlCommand;

import java.util.HashMap;
import java.util.Map;

public class SmartHomeRemoteControl implements RemoteControl {
    private final Map<String, RemoteControlCommand> commands = new HashMap<>();
    private String rcId;

    public SmartHomeRemoteControl(String rcId){
        this.rcId = rcId;
    }

    public void addCommand(String buttonCode, RemoteControlCommand command) {
        commands.put(buttonCode, command);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (rcId.equals(this.rcId) && commands.containsKey(buttonCode)){
            commands.get(buttonCode).execute();
        }
    }
}
