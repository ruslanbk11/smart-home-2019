package ru.sbt.mipt.oop;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

class EventRunner {
    private static HomeObject checkObject(String eventId, Collection<Room> rooms, SensorEventType type){
        for (Room room : rooms) {
            if (type == LIGHT_ON || type == LIGHT_OFF) {
                for (HomeObject homeObject : room.getLights()) {
                    if (homeObject.getId().equals(eventId)) {
                        System.out.println("Light " + homeObject.getId() + " in room " + room.getName());
                        return homeObject;
                    }
                }
            }
            if (type == DOOR_OPEN || type == DOOR_CLOSED){
                for (HomeObject homeObject : room.getDoors()) {
                    if (homeObject.getId().equals(eventId)) {
                        System.out.println("Door " + homeObject.getId() + " in room " + room.getName());
                        return homeObject;
                    }
                }
            }
        }
        return null;
    }

    static void runEvents(SmartHome smartHome){
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            HomeObject object;
            switch (event.getType()){
                case LIGHT_ON:
                    object = checkObject(event.getObjectId(), smartHome.getRooms(), LIGHT_ON);
                    if (object != null){
                        object.setState(true);
                    }
                    break;
                case LIGHT_OFF:
                    object = checkObject(event.getObjectId(), smartHome.getRooms(), LIGHT_OFF);
                    if (object != null){
                        object.setState(false);
                    }
                    break;
                case DOOR_OPEN:
                    object = checkObject(event.getObjectId(), smartHome.getRooms(), DOOR_OPEN);
                    if (object != null){
                        object.setState(true);
                    }
                    break;
                case DOOR_CLOSED:
                    object = checkObject(event.getObjectId(), smartHome.getRooms(), DOOR_CLOSED);
                    if (object != null){
                        object.setState(false);
                    }
                    break;
            }
            event = getNextSensorEvent();
        }
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

}
