package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.DetalleDiagnosticoDTO;
import com.software.backend.models.DetalleDiagnostico;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DetalleDiagnosticoMapper extends GenericMapper<DetalleDiagnostico, DetalleDiagnosticoDTO>{
}
