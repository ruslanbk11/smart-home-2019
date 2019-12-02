package ru.sbt.mipt.oop;

public class HomeRunner {
    private EventRunnable alarmDecorator;

    public HomeRunner(EventRunnable alarmDecorator) {
        this.alarmDecorator = alarmDecorator;
    }

    public void run() {
        SensorEvent event = SensorEventGetter.getNextSensorEvent();
        while(event != null) {
            this.alarmDecorator.runEvent(event);
            event = SensorEventGetter.getNextSensorEvent();
        }
    }
}
