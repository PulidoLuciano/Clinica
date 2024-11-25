package com.software.backend.services.interfaces;

import java.util.List;

import com.software.backend.models.Diagnostico;
import com.software.backend.models.Evolucion;
import com.software.backend.models.HistoriaClinica;
import com.software.backend.models.Paciente;
import com.software.backend.models.PedidoLaboratorio;
import com.software.backend.models.RecetaDigital;

public interface PacienteService extends GenericService<Paciente, Long> {
    public Evolucion createEvolucionPaciente(Long cuilPaciente, Long cuilMedico, String nombreDiagnostico, String texto,RecetaDigital receta, PedidoLaboratorio pedidoLaboratorio) ;

    public HistoriaClinica getHistoriaClinica(Long cuilPaciente);

    public List<RecetaDigital> getRecetas(Long cuil);

    public List<Diagnostico> getDiagnosticos(Long cuil);

    public List<PedidoLaboratorio> getPedidos(Long cuil);
}
