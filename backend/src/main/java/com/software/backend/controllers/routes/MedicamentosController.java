package com.software.backend.controllers.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.controllers.dtos.MedicamentoDTO;
import com.software.backend.controllers.dtos.mappers.MedicamentoMapper;
import com.software.backend.models.Medicamento;
import com.software.backend.services.interfaces.IMedicamentoService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentosController extends GenericController <Medicamento, Long, IMedicamentoService, MedicamentoDTO, MedicamentoMapper>{

}
