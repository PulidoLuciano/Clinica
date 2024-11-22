package com.software.backend.models;

import java.util.List;
import java.sql.Date;

public class DetalleDiagnostico {

    private long id;
    private HistoriaClinica historiaClinica;
    private Diagnostico diagnostico;
    private List<Evolucion> evoluciones;
    private String observaciones;
    private Date fechaInicio;

    public DetalleDiagnostico() {
    }

    public DetalleDiagnostico(long id, HistoriaClinica historiaClinica, Diagnostico diagnostico,
            List<Evolucion> evoluciones, String observaciones, Date fechaInicio) {
        this.id = id;
        this.historiaClinica = historiaClinica;
        this.diagnostico = diagnostico;
        this.evoluciones = evoluciones;
        this.observaciones = observaciones;
        this.fechaInicio = fechaInicio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<Evolucion> getEvoluciones() {
        return evoluciones;
    }

    public void setEvoluciones(List<Evolucion> evoluciones) {
        this.evoluciones = evoluciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

}