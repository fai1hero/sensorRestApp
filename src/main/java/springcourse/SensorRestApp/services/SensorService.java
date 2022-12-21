package springcourse.SensorRestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcourse.SensorRestApp.dto.SensorDTO;
import springcourse.SensorRestApp.models.Sensor;
import springcourse.SensorRestApp.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void saveSensor(SensorDTO sensorDTO) {
        sensorRepository.save(convertToSensor(sensorDTO));
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }


    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
