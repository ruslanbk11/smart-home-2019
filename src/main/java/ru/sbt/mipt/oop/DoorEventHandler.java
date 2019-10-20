package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

class DoorEventHandler {
    static void handle(SensorEvent event, SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setState(true);
                        System.out.println("Door " + door.getId() + " in " + room.getName() + " was opened.");
                    } else {
                        door.setState(false);
                        System.out.println("Door " + door.getId() + " in " + room.getName() + " was closed.");
                    }
                }
            }
        }
    }
}
