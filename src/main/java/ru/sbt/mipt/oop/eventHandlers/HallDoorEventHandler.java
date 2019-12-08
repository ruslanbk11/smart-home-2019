package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.*;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler{
    private SmartHome smartHome;

    public HallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event == null) {
            return;
        }

        Action action;
        if (event.getType() != DOOR_CLOSED) {
            action = null;
        } else {
            action = o -> {
                if (!(o instanceof Door)){
                    return;
                }
                Door door = (Door) o;
                if (door.getId().equals(event.getObjectId())) {
                    if (door.getRoomName().equals("hall")) {
                        if (!door.getState()) {
                            Action lightsOff = o1 -> {
                                    if (!(o1 instanceof Light)) {
                                        return;
                                    }
                                    Light light = (Light) o1;
                                    light.setState(false);
                                };
                            smartHome.execute(lightsOff);
                            }
                            System.out.println("All lights were turned off.");
                        }
                    }
                };
            }
        if (action != null) {
            smartHome.execute(action);
        }
    }
}
