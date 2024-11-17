package com.software.backend.services.interfaces;

import com.software.backend.models.HistoriaClinica;
import com.software.backend.models.Paciente;

public interface IPacienteService extends IGenericService<Paciente, Long>{

    public HistoriaClinica getHistoriaClinica(Long cuil);
}
