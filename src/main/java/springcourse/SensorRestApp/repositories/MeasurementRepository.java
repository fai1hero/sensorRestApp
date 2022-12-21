package springcourse.SensorRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springcourse.SensorRestApp.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    public int findMeasurementsByAddedAtTrue();
}
