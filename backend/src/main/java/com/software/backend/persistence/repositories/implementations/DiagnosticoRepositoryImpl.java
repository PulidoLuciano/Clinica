package com.software.backend.persistence.repositories.implementations;

import org.springframework.stereotype.Repository;

import com.software.backend.models.Diagnostico;
import com.software.backend.persistence.base.BaseRepositoryImpl;
import com.software.backend.persistence.repositories.interfaces.DiagnosticoRepository;

@Repository
public class DiagnosticoRepositoryImpl extends BaseRepositoryImpl<Diagnostico, String> implements DiagnosticoRepository{

}
