package com.software.backend.controllers.dtos;

import java.util.Date;
import java.util.List;

import com.software.backend.models.Diagnostico;

public class DetalleDiagnosticoDTO {

    private long id;

    private String observaciones;

    private Date fechaInicio;

    private Diagnostico diagnostico;

    private List<EvolucionDTO> evoluciones;

    public DetalleDiagnosticoDTO() {
        this.fechaInicio = new Date(System.currentTimeMillis());
    }

    public DetalleDiagnosticoDTO(long id, String observaciones, Date fechaInicio, List<EvolucionDTO> evoluciones, Diagnostico diagnostico) {
        this.id = id;
        this.observaciones = observaciones;
        this.fechaInicio = fechaInicio;
        this.evoluciones = evoluciones;
        this.diagnostico = diagnostico;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<EvolucionDTO> getEvoluciones() {
        return evoluciones;
    }

    public void setEvoluciones(List<EvolucionDTO> evoluciones) {
        this.evoluciones = evoluciones;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
}
