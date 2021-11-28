package com.example.driversandcars.service;

import com.example.driversandcars.data.CarEntityRepo;
import com.example.driversandcars.data.DriverEntityRepo;
import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.entity.CarEntity;
import com.example.driversandcars.entity.DriverEntity;
import com.example.driversandcars.mapper.CarMapper;
import com.example.driversandcars.mapper.DriverMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriversAndCarsService {

    private final CarEntityRepo carEntityRepo;
    private final DriverEntityRepo driverEntityRepo;
    private final DriverMapper driverMapper;
    private final CarMapper carMapper;

    public DriverDto getDriverByCarId(String numberOfCar) {
        Optional<CarEntity> carEntity = carEntityRepo.findByNumberOfCar(numberOfCar);
        return carEntity.isEmpty() ? new DriverDto() : driverMapper.toDriverDto(carEntity.get().getOwner()); // return 404 exception
    }

    @Transactional
    public void addDriversAndCars(DriverDto driverDTO) {
        Example<DriverEntity> example = Example.of(driverMapper.toDriverEntity(driverDTO));
        driverEntityRepo.findOne(example).ifPresentOrElse(driverEntity -> {
            CarEntity carEntity = carMapper.toCarEntity(driverDTO.getCar());
            carEntity.setOwner(driverEntity);
            driverEntity.getCars().add(carEntity);
            driverEntityRepo.save(driverEntity);
        }, () -> {
            var driverEntity = driverMapper.toDriverEntity(driverDTO);
            driverEntity.setCars(List.of(carMapper.toCarEntity(driverDTO.getCar())));
//            driverEntity.getCars().get(0).setOwner(driverEntity); // todo research and remove
            driverEntityRepo.save(driverEntity);
        });
    }
}
