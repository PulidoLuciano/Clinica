package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.ProvinciaDTO;
import com.software.backend.models.Provincia;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProvinciaMapper extends GenericMapper<Provincia, ProvinciaDTO>{}
