package com.example.driversandcars.controller;

import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

//todo add checkstyle

@Validated
@RestController
@RequestMapping("/drivers") //todo rename
@RequiredArgsConstructor
public class DriversController {

    private final DriverService driverService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/car/{carNumber}")
    public DriverDto getDriverByCarId(@Pattern(regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$", message = "" +
            "Должно соответветстовать формату гос. знака РФ. А123БВ32") @PathVariable String carNumber) {
        return driverService.getDriverByCarId(carNumber);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public DriverDto addDriver(@Valid @RequestBody DriverDto driverDTO) {
        return driverService.addDriver(driverDTO);
    }
}
