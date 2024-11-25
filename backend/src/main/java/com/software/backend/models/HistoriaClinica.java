package com.software.backend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public Evolucion createEvolucion(Medico medico, Diagnostico diagnostico, String texto, RecetaDigital receta, PedidoLaboratorio pedidoLaboratorio){
        Optional<DetalleDiagnostico> detalleOpt = detalles.stream().filter(item -> item.getDiagnostico().equals(diagnostico)).findFirst();
        DetalleDiagnostico detalle;
        if(detalleOpt.isEmpty()){
            detalle = new DetalleDiagnostico(diagnostico);
            detalles.add(detalle);
        }else{
            detalle = detalleOpt.get();
        }
        return detalle.crearEvolucion(medico, texto, receta, pedidoLaboratorio);
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

}
