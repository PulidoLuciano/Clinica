package com.software.backend.services.implementations;

import org.springframework.stereotype.Service;

import com.software.backend.models.Medico;
import com.software.backend.persistence.repositories.interfaces.MedicoRepository;
import com.software.backend.services.interfaces.MedicoService;

@Service
public class MedicoServiceImpl extends GenericServiceImpl<Medico, Long, MedicoRepository> implements MedicoService{

}
