package springcourse.SensorRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcourse.SensorRestApp.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository  extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findByName(String name);
}
