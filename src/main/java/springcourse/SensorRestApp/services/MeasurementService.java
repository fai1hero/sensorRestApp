package springcourse.SensorRestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcourse.SensorRestApp.dto.MeasurementDTO;
import springcourse.SensorRestApp.models.Measurement;
import springcourse.SensorRestApp.repositories.MeasurementRepository;
import springcourse.SensorRestApp.utill.SensorNotRegisteredException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;
    private final SensorService sensorService;


    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, ModelMapper modelMapper, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    public List<MeasurementDTO> getAllMeasurements() {
        return measurementRepository.findAll()
                .stream()
                .map(this::convertToMeasDTO)
                .collect(Collectors.toList());
    }

    // Возвращаем количество дождливых дней
    public int getAllRainyDays() {
        return measurementRepository.findAll()
                .stream()
                .filter(Measurement::isRaining)
                .toList().size();
    }

    @Transactional
    public void saveMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurementToSave = convertToMeasurement(measurementDTO);
        measurementToSave.setAddedAt(LocalDateTime.now());
        measurementToSave.setSensor(sensorService
                .findByName(measurementToSave.getSensor().getName()).orElseThrow(SensorNotRegisteredException::new));

        measurementRepository.save(measurementToSave);
    }

    private MeasurementDTO convertToMeasDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
