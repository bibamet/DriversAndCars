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

//todo add checkstyle

@RestController
@RequestMapping("/drivers") //todo rename
@RequiredArgsConstructor
public class DriversAndCarsController {

    private final DriversAndCarsService driversAndCarsService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/car/{carNumber}")
    public DriverDto getDriverByCarId(@PathVariable String carNumber) { //add validation
        //remove wsp
        return driversAndCarsService.getDriverByCarId(carNumber);

        //delete comments
//        return carEntity.map(entity -> new ResponseEntity<>(entity.getOwner(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new DriverEntity(), HttpStatus.NOT_FOUND));

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add") // remove signature
    public void addDriversAndCars(@Valid @RequestBody DriverDto driverDTO) {
        //remove wsp
        driversAndCarsService.addDriversAndCars(driverDTO);

    }

    public void testMethod() {
        System.out.println("Привет"); //remove
    }
}
