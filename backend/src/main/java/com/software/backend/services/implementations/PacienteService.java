package com.software.backend.services.implementations;

import org.springframework.stereotype.Service;
import com.software.backend.models.Paciente;
import com.software.backend.persistence.repositories.IPacienteRepository;
import com.software.backend.services.interfaces.IPacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService{

    @Autowired
    private IPacienteRepository pacienteRepository;

    // Obtener todos los pacientes
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    // Crear un nuevo paciente
    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // Obtener un paciente por su CUIL
    public Optional<Paciente> getPacienteByCuil(int cuil) {
        return pacienteRepository.findById(cuil);
    }
}


