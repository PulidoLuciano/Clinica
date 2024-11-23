package com.software.backend.persistence.repositories.implementations;

import org.springframework.stereotype.Repository;

import com.software.backend.models.Medicamento;
import com.software.backend.persistence.base.BaseRepositoryImpl;
import com.software.backend.persistence.repositories.interfaces.MedicamentoRepository;

@Repository
public class MedicamentoRepositoryImpl extends BaseRepositoryImpl<Medicamento, String> implements MedicamentoRepository {

}
