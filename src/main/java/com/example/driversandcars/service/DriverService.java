package com.example.driversandcars.service;

import com.example.driversandcars.data.CarEntityRepo;
import com.example.driversandcars.data.DriverEntityRepo;
import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.entity.CarEntity;
import com.example.driversandcars.mapper.DriverMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final CarEntityRepo carEntityRepo;
    private final DriverEntityRepo driverEntityRepo;
    private final DriverMapper driverMapper;

    public DriverDto getDriverByCarId(String numberOfCar) {
        CarEntity carEntity = carEntityRepo.findByNumberOfCarAndOwnerNotNull(numberOfCar)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Машина с номером %s не найдена", numberOfCar)));
        return driverMapper.toDriverDto(carEntity.getOwner()); // return 404 exception
    }

    @Transactional
    public DriverDto addDriver(DriverDto driverDTO) {
        return driverMapper.toDriverDto(driverEntityRepo.save(driverMapper.toDriverEntity(driverDTO)));
    }
}
