package com.example.driversandcars.data;

import com.example.driversandcars.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverEntityRepo extends JpaRepository<DriverEntity, Long> {
//    Optional<DriverEntity> findBySerial_licenseAndNumber_license(Long serial_license, Long number_license);
}
