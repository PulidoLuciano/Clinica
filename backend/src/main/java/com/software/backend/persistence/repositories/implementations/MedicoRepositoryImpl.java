package com.software.backend.persistence.repositories.implementations;

import org.springframework.stereotype.Repository;

import com.software.backend.models.Medico;
import com.software.backend.persistence.base.BaseRepositoryImpl;
import com.software.backend.persistence.repositories.interfaces.MedicoRepository;

@Repository
public class MedicoRepositoryImpl extends BaseRepositoryImpl<Medico, Long> implements MedicoRepository{

}
