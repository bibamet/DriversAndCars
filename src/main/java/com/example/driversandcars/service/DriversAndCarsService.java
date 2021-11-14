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

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriversAndCarsService {

    private final CarEntityRepo carEntityRepo;
    private final DriverEntityRepo driverEntityRepo;
    private final DriverMapper driverMapper;
    private final CarMapper carMapper;

    @Transactional
    public DriverDto getDriverByCarId(String numberOfCar, HttpServletRequest httpServletRequest) {

        Optional<CarEntity> carEntity = carEntityRepo.findByNumberOfCar(numberOfCar);

        return carEntity.isEmpty() ? new DriverDto() : driverMapper.toDriverDto(carEntity.get().getOwner());

    }

    public DriverDto mapToDriverDTO(DriverEntity driverEntity, String numberOfCar, String model) {
        return DriverDto.builder()
                .name(driverEntity.getName())
                .category(driverEntity.getCategory())
                .license(driverEntity.getLicense())
                .serial_license(driverEntity.getSerial_license())
                .number_license(driverEntity.getNumber_license())
                .build();
    }

    public void addDriversAndCars(DriverDto driverDTO, HttpServletRequest httpServletRequest) {

        Example<DriverEntity> example = Example.of(driverMapper.toDriverEntity(driverDTO));

        DriverEntity driverEntity;

        Optional<DriverEntity> driverEntityOptional = driverEntityRepo.findOne(example);

        if (driverEntityOptional.isPresent()) {

            driverEntity = driverEntityOptional.get();

            CarEntity carEntity = carMapper.toCarEntity(driverDTO.getCar());
            carEntity.setOwner(driverEntity);

            driverEntity.getCars().add(carEntity);

        } else {

            driverEntity = driverMapper.toDriverEntity(driverDTO);
            driverEntity.setCars(List.of(carMapper.toCarEntity(driverDTO.getCar())));
            driverEntity.getCars().get(0).setOwner(driverEntity);

        }

        driverEntityRepo.save(driverEntity);

    }

    //    @PostConstruct
    public void saveDriversAndCars() {
        driverEntityRepo.save(DriverEntity.builder()
                        .category("B")
                        .license(LocalDate.of(2016, 9, 16))
                        .name("Pasha Kalinchuk")
                        .cars(Collections.singletonList(CarEntity.builder()
                                        .model("Porsche")
                                        .numberOfCar("E666KH62")
                                .build()))
                .build());
    }

//    @PostConstruct
    public void test() {
        List<DriverEntity> driverEntity = driverEntityRepo.findAll();
        Optional<CarEntity> carEntity = carEntityRepo.findById(1L);
    }

}
