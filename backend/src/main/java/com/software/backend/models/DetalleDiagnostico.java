package com.software.backend.models;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class DetalleDiagnostico {

    private Diagnostico diagnostico;
    private List<Evolucion> evoluciones;
    private Date fechaInicio;

    public DetalleDiagnostico() {
    }

    public DetalleDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
        this.evoluciones = new ArrayList<>();
        this.fechaInicio = new Date(System.currentTimeMillis());
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Evolucion crearEvolucion(Medico medico, String texto){
        Evolucion evolucion = new Evolucion(medico, texto, null, null);
        evoluciones.add(evolucion);
        return evolucion;
    }

}