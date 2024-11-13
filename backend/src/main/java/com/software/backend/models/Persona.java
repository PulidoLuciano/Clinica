package com.software.backend.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persona {
    
    @Id
    private int cuil;

    @Column(unique = true, nullable = false)
    private int dni;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(nullable = false)
    private String email;

    @Column
    private int telefono;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;
}
