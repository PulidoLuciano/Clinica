package com.software.backend.controllers.routes;

import org.springframework.web.bind.annotation.RestController;

import com.software.backend.controllers.dtos.CrearEvolucionDTO;
import com.software.backend.controllers.dtos.PacienteDTO;
import com.software.backend.controllers.dtos.mappers.PacienteMapper;
import com.software.backend.models.Diagnostico;
import com.software.backend.models.Evolucion;
import com.software.backend.models.HistoriaClinica;
import com.software.backend.models.Paciente;
import com.software.backend.services.interfaces.PacienteService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.backend.models.PedidoLaboratorio;
import com.software.backend.models.RecetaDigital;

@RestController
@RequestMapping("/pacientes")
public class PacienteController extends GenericController<Paciente, Long, PacienteService, PacienteDTO, PacienteMapper>{
    
    @PostMapping("/{cuilPaciente}/historia-clinica/{nombreDiagnostico}/evolucion")
    public ResponseEntity<Evolucion> createEvolucion(@Valid @RequestBody CrearEvolucionDTO dto, @PathVariable("cuilPaciente") Long cuilPaciente, @PathVariable("nombreDiagnostico") String nombreDiagnostico){
        Evolucion evolucion = super.getServicio().createEvolucionPaciente(cuilPaciente, dto.getCuilMedico(), nombreDiagnostico, dto.getTexto(),dto.getReceta(),dto.getPedidoLaboratorio());
        return new ResponseEntity<>(evolucion, HttpStatus.CREATED);
    }

    @GetMapping("/{cuilPaciente}/historia-clinica")
    public ResponseEntity<HistoriaClinica> getHistoriaClinica(@PathVariable("cuilPaciente") Long cuilPaciente){
        HistoriaClinica historia = super.getServicio().getHistoriaClinica(cuilPaciente);
        return ResponseEntity.ok(historia);
    }

    @GetMapping("/{cuilPaciente}/historia-clinica/recetas")
    public ResponseEntity<List<RecetaDigital>> getRecetasPaciente(@PathVariable("cuilPaciente") Long cuilPaciente){
        List<RecetaDigital> recetas = super.getServicio().getRecetas(cuilPaciente);
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{cuilPaciente}/historia-clinica/diagnosticos")
    public ResponseEntity<List<Diagnostico>> getDiagnosticosPaciente(@PathVariable("cuilPaciente") Long cuilPaciente){
        List<Diagnostico> diagnosticos = super.getServicio().getDiagnosticos(cuilPaciente);
        return ResponseEntity.ok(diagnosticos);
    }

    @GetMapping("/{cuilPaciente}/historia-clinica/pedidos")
    public ResponseEntity<List<PedidoLaboratorio>> getPedidosPaciente(@PathVariable("cuilPaciente") Long cuilPaciente){
        List<PedidoLaboratorio> pedidosLaboratorio = super.getServicio().getPedidos(cuilPaciente);
        return ResponseEntity.ok(pedidosLaboratorio);
    }


}
