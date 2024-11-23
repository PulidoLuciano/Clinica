package com.software.backend.controllers.routes;

import org.springframework.web.bind.annotation.RestController;

import com.software.backend.controllers.dtos.CrearEvolucionDTO;
import com.software.backend.controllers.dtos.PacienteDTO;
import com.software.backend.controllers.dtos.mappers.PacienteMapper;
import com.software.backend.models.Evolucion;
import com.software.backend.models.ObraSocial;
import com.software.backend.models.Paciente;
import com.software.backend.persistence.repositories.interfaces.ObraSocialRepository;
import com.software.backend.services.interfaces.PacienteService;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/pacientes")
public class PacienteController extends GenericController<Paciente, Long, PacienteService, PacienteDTO, PacienteMapper>{

    @Autowired
    private ObraSocialRepository obraSocialRepository;

    @Override
    public ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO dto){
        String nombreObraSocial = dto.getObraSocial().getNombre();
        Optional<ObraSocial> obraSocialFromRepo = obraSocialRepository.findById(nombreObraSocial);
        if(obraSocialFromRepo.isEmpty()) throw new IllegalArgumentException("No existe la obra social con nombre " + nombreObraSocial);
        dto.setObraSocial(obraSocialFromRepo.get());
        return super.create(dto);
    }

    @PostMapping("/{cuilPaciente}/historia-clinica/{nombreDiagnostico}/evolucion")
    public ResponseEntity<Evolucion> createEvolucion(@Valid @RequestBody CrearEvolucionDTO dto, @PathVariable("cuilPaciente") Long cuilPaciente, @PathVariable("nombreDiagnostico") String nombreDiagnostico){
        Evolucion evolucion = super.getServicio().createEvolucionPaciente(cuilPaciente, dto.getCuilMedico(), nombreDiagnostico, dto.getTexto());
        return new ResponseEntity<>(evolucion, HttpStatus.CREATED);
    }
}
