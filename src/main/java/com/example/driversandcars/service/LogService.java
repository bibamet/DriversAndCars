package com.example.driversandcars.service;

import com.example.driversandcars.data.LogEntityRepo;
import com.example.driversandcars.entity.log.LogEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogEntityRepo logEntityRepo;

    @Transactional
    public LogEntity saveLog(LogEntity log) {

        return logEntityRepo.save(log);

    }

}
