package com.software.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private int altura;

    @Column
    private int piso;

    @Column
    private String departamento;

    @ManyToOne
    @JoinColumn(name = "localidad_id")
    private Localidad localidad;
}
