package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.LocalidadDTO;
import com.software.backend.models.Localidad;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocalidadMapper extends GenericMapper<Localidad, LocalidadDTO>{}
