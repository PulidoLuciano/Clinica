package com.software.backend.controllers.dtos.mappers;

import com.software.backend.controllers.dtos.PedidoLaboratorioDTO;
import com.software.backend.models.PedidoLaboratorio;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PedidoLaboratorioMapper extends GenericMapper<PedidoLaboratorio, PedidoLaboratorioDTO> {

}
