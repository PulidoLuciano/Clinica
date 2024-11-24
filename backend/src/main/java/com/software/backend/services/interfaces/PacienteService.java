package com.software.backend.services.interfaces;

import com.software.backend.models.Evolucion;
import com.software.backend.models.HistoriaClinica;
import com.software.backend.models.Paciente;

public interface PacienteService extends GenericService<Paciente, Long>{
    public Evolucion createEvolucionPaciente(Long cuilPaciente, Long cuilMedico, String nombreDiagnostico, String texto);

    public HistoriaClinica getHistoriaClinica(Long cuilPaciente);
}
