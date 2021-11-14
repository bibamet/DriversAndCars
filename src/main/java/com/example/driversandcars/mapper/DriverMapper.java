package com.example.driversandcars.mapper;

import com.example.driversandcars.dto.DriverDto;
import com.example.driversandcars.entity.DriverEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DriverMapper {

    @Mapping(target = "car", ignore = true)
    DriverDto toDriverDto(DriverEntity driverEntity);

    DriverEntity toDriverEntity(DriverDto dto);

}
