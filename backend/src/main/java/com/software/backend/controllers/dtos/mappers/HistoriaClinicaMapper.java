package com.software.backend.controllers.dtos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.software.backend.controllers.dtos.HistoriaClinicaDTO;
import com.software.backend.models.HistoriaClinica;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HistoriaClinicaMapper extends GenericMapper<HistoriaClinica, HistoriaClinicaDTO>{
}
