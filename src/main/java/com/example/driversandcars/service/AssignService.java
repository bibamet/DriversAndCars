package com.example.driversandcars.service;

import com.example.driversandcars.data.CarEntityRepo;
import com.example.driversandcars.data.DriverEntityRepo;
import com.example.driversandcars.dto.AssignDto;
import com.example.driversandcars.entity.CarEntity;
import com.example.driversandcars.entity.DriverEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignService {

    private final CarEntityRepo carEntityRepo;
    private final DriverEntityRepo driverEntityRepo;

    @Transactional
    public void assignEntities(AssignDto assignDto) {
        DriverEntity driverEntity = driverEntityRepo.findBySerialLicenseAndNumberLicense(assignDto.getSerialLicense(), assignDto.getNumberLicense())
                .orElseThrow(() -> new EntityNotFoundException("Водитель не найден"));
        CarEntity carEntity = carEntityRepo.findByNumberOfCar(assignDto.getNumberOfCar()).orElseThrow(() -> new EntityNotFoundException("Машина не найдена"));
        carEntity.setOwner(driverEntity);
        carEntityRepo.save(carEntity);
    }

}
