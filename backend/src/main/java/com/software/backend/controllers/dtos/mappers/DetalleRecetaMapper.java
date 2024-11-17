package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.DetalleRecetaDTO;
import com.software.backend.models.DetalleReceta;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DetalleRecetaMapper extends GenericMapper<DetalleReceta, DetalleRecetaDTO>{

}
