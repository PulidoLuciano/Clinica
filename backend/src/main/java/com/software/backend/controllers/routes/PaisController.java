package com.software.backend.controllers.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.controllers.dtos.PaisDTO;
import com.software.backend.controllers.dtos.mappers.PaisMapper;
import com.software.backend.models.Pais;
import com.software.backend.services.interfaces.PaisService;


@RestController
@RequestMapping("/paises")
public class PaisController extends GenericController<Pais, String, PaisService, PaisDTO, PaisMapper> {

}
