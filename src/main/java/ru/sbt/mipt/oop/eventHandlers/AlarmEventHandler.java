package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventHandler implements EventHandler {

    private SmartHome smartHome;

    public AlarmEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event){
        Action action;
        if (event.getType() != ALARM_ACTIVATE && event.getType() != ALARM_DEACTIVATE) {
            action = null;
        } else {
            action = o -> {
                if (!(o instanceof Alarm)){
                    return;
                }
                Alarm alarm = (Alarm) o;
                if (event.getType() == ALARM_ACTIVATE) {
                    alarm.activate("parol_ot_signalizacii");
                } else {
                    alarm.deactivate("parol_ot_signalizacii");
                }
            };
        }
        if (action != null) {
            smartHome.execute(action);
        }
    }
}
