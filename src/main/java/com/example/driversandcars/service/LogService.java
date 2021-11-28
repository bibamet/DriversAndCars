package com.example.driversandcars.service;

import com.example.driversandcars.data.LogEntityRepo;
import com.example.driversandcars.entity.log.LogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogEntityRepo logEntityRepo;

    public LogEntity saveLog(LogEntity log) {

        return logEntityRepo.save(log);

    }

}
