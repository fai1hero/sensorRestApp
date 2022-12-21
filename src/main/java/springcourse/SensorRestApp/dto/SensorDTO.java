package springcourse.SensorRestApp.dto;


import springcourse.SensorRestApp.models.Sensor;

public class SensorDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO() {
    }
}
