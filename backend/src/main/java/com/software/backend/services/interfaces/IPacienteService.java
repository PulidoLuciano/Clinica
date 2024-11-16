package com.software.backend.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.software.backend.models.Paciente;

public interface IPacienteService {

    public List<Paciente> getAllPacientes();
    public Paciente savePaciente(Paciente paciente);
    public Optional<Paciente> getPacienteByCuil(int cuil);
}
