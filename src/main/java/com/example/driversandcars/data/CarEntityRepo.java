package com.example.driversandcars.data;

import com.example.driversandcars.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarEntityRepo extends JpaRepository<CarEntity, Long> {
   Optional<CarEntity> findByNumberOfCar(String numberOfCar);
   Optional<CarEntity> findByNumberOfCarAndOwnerNotNull(String numberOfCar);
}
