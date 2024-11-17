package com.software.backend.controllers.routes;

import org.springframework.web.bind.annotation.*;

import com.software.backend.controllers.dtos.PacienteDTO;
import com.software.backend.controllers.dtos.mappers.PacienteMapper;
import com.software.backend.models.Paciente;
import com.software.backend.services.interfaces.IPacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController extends GenericController<Paciente, Long, IPacienteService, PacienteDTO, PacienteMapper>{
}

