package com.software.backend.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.software.backend.models.HistoriaClinica;
import com.software.backend.models.Paciente;
import com.software.backend.persistence.repositories.IPacienteRepository;
import com.software.backend.services.interfaces.IPacienteService;

@Service
public class PacienteService extends GenericService<Paciente, Long, IPacienteRepository> implements IPacienteService{

    @Override
    public Paciente save(Paciente paciente){
        if(super.getRepositorio().existsById(paciente.getCuil()))
            return null;
        return super.save(paciente);
    }

    @Override
    public HistoriaClinica getHistoriaClinica(Long cuil) {
        Optional<Paciente> paciente = super.getRepositorio().findById(cuil);
        if(!paciente.isPresent())
            throw new RuntimeException("El paciente no existe");
        return paciente.get().getHistoriaClinica();
    }
}


