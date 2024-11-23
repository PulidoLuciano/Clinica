package com.software.backend.models;

import java.util.Date;
import java.util.List;

public class HistoriaClinica {

    private Date fechaCreacion;
    private List<DetalleDiagnostico> detalles;

    public HistoriaClinica() {
    }

    public HistoriaClinica(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
}
