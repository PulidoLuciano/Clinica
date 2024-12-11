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
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.backend.models.PedidoLaboratorio;
import com.software.backend.models.RecetaDigital;
import com.software.backend.security.JwtTokenProvider;

@RestController
@RequestMapping("/pacientes")
public class PacienteController
        extends GenericController<Paciente, Long, PacienteService, PacienteDTO, PacienteMapper> {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping("/{cuilPaciente}/{nombreDiagnostico}/evoluciones")
    public ResponseEntity<List<Evolucion>> getEvolucionesPorDiagnostico(@PathVariable("cuilPaciente") long cuilPaciente, @PathVariable("nombreDiagnostico") String nombreDiagnostico){

        List<Evolucion> evoluciones = super.getServicio().getEvolucionesPorDiagnostico(cuilPaciente,nombreDiagnostico);
        return ResponseEntity.ok(evoluciones);
    }

    @PostMapping("/{cuilPaciente}/historia-clinica/{nombreDiagnostico}/evolucion")
    public ResponseEntity<Evolucion> createEvolucion(@Valid @RequestBody CrearEvolucionDTO dto,
            @PathVariable("cuilPaciente") Long cuilPaciente,
            @PathVariable("nombreDiagnostico") String nombreDiagnostico) {
        
        if(dto.getMedicamentosReceta() != null && dto.getTextoPedidoLaboratorio() != null)
            throw new IllegalArgumentException("El pedido de laboratorio y la receta digital son excluyentes en una evolución");
        
        Long cuilMedico = getLoggedCuil();
        Evolucion evolucion;

        if(dto.getMedicamentosReceta() != null){
            List<Map<String, Integer>> medicamentosRecetados = dto.getMedicamentosReceta().stream()
                .map(detalle -> Map.of(
                        "codigoMedicamento", detalle.getCodigoMedicamento(),
                        "cantidad", detalle.getCantidad()))
                .collect(Collectors.toList());
            evolucion = super.getServicio().createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, dto.getTexto(), medicamentosRecetados);
        }else if(dto.getTextoPedidoLaboratorio() != null){
            String textoPedido = dto.getTextoPedidoLaboratorio();
            evolucion = super.getServicio().createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, dto.getTexto(), textoPedido);
        }else{
            evolucion = super.getServicio().createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, dto.getTexto());
        }
        return new ResponseEntity<>(evolucion, HttpStatus.CREATED);
    }

    @GetMapping("/{cuilPaciente}/historia-clinica")
    public ResponseEntity<HistoriaClinica> getHistoriaClinica(@PathVariable("cuilPaciente") Long cuilPaciente) {
        HistoriaClinica historia = super.getServicio().getHistoriaClinica(cuilPaciente);
        return ResponseEntity.ok(historia);
    }

    @GetMapping("/{cuilPaciente}/historia-clinica/recetas")
    public ResponseEntity<List<RecetaDigital>> getRecetasPaciente(@PathVariable("cuilPaciente") Long cuilPaciente) {
        List<RecetaDigital> recetas = super.getServicio().getRecetas(cuilPaciente);
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{cuilPaciente}/historia-clinica/diagnosticos")
    public ResponseEntity<List<Diagnostico>> getDiagnosticosPaciente(@PathVariable("cuilPaciente") Long cuilPaciente) {
        List<Diagnostico> diagnosticos = super.getServicio().getDiagnosticos(cuilPaciente);
        return ResponseEntity.ok(diagnosticos);
    }

    @GetMapping("/{cuilPaciente}/historia-clinica/pedidos")
    public ResponseEntity<List<PedidoLaboratorio>> getPedidosPaciente(@PathVariable("cuilPaciente") Long cuilPaciente) {
        List<PedidoLaboratorio> pedidosLaboratorio = super.getServicio().getPedidos(cuilPaciente);
        return ResponseEntity.ok(pedidosLaboratorio);
    }

    @GetMapping("/{cuilPaciente}/historia-clinica/evoluciones")
    public ResponseEntity<List<Evolucion>> getEvolucionesPaciente(@PathVariable("cuilPaciente") Long cuilPaciente) {
        List<Evolucion> evoluciones = super.getServicio().getEvolucionesPaciente(cuilPaciente);
        return ResponseEntity.ok(evoluciones);
    }

    public Long getLoggedCuil() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return null;
        String credentials = authentication.getCredentials().toString();
        Long cuil = tokenProvider.getCuil(credentials);

        return cuil;
    }

}
