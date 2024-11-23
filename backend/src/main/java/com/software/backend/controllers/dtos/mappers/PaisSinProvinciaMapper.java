package com.software.backend.controllers.dtos.mappers;

import com.software.backend.controllers.dtos.PaisSinProvinciaDTO;
import com.software.backend.models.Pais;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaisSinProvinciaMapper extends GenericMapper<Pais, PaisSinProvinciaDTO> {

}
