package com.software.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario extends Persona{

    @Column(nullable = false)
    private String contrasenia;
}
