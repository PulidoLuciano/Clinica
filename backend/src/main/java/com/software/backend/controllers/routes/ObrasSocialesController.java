package com.software.backend.controllers.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.controllers.dtos.ObraSocialDTO;
import com.software.backend.controllers.dtos.mappers.ObraSocialMapper;
import com.software.backend.models.ObraSocial;
import com.software.backend.services.interfaces.IObraSocialService;

@RestController
@RequestMapping("/obrasSociales")
public class ObrasSocialesController extends GenericController<ObraSocial, Long, IObraSocialService, ObraSocialDTO, ObraSocialMapper>{
}
