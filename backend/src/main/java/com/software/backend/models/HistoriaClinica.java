package com.software.backend.models;

import java.util.Date;
import java.util.List;

public class HistoriaClinica {

    private long id;
    private Date fechaCreacion;
    private Paciente paciente;
    private List<DetalleDiagnostico> detalles;

    public HistoriaClinica() {
    }

    public HistoriaClinica(Date fechaCreacion, Paciente paciente) {
        this.fechaCreacion = fechaCreacion;
        this.paciente = paciente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<DetalleDiagnostico> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDiagnostico> detalles) {
        this.detalles = detalles;
    }

    
}
