package com.software.backend.controllers.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.software.backend.controllers.dtos.PacienteDTO;
import com.software.backend.controllers.dtos.mappers.PacienteMapper;
import com.software.backend.models.Paciente;
import com.software.backend.services.interfaces.IPacienteService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private PacienteMapper pacienteMapper;

    // Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        List<PacienteDTO> pacienteDTOs = pacienteMapper.toDTOList(pacientes);
        return ResponseEntity.ok(pacienteDTOs);
    }

    // Crear un nuevo paciente
    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        Paciente savedPaciente = pacienteService.savePaciente(paciente);
        PacienteDTO savedPacienteDTO = pacienteMapper.toDTO(savedPaciente);
        return new ResponseEntity<>(savedPacienteDTO, HttpStatus.CREATED);
    }

    // Obtener un paciente por su CUIL
    @GetMapping("/{cuil}")
    public ResponseEntity<PacienteDTO> getPacienteByCuil(@PathVariable("cuil") int cuil) {
        Optional<Paciente> pacienteOptional = pacienteService.getPacienteByCuil(cuil);
        if(pacienteOptional != null){
            Paciente paciente = pacienteOptional.get();
            return ResponseEntity.ok(pacienteMapper.toDTO(paciente));
        }
        else
            return null;
    }
}

