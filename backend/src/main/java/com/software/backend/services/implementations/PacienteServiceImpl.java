package com.software.backend.services.implementations;

import org.springframework.stereotype.Service;

import com.software.backend.models.Paciente;
import com.software.backend.persistence.repositories.interfaces.PacienteRepository;
import com.software.backend.services.interfaces.PacienteService;

@Service
public class PacienteServiceImpl extends GenericServiceImpl<Paciente, Long, PacienteRepository> implements PacienteService{

}
