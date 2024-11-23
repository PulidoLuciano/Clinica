package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import com.software.backend.controllers.dtos.PaisDTO;
import com.software.backend.models.Pais;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaisMapper extends GenericMapper<Pais, PaisDTO>{

}
