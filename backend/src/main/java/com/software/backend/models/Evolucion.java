package com.software.backend.models;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "evoluciones")
public class Evolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String texto;

    @Column
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "detalle_diagnostico_id")
    private DetalleDiagnostico detalleDiagnostico;

    @OneToOne(optional = true)
    private RecetaDigital receta;
    
}
