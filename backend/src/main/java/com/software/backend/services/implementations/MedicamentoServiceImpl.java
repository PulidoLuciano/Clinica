package com.software.backend.services.implementations;

import org.springframework.stereotype.Service;

import com.software.backend.models.Medicamento;
import com.software.backend.persistence.repositories.interfaces.MedicamentoRepository;
import com.software.backend.services.interfaces.MedicamentoService;

@Service
public class MedicamentoServiceImpl extends GenericServiceImpl<Medicamento, String, MedicamentoRepository> implements MedicamentoService {

}
