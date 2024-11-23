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

    public Evolucion createEvolucion(Medico medico, Diagnostico diagnostico, String texto){
        Optional<DetalleDiagnostico> detalleOpt = detalles.stream().filter(item -> item.getDiagnostico().equals(diagnostico)).findFirst();
        DetalleDiagnostico detalle;
        if(detalleOpt.isEmpty()){
            detalle = new DetalleDiagnostico(diagnostico);
            detalles.add(detalle);
        }else{
            detalle = detalleOpt.get();
        }
        return detalle.crearEvolucion(medico, texto);
    }
}
