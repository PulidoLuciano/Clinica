package com.software.backend.services.implementations;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.backend.models.DetalleReceta;
import com.software.backend.models.Diagnostico;
import com.software.backend.models.Evolucion;
import com.software.backend.models.HistoriaClinica;
import com.software.backend.models.Medico;
import com.software.backend.models.ObraSocial;
import com.software.backend.models.Paciente;
import com.software.backend.models.PedidoLaboratorio;
import com.software.backend.models.RecetaDigital;
import com.software.backend.persistence.repositories.interfaces.ApiSalud;
import com.software.backend.persistence.repositories.interfaces.DiagnosticoRepository;
import com.software.backend.persistence.repositories.interfaces.MedicoRepository;
import com.software.backend.persistence.repositories.interfaces.PacienteRepository;
import com.software.backend.services.interfaces.PacienteService;

@Service
public class PacienteServiceImpl extends GenericServiceImpl<Paciente, Long, PacienteRepository> implements PacienteService{

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ApiSalud apiSalud;
    
    @Override
    public Paciente save(Paciente paciente){
        if(paciente.getObraSocial() != null){
            ObraSocial obraSocial = apiSalud.getObraSocialbyCode(paciente.getObraSocial().getCodigo());
            paciente.setObraSocial(obraSocial);
        }
        return super.save(paciente);
    }

    @Override
    public Evolucion createEvolucion(Long cuilPaciente, Long cuilMedico, String nombreDiagnostico, String texto) {
        Paciente paciente = verificarCuilPaciente(cuilPaciente);
        Diagnostico diagnostico = diagnosticoRepository.findById(nombreDiagnostico).orElseThrow(() -> new IllegalArgumentException("No existe un diagnóstico con ese nombre en el sistema"));
        Medico medico = medicoRepository.findById(cuilMedico).orElseThrow(() -> new IllegalArgumentException("No existe un médico con ese CUIL en el sistema"));
        return paciente.createEvolucion(medico, diagnostico, texto);
    }

    @Override
    public Evolucion createEvolucion(Long cuilPaciente, Long cuilMedico, String nombreDiagnostico, String texto,
            List<Map<String, Integer>> detallesReceta) {
        Paciente paciente = verificarCuilPaciente(cuilPaciente);
        Diagnostico diagnostico = diagnosticoRepository.findById(nombreDiagnostico).orElseThrow(() -> new IllegalArgumentException("No existe un diagnóstico con ese nombre en el sistema"));
        Medico medico = medicoRepository.findById(cuilMedico).orElseThrow(() -> new IllegalArgumentException("No existe un médico con ese CUIL en el sistema"));
        List<DetalleReceta> medicamentosRecetados = verificarMedicamentos(detallesReceta);
        return paciente.createEvolucion(medico, diagnostico, texto, medicamentosRecetados);
    }

    @Override
    public Evolucion createEvolucion(Long cuilPaciente, Long cuilMedico, String nombreDiagnostico, String texto,
            String textoPedidoLaboratorio) {
        Paciente paciente = verificarCuilPaciente(cuilPaciente);
        Diagnostico diagnostico = diagnosticoRepository.findById(nombreDiagnostico).orElseThrow(() -> new IllegalArgumentException("No existe un diagnóstico con ese nombre en el sistema"));
        Medico medico = medicoRepository.findById(cuilMedico).orElseThrow(() -> new IllegalArgumentException("No existe un médico con ese CUIL en el sistema"));
        return paciente.createEvolucion(medico, diagnostico, texto, textoPedidoLaboratorio);
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

    private List<DetalleReceta> verificarMedicamentos(List<Map<String, Integer>> detallesReceta){
        List<DetalleReceta> detalles = new ArrayList<>();
        if(detallesReceta.get(0).get("codigoMedicamento").intValue() == detallesReceta.get(1).get("codigoMedicamento").intValue()){
            detallesReceta = List.of(Map.of("codigoMedicamento", detallesReceta.get(0).get("codigoMedicamento"), "cantidad", detallesReceta.get(0).get("cantidad") + detallesReceta.get(1).get("cantidad")));
        }
        detalles = detallesReceta.stream().map(map -> new DetalleReceta(apiSalud.getMedicamentobyCode(map.get("codigoMedicamento")), map.get("cantidad"))).collect(Collectors.toList());
        return detalles;
    }

    @Override
    public List<RecetaDigital> getRecetas(Long cuil){
        Paciente paciente = verificarCuilPaciente(cuil);
        List<RecetaDigital> recetas = paciente.getRecetas(); 
        if(recetas.isEmpty()) throw new IllegalArgumentException("Esta historia clínica no tiene evoluciones con recetas digitales");  
         return recetas;
    }


    @Override
    public List<Diagnostico> getDiagnosticos(Long cuil){
        Paciente paciente = verificarCuilPaciente(cuil);
        List<Diagnostico> diagnosticos = paciente.getDiagnosticos();
        if(diagnosticos.isEmpty()) throw new IllegalArgumentException("Esta historia clinica no tiene diagnosticos");  
         return diagnosticos;
    }

    @Override
    public List<PedidoLaboratorio> getPedidos(Long cuil){
        Paciente paciente = verificarCuilPaciente(cuil);
        List<PedidoLaboratorio> pedidosLaboratorio = paciente.getPedidosLaboratorio();      
        if(pedidosLaboratorio.isEmpty()) throw new IllegalArgumentException("Esta historia clinica no tiene evoluciones con pedidos de laboratorio");  
         return pedidosLaboratorio;
    }
}
