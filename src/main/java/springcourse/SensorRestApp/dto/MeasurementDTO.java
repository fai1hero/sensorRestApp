package springcourse.SensorRestApp.dto;

import springcourse.SensorRestApp.models.Sensor;

public class MeasurementDTO {

    private SensorDTO sensor;

    private double tempValue;

    private boolean raining;

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public double getTempValue() {
        return tempValue;
    }

    public void setTempValue(int tempValue) {
        this.tempValue = tempValue;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

}
