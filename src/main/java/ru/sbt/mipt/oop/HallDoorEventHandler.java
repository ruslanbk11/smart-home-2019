package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

class HallDoorEventHandler implements EventHandler{
    static void handle(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (room.getName().equals("hall")) {
                            if (!door.getState()) {
                                for (Room homeRoom : smartHome.getRooms()) {
                                    for (Light light : homeRoom.getLights()) {
                                        light.setState(false);
                                    }
                                }
                                System.out.println("All lights were turned off.");
                            }
                        }
                    }
                }
            }
        }
    }
}
