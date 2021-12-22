package com.example.driversandcars.mapper;

import com.example.driversandcars.dto.CarDto;
import com.example.driversandcars.entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface CarMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    CarEntity toCarEntity(CarDto dto);

    CarDto toCarDto(CarEntity carEntity);

}
