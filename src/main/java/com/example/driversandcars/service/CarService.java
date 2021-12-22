package com.example.driversandcars.service;

import com.example.driversandcars.data.CarEntityRepo;
import com.example.driversandcars.dto.CarDto;
import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.entity.CarEntity;
import com.example.driversandcars.mapper.CarMapper;
import com.example.driversandcars.mapper.DriverMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarEntityRepo carEntityRepo;
    private final CarMapper carMapper;
    private final DriverMapper driverMapper;

    public CarDto addCar(CarDto car) {
        return carMapper.toCarDto(carEntityRepo.save(carMapper.toCarEntity(car)));
    }

    public DriverDto getDriverByCarId(String numberOfCar) {
        Optional<CarEntity> carEntity = carEntityRepo.findByNumberOfCar(numberOfCar);
        return carEntity.isEmpty() ? new DriverDto() : driverMapper.toDriverDto(carEntity.get().getOwner()); // return 404 exception
    }

}
