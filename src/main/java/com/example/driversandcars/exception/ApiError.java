package com.example.driversandcars.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

    private HttpStatus status;
    private String message;
    private ApiErrorType type;

}
