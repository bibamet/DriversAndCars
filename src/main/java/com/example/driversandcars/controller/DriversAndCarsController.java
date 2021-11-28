package com.example.driversandcars.controller;

import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.service.DriversAndCarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//todo add checkstyle

@RestController
@RequestMapping("/drivers") //todo rename
@RequiredArgsConstructor
public class DriversAndCarsController {

    private final DriversAndCarsService driversAndCarsService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/car/{carNumber}")
    public DriverDto getDriverByCarId(@PathVariable String carNumber) {
        return driversAndCarsService.getDriverByCarId(carNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add") // remove signature
    public void addDriversAndCars(@Valid @RequestBody DriverDto driverDTO) {
        driversAndCarsService.addDriversAndCars(driverDTO);
    }
}
