package com.example.driversandcars.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

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
    private Long serial_license;

    @Positive
    @Min(value = 100000, message = "Номер должен состоять из 6 цифр")
    @Max(value = 999999, message = "Номер должен состоять из 6 цифр")
    private Long number_license;
    private LocalDate license;

    @Valid
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CarDto car;

}
