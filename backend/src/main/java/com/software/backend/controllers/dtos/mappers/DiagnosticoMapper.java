package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.DiagnosticoDTO;
import com.software.backend.models.Diagnostico;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DiagnosticoMapper extends GenericMapper<Diagnostico, DiagnosticoDTO>{

}
