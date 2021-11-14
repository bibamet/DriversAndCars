package com.example.driversandcars.data;

import com.example.driversandcars.entity.log.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogEntityRepo extends JpaRepository<LogEntity, UUID> {
}
