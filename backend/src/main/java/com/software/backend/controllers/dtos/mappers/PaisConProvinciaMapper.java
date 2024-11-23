package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import com.software.backend.controllers.dtos.PaisConProvinciaDTO;
import com.software.backend.models.Pais;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaisConProvinciaMapper extends GenericMapper<Pais, PaisConProvinciaDTO>{

}
