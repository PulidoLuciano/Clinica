package com.software.backend.services.implementations;

import org.springframework.stereotype.Service;

import com.software.backend.models.Diagnostico;
import com.software.backend.persistence.repositories.interfaces.DiagnosticoRepository;
import com.software.backend.services.interfaces.DiagnosticoService;

@Service
public class DiagnosticoServiceImpl extends GenericServiceImpl<Diagnostico, String, DiagnosticoRepository> implements DiagnosticoService{

}
