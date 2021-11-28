package com.example.driversandcars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDto {

    @NotBlank
    private String name;
    @NotBlank
    private String category;

    @Positive
    @Min(value = 1000, message = "Серия должна состоять из 4 цифр")
    @Max(value = 9999, message = "Серия должна состоять из 4 цифр")
    private Long serialLicense;

    @Positive
    @Min(value = 100000, message = "Номер должен состоять из 6 цифр")
    @Max(value = 999999, message = "Номер должен состоять из 6 цифр")
    private Long numberLicense;
    private LocalDate license; //rename

    @Valid
    private CarDto car;

}
