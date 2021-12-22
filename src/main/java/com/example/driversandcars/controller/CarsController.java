package com.example.driversandcars.controller;

import com.example.driversandcars.dto.CarDto;
import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.service.CarService;
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
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/cars") //todo rename
@RequiredArgsConstructor
public class CarsController {

    private final CarService carService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{carNumber}")
    public DriverDto getDriverByCarId(@Pattern(regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$", message = "" +
            "Должно соответветстовать формату гос. знака РФ. А123БВ32") @PathVariable String carNumber) {
        return carService.getDriverByCarId(carNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public CarDto addCar(@Valid @RequestBody CarDto car) {
        return carService.addCar(car);
    }
}
