package com.software.backend.controllers.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.controllers.dtos.MedicoDTO;
import com.software.backend.controllers.dtos.mappers.MedicoMapper;
import com.software.backend.models.Medico;
import com.software.backend.services.interfaces.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController extends GenericController<Medico, Long, MedicoService, MedicoDTO, MedicoMapper>{

}
