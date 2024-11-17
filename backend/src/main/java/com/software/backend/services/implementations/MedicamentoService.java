package com.software.backend.services.implementations;

import org.springframework.stereotype.Service;

import com.software.backend.models.Medicamento;
import com.software.backend.persistence.repositories.IMedicamentoRepository;
import com.software.backend.services.interfaces.IMedicamentoService;

@Service
public class MedicamentoService extends GenericService<Medicamento, Long, IMedicamentoRepository> implements IMedicamentoService {

}
