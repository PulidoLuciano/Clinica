package com.software.backend.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.backend.models.Diagnostico;
import com.software.backend.models.Evolucion;
import com.software.backend.models.HistoriaClinica;
import com.software.backend.models.Medico;
import com.software.backend.models.ObraSocial;
import com.software.backend.models.Paciente;
import com.software.backend.models.PedidoLaboratorio;
import com.software.backend.models.RecetaDigital;
import com.software.backend.persistence.repositories.interfaces.DiagnosticoRepository;
import com.software.backend.persistence.repositories.interfaces.MedicoRepository;
import com.software.backend.persistence.repositories.interfaces.ObraSocialRepository;
import com.software.backend.persistence.repositories.interfaces.PacienteRepository;
import com.software.backend.services.interfaces.PacienteService;

@Service
public class PacienteServiceImpl extends GenericServiceImpl<Paciente, Long, PacienteRepository> implements PacienteService{

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private ObraSocialRepository obraSocialRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Override
    public Paciente save(Paciente paciente){
        if(paciente.getObraSocial() != null){
            String nombreObraSocial = paciente.getObraSocial().getNombre();
            Optional<ObraSocial> obraSocialFromRepo = obraSocialRepository.findById(nombreObraSocial);
            if(obraSocialFromRepo.isEmpty()) throw new IllegalArgumentException("No existe la obra social con nombre " + nombreObraSocial);
            paciente.setObraSocial(obraSocialFromRepo.get());
        }
        return super.save(paciente);
    }

    @Override
    public Evolucion createEvolucionPaciente(Long cuilPaciente, Long cuilMedico, String nombreDiagnostico, String texto, RecetaDigital receta, PedidoLaboratorio pedidoLaboratorio) {
        Paciente paciente = verificarCuilPaciente(cuilPaciente);
        Diagnostico diagnostico = diagnosticoRepository.findById(nombreDiagnostico).orElseThrow(() -> new IllegalArgumentException("No existe un diagnostico con ese nombre en el sistema"));
        Medico medico = medicoRepository.findById(cuilMedico).orElseThrow(() -> new IllegalArgumentException("No existe un diagnostico con ese CUIL en el sistema"));
        return paciente.createEvolucion(medico, diagnostico, texto, receta, pedidoLaboratorio);
    }

    @Override
    public HistoriaClinica getHistoriaClinica(Long cuilPaciente) {
        Paciente paciente = verificarCuilPaciente(cuilPaciente);
        return paciente.getHistoriaClinica();
    }

    private Paciente verificarCuilPaciente(Long cuil){
        Optional<Paciente> paciente = super.getRepositorio().findById(cuil);
        if(paciente.isEmpty()) throw new IllegalArgumentException("No existe un paciente con ese cuil");
        return paciente.get();
    }

    @Override
    public List<RecetaDigital> getRecetas(Long cuil){
        Paciente paciente = verificarCuilPaciente(cuil);
        List<RecetaDigital> recetas = new ArrayList<>(); 
        paciente.getHistoriaClinica().getDetalles().forEach(detalle -> { 
            detalle.getEvoluciones().forEach(evolucion -> {recetas.add(evolucion.getReceta());} );
         });
        if(recetas.isEmpty()) throw new IllegalArgumentException("Esta historia clínica no tiene evoluciones con recetas digitales");  
         return recetas;
    }


    @Override
    public List<Diagnostico> getDiagnosticos(Long cuil){
        Paciente paciente = verificarCuilPaciente(cuil);
        List<Diagnostico> diagnosticos = new ArrayList<>(); 
        paciente.getHistoriaClinica().getDetalles().forEach(detalle -> { 
            diagnosticos.add(detalle.getDiagnostico());
         });
        if(diagnosticos.isEmpty()) throw new IllegalArgumentException("Esta historia clinica no tiene diagnosticos");  
         return diagnosticos;
    }

    @Override
    public List<PedidoLaboratorio> getPedidos(Long cuil){
        Paciente paciente = verificarCuilPaciente(cuil);
        List<PedidoLaboratorio> pedidosLaboratorio = new ArrayList<>();         
        paciente.getHistoriaClinica().getDetalles().forEach(detalle -> { 
            detalle.getEvoluciones().forEach(evolucion -> {pedidosLaboratorio.add(evolucion.getPedidoLaboratorio());} );
         });
        if(pedidosLaboratorio.isEmpty()) throw new IllegalArgumentException("Esta historia clinica no tiene evoluciones con pedidos de laboratorio");  
         return pedidosLaboratorio;
    }
}
