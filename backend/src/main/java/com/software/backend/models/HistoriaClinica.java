package com.software.backend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class HistoriaClinica {

    private Date fechaCreacion;
    private List<DetalleDiagnostico> detalles;

    public HistoriaClinica() {
        this.fechaCreacion = new Date(System.currentTimeMillis());
        this.detalles = new ArrayList<>();
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<DetalleDiagnostico> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDiagnostico> detalles) {
        this.detalles = detalles;
    }

    public Evolucion createEvolucion(Medico medico, Diagnostico diagnostico, String texto){
        DetalleDiagnostico detalle = getDetalleByDiagnostico(diagnostico);
        return detalle.crearEvolucion(medico, texto);
    }

    public Evolucion createEvolucion(Medico medico, Diagnostico diagnostico, String texto, List<DetalleReceta> medicamentosRecetados){
        DetalleDiagnostico detalle = getDetalleByDiagnostico(diagnostico);
        return detalle.crearEvolucion(medico, texto, medicamentosRecetados);
    }

    public Evolucion createEvolucion(Medico medico, Diagnostico diagnostico, String texto, String textoPedidoLaboratorio){
        DetalleDiagnostico detalle = getDetalleByDiagnostico(diagnostico);
        return detalle.crearEvolucion(medico, texto, textoPedidoLaboratorio);
    }

    public List<RecetaDigital> getRecetas(){
        List<RecetaDigital> recetas = new ArrayList<>();
        detalles.forEach(detalle -> { recetas.addAll(detalle.getRecetas());});
        return recetas;
    }

    public List<Diagnostico> getDiagnosticos(){
        List<Diagnostico> diagnosticos = new ArrayList<>();
        detalles.forEach(detalle -> { diagnosticos.add(detalle.getDiagnostico());});
        return diagnosticos;
    }

    public List<PedidoLaboratorio> getPedidosLaboratorio(){
        List<PedidoLaboratorio> pedidosLaboratorio = new ArrayList<>();
        detalles.forEach(detalle -> { pedidosLaboratorio.addAll(detalle.getPedidosLaboratorio());});
        return pedidosLaboratorio;
    }

    private DetalleDiagnostico getDetalleByDiagnostico(Diagnostico diagnostico){
        if(diagnostico == null) throw new IllegalArgumentException("El diagnóstico no puede ser nulo");
        Optional<DetalleDiagnostico> detalleOpt = detalles.stream().filter(item -> item.getDiagnostico().equals(diagnostico)).findFirst();
        DetalleDiagnostico detalle;
        if(detalleOpt.isEmpty()){
            detalle = new DetalleDiagnostico(diagnostico);
            detalles.add(detalle);
        }else{
            detalle = detalleOpt.get();
        }
        return detalle;
    }

    public List<Evolucion> getEvolucionesPorDiagnostico(String diagnostico){
        List<Evolucion> evoluciones = new ArrayList<>();
        try{
            DetalleDiagnostico detalle = detalles.stream().filter(d->d.getDiagnostico().getNombre().equals(diagnostico)).findFirst().get();
            evoluciones = detalle.getEvoluciones();
        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("Este diagnostico no tiene evoluciones");
        }
        
        return evoluciones;
    } 

}
