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
@Table(name = "recetas_digitales")
public class RecetaDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date fecha;

    @Column(name = "codigo")
    private int codigo;

    @OneToOne(mappedBy = "receta")
    private Evolucion evolucion;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<DetalleReceta> detalles;
}
