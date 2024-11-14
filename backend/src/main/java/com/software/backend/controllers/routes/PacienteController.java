package com.software.backend.controllers.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.models.Paciente;
import com.software.backend.services.interfaces.IPacienteService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService servicio;

    @GetMapping("")
    public Iterable<Paciente> getAllPacientes() {
        return servicio.getAllPacientes();
    }
    
}
