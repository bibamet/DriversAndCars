package com.example.driversandcars.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessageDto {

    private String method;
    private Long executionTime;
    private String result;
    private String exception;
    private String clientHost;

}
