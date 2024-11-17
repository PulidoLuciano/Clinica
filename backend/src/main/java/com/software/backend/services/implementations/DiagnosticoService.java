package com.software.backend.services.implementations;

import org.springframework.stereotype.Service;

import com.software.backend.models.Diagnostico;
import com.software.backend.persistence.repositories.IDiagnosticoRepository;
import com.software.backend.services.interfaces.IDiagnosticoService;

@Service
public class DiagnosticoService extends GenericService<Diagnostico, Long, IDiagnosticoRepository> implements IDiagnosticoService{
}
