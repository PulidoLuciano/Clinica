package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.RecetaDigitalDTO;
import com.software.backend.models.RecetaDigital;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecetaDigitalMapper extends GenericMapper<RecetaDigital, RecetaDigitalDTO>{

}
