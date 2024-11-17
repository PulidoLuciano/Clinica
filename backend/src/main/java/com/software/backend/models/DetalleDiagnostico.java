package com.software.backend.models;

import java.util.List;
import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DetalleDiagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "historia_clinica_id")
    private HistoriaClinica historiaClinica;

    @ManyToOne
    @JoinColumn(name = "diagnostico_id")
    private Diagnostico diagnostico;

    @OneToMany(mappedBy = "detalleDiagnostico", cascade = CascadeType.ALL)
    private List<Evolucion> evoluciones;

    @Column
    private String observaciones;

    @Column(name = "fecha_inicio")
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