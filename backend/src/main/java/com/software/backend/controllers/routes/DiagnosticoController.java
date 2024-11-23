package com.software.backend.controllers.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.controllers.dtos.DiagnosticoDTO;
import com.software.backend.controllers.dtos.mappers.DiagnosticoMapper;
import com.software.backend.models.Diagnostico;
import com.software.backend.services.interfaces.DiagnosticoService;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController extends GenericController<Diagnostico, String, DiagnosticoService, DiagnosticoDTO, DiagnosticoMapper>{
}
