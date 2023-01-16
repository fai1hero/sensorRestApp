package springcourse.SensorRestApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import springcourse.SensorRestApp.dto.MeasurementDTO;
import springcourse.SensorRestApp.services.MeasurementService;
import springcourse.SensorRestApp.utill.MeasurementErrorResponse;
import springcourse.SensorRestApp.utill.MeasurementNotAddedException;
import springcourse.SensorRestApp.utill.SensorErrorResponse;
import springcourse.SensorRestApp.utill.SensorNotRegisteredException;

import javax.validation.Valid;
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
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new MeasurementNotAddedException(errorMessage.toString());
        }


        measurementService.saveMeasurement(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handeException(MeasurementNotAddedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handeException(SensorNotRegisteredException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                "Sensor with this name not registered",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND 404 status
    }


}
