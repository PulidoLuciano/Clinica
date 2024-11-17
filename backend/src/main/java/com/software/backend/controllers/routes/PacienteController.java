package com.software.backend.controllers.routes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.software.backend.controllers.dtos.DetalleDiagnosticoDTO;
import com.software.backend.controllers.dtos.HistoriaClinicaDTO;
import com.software.backend.controllers.dtos.PacienteDTO;
import com.software.backend.controllers.dtos.mappers.DetalleDiagnosticoMapper;
import com.software.backend.controllers.dtos.mappers.HistoriaClinicaMapper;
import com.software.backend.controllers.dtos.mappers.PacienteMapper;
import com.software.backend.models.Diagnostico;
import com.software.backend.models.Paciente;
import com.software.backend.services.interfaces.IPacienteService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/pacientes")
public class PacienteController extends GenericController<Paciente, Long, IPacienteService, PacienteDTO, PacienteMapper>{

    @Autowired
    private HistoriaClinicaMapper historiaMapper;
    @Autowired
    private DetalleDiagnosticoMapper detallesDiagnosticosMapper;
    
    @GetMapping("/{cuil}/historia")
    public ResponseEntity<HistoriaClinicaDTO> getHistoriaClinica(@PathVariable("cuil") Long cuil) {
        HistoriaClinicaDTO historiaDto = historiaMapper.toDTO(super.getServicio().getHistoriaClinica(cuil));
        return ResponseEntity.ok(historiaDto);
    }

    @GetMapping("/{cuil}/historia/diagnosticos")
    public ResponseEntity<List<Diagnostico>> getDiagnosticos(@PathVariable("cuil") Long cuil) {
        List<DetalleDiagnosticoDTO> detallesDto = detallesDiagnosticosMapper.toDTOList(super.getServicio().getHistoriaClinica(cuil).getDetalles());
        List<Diagnostico> diagnosticosUsados = detallesDto.stream().map(detalle -> detalle.getDiagnostico()).toList();
        return ResponseEntity.ok(diagnosticosUsados);
    }

    @PostMapping("/{cuil}/historia/diagnosticos/{diagnostico}/evolucion")
    public String createEvolucion(@PathVariable("cuil") Long cuil, @PathVariable("diagnostico") String nameDiagnostico) {
        return nameDiagnostico;
    }
    
    
}

