package com.software.backend.controllers.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.controllers.dtos.MedicamentoDTO;
import com.software.backend.controllers.dtos.mappers.MedicamentoMapper;
import com.software.backend.models.Medicamento;
import com.software.backend.services.interfaces.MedicamentoService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController extends GenericController<Medicamento, String, MedicamentoService, MedicamentoDTO, MedicamentoMapper> {

}
