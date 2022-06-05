package com.example.driversandcars.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessageDtoConsumer {

    private String method;
    private Long executionTime;
    private String result;
    private String exception;
    private String clientHost;

}
