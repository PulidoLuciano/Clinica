package com.software.backend.services.implementations;

import org.springframework.stereotype.Service;
import com.software.backend.models.Paciente;
import com.software.backend.persistence.repositories.IPacienteRepository;
import com.software.backend.services.interfaces.IPacienteService;

@Service
public class PacienteService extends GenericService<Paciente, Long, IPacienteRepository> implements IPacienteService{
}


