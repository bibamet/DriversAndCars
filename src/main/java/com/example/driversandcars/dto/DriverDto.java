package com.example.driversandcars.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverDto {

    private String name;
    private String category;
    private Long serial_license;
    private Long number_license;
    private LocalDate license;
    private CarDto car;
//    private String numberOfCar;
//    private String model;

}
