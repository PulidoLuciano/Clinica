package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.ObraSocialDTO;
import com.software.backend.models.ObraSocial;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ObraSocialMapper extends GenericMapper<ObraSocial, ObraSocialDTO> {}
