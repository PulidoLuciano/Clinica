package com.software.backend.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.backend.models.Paciente;
import com.software.backend.persistence.repositories.IPacienteRepository;
import com.software.backend.services.interfaces.IPacienteService;

@Service
public class PacienteService implements IPacienteService{

    @Autowired
    private IPacienteRepository repositorio;

    @Override
    public Iterable<Paciente> getAllPacientes() {
        return repositorio.findAll();
    }


}
