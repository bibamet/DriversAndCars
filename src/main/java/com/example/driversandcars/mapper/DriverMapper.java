package com.example.driversandcars.mapper;

import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.entity.DriverEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DriverMapper {

    DriverDto toDriverDto(DriverEntity driverEntity);

    DriverEntity toDriverEntity(DriverDto dto);

}
