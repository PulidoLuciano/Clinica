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

}