package ru.sbt.mipt.oop;

class HallDoorEventHandler {
    static void handle(SensorEvent event, SmartHome smartHome) {
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
                        }
                    }
                    System.out.println("All lights were turned off.");
                }
            }
        }
    }
}
