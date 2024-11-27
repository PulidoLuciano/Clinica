package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.MedicoDTO;
import com.software.backend.models.Medico;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicoMapper extends GenericMapper<Medico, MedicoDTO>{

}
