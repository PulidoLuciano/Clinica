package com.software.backend.services.interfaces;

import com.software.backend.models.Paciente;

public interface IPacienteService {

    public Iterable<Paciente> getAllPacientes();
}
