package com.example.driversandcars.controller;

import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.service.DriversAndCarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/driversandcars")
@RequiredArgsConstructor
public class DriversAndCarsController {

    private final DriversAndCarsService driversAndCarsService;

    @GetMapping("/driverid/{numberOfCar}")
    public ResponseEntity<DriverDto> getDriverByCarId(@PathVariable String numberOfCar, HttpServletRequest httpServletRequest) {

        DriverDto driverDTO = driversAndCarsService.getDriverByCarId(numberOfCar, httpServletRequest);

        return new ResponseEntity<>(driverDTO, HttpStatus.OK);

//        return carEntity.map(entity -> new ResponseEntity<>(entity.getOwner(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new DriverEntity(), HttpStatus.NOT_FOUND));

    }

    @PostMapping("/add")
    public void addDriversAndCars(@RequestBody DriverDto driverDTO, HttpServletRequest httpServletRequest) {

        driversAndCarsService.addDriversAndCars(driverDTO, httpServletRequest);

    }

}
