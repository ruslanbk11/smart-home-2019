package ru.sbt.mipt.oop.remoteControl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.remoteControl.commands.*;

@Configuration
public class RemoteControlConfiguration {
    @Bean
    public RemoteControl remoteControl(ActivateAlarmCommand activate,
                                             @Qualifier("AllLightsOn") AllLightsCommand AllLightsOn,
                                             @Qualifier("AllLightsOff") AllLightsCommand AllLightsOff,
                                             CloseHallDoorCommand closeHallDoor,
                                             AlertCommand alert,
                                             HallLightsOnCommand hallLightsOn,
                                             RemoteControlRegistry remoteControlRegistry) {
        SmartHomeRemoteControl rc = new SmartHomeRemoteControl("1");
        rc.addCommand("A", activate);
        rc.addCommand("B", AllLightsOn);
        rc.addCommand("C", AllLightsOff);
        rc.addCommand("D", closeHallDoor);
        rc.addCommand("1", alert);
        rc.addCommand("2", hallLightsOn);
        remoteControlRegistry.registerRemoteControl(rc, "1");
        return rc;
    }

    @Bean
    public RemoteControlRegistry remoteControlRegistry(){
        return new RemoteControlRegistry();
    }

    @Bean
    public ActivateAlarmCommand activate(SmartHome smartHome) {
        return new ActivateAlarmCommand(smartHome);
    }

    @Bean
    public AllLightsCommand AllLightsOn(SmartHome smartHome) {
        return new AllLightsCommand(smartHome, true);
    }

    @Bean
    public AllLightsCommand AllLightsOff(SmartHome smartHome) {
        return new AllLightsCommand(smartHome, false);
    }

    @Bean
    public CloseHallDoorCommand closeHallDoor(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

    @Bean
    public AlertCommand alert(SmartHome smartHome) {
        return new AlertCommand(smartHome);
    }

    @Bean
    public HallLightsOnCommand hallLightsOn(SmartHome smartHome) {
        return new HallLightsOnCommand(smartHome);
    }
}
