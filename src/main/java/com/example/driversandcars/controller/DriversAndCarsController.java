package com.example.driversandcars.controller;

import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.service.DriversAndCarsService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/driversandcars")
@RequiredArgsConstructor
public class DriversAndCarsController {

    private final DriversAndCarsService driversAndCarsService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/driverid/{numberOfCar}")
    public DriverDto getDriverByCarId(@PathVariable String numberOfCar) {

        return driversAndCarsService.getDriverByCarId(numberOfCar);

//        return carEntity.map(entity -> new ResponseEntity<>(entity.getOwner(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new DriverEntity(), HttpStatus.NOT_FOUND));

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public Boolean addDriversAndCars(@Valid @RequestBody DriverDto driverDTO) {

        return driversAndCarsService.addDriversAndCars(driverDTO);

    }

    public void testMethod() {
        System.out.println("Привет");
    }

}
