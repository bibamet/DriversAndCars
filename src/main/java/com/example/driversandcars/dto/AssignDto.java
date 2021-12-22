package com.example.driversandcars.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignDto {

    @Positive
    @Min(value = 1000, message = "Серия должна состоять из 4 цифр")
    @Max(value = 9999, message = "Серия должна состоять из 4 цифр")
    private Long serialLicense;

    @Positive
    @Min(value = 100000, message = "Номер должен состоять из 6 цифр")
    @Max(value = 999999, message = "Номер должен состоять из 6 цифр")
    private Long numberLicense;

    @Pattern(regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$", message = "" +
            "Должно соответветстовать формату гос. знака РФ. А123БВ32")
    @NotBlank
    private String numberOfCar;

}
