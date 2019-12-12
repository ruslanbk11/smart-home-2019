package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.*;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
    private SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event == null) {
            return;
        }

        Action action;
        if (event.getType() != DOOR_OPEN && event.getType() != DOOR_CLOSED) {
            action = null;
        } else {
            action = o -> {
                if (!(o instanceof Door)) {
                    return;
                }
                Door door = (Door) o;
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setState(true);
                        System.out.println("Door " + door.getId() + " in " + door.getRoomName() + " was opened.");
                    } else {
                        door.setState(false);
                        System.out.println("Door " + door.getId() + " in " + door.getRoomName() + " was closed.");
                    }
                }
            };
        }
        if (action != null) {
            smartHome.execute(action);
        }
    }
}
