package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.EvolucionDTO;
import com.software.backend.models.Evolucion;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EvolucionMapper extends GenericMapper<Evolucion, EvolucionDTO>{

}
