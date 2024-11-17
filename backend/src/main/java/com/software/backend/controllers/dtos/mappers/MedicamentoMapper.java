package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.MedicamentoDTO;
import com.software.backend.models.Medicamento;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicamentoMapper extends GenericMapper<Medicamento, MedicamentoDTO>{}
