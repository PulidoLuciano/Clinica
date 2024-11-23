package com.software.backend.persistence.repositories.implementations;

import org.springframework.stereotype.Repository;

import com.software.backend.models.Paciente;
import com.software.backend.persistence.base.BaseRepositoryImpl;
import com.software.backend.persistence.repositories.interfaces.PacienteRepository;

@Repository
public class PacienteRepositoryImpl extends BaseRepositoryImpl<Paciente, Long> implements PacienteRepository{

}
