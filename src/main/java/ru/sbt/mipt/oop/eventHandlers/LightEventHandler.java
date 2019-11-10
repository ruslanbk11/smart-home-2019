package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.*;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler{
    private SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        Action action;
        if (event.getType() != LIGHT_OFF && event.getType() != LIGHT_ON) {
            action = null;
        } else {
            action = o -> {
                if (!(o instanceof Light)) {
                    return;
                }
                Light light = (Light) o;
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setState(true);
                        System.out.println("Light " + light.getId() + " in " + light.getRoomName() + " was turned on.");
                    } else {
                        light.setState(false);
                        System.out.println("Light " + light.getId() + " in " + light.getRoomName() + " was turned off.");
                    }
                }
            };
        }
        if (action != null) {
            smartHome.execute(action);
        }
    }
}
