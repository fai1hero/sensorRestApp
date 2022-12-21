package springcourse.SensorRestApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springcourse.SensorRestApp.dto.MeasurementDTO;
import springcourse.SensorRestApp.services.MeasurementService;

import java.util.List;


@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping()
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public int getNumberOfRainyDays() {
        return measurementService.getAllRainyDays();
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody MeasurementDTO measurementDTO) {
        measurementService.saveMeasurement(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
