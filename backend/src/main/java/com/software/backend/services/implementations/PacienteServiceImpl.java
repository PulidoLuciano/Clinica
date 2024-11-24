package com.software.backend.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.backend.models.Diagnostico;
import com.software.backend.models.Evolucion;
import com.software.backend.models.Medico;
import com.software.backend.models.ObraSocial;
import com.software.backend.models.Paciente;
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
    private MedicoRepository medicoRepository;
    @Autowired
    private ObraSocialRepository obraSocialRepository;
    
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
    public Evolucion createEvolucionPaciente(Long cuilPaciente, Long cuilMedico, String nombreDiagnostico, String texto) {
        Optional<Paciente> paciente = super.getRepositorio().findById(cuilPaciente);
        if(paciente.isEmpty()) throw new IllegalArgumentException("No existe un paciente con ese cuil");
        Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(nombreDiagnostico);
        if(diagnostico.isEmpty()) throw new IllegalArgumentException("No existe un diagnostico con ese nombre en el sistema");
        Optional<Medico> medico = medicoRepository.findById(cuilMedico);
        if(medico.isEmpty()) throw new IllegalArgumentException("No existe un médico con ese cuil en el sistema");
        return paciente.get().createEvolucion(medico.get(), diagnostico.get(), texto);
    }
    
}
