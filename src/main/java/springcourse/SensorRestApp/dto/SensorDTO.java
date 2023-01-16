package springcourse.SensorRestApp.dto;


import springcourse.SensorRestApp.models.Sensor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 3, max = 40, message = "Название сенсора должно содержать от 3 до 40 символов")
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
