package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.DireccionDTO;
import com.software.backend.models.Direccion;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DireccionMapper extends GenericMapper<Direccion, DireccionDTO>{}
