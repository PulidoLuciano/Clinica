package com.software.backend.models;

import java.sql.Date;
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

@Entity
@Table(name = "historias_clinicas")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @OneToOne(cascade = CascadeType.ALL)
    private Paciente paciente;

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL)
    private List<DetalleDiagnostico> detalles;
}
