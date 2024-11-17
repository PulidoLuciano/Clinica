package com.software.backend.controllers.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.software.backend.controllers.dtos.HistoriaClinicaDTO;
import com.software.backend.controllers.dtos.PacienteDTO;
import com.software.backend.controllers.dtos.mappers.HistoriaClinicaMapper;
import com.software.backend.controllers.dtos.mappers.PacienteMapper;
import com.software.backend.models.Paciente;
import com.software.backend.services.interfaces.IPacienteService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/pacientes")
public class PacienteController extends GenericController<Paciente, Long, IPacienteService, PacienteDTO, PacienteMapper>{

    @Autowired
    private HistoriaClinicaMapper historiaMapper;
    
    @GetMapping("/{cuil}/historia")
    public ResponseEntity<HistoriaClinicaDTO> getHistoriaClinica(@PathVariable("cuil") Long cuil) {
        HistoriaClinicaDTO historiaDto = historiaMapper.toDTO(super.getServicio().getHistoriaClinica(cuil));
        return ResponseEntity.ok(historiaDto);
    }
}

