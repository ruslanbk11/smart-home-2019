package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

class LightEventHandler implements EventHandler{
    static void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == LIGHT_OFF || event.getType() == LIGHT_ON) {
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            light.setState(true);
                            System.out.println("Light " + light.getId() + " in " + room.getName() + " was turned on.");
                        } else {
                            light.setState(false);
                            System.out.println("Light " + light.getId() + " in " + room.getName() + " was turned off.");
                        }
                    }
                }
            }
        }
    }
}
