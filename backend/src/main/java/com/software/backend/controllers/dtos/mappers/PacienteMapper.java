package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.PacienteDTO;
import com.software.backend.models.Paciente;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PacienteMapper extends GenericMapper<Paciente, PacienteDTO>{
}
