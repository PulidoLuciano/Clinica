package com.software.backend.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "historias_clinicas")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @OneToOne(cascade = CascadeType.ALL)
    private Paciente paciente;

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL)
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
