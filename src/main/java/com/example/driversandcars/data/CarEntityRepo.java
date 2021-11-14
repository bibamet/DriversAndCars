package com.example.driversandcars.data;

import com.example.driversandcars.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarEntityRepo extends JpaRepository<CarEntity, Long> {
   Optional<CarEntity> findByNumberOfCar(String numberOfCar);
}
