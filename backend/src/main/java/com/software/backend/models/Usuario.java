package com.software.backend.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario extends Persona{

    @Column(nullable = false)
    private String contrasenia;

    public Usuario(int cuil, int dni, Date fechaNacimiento, String email, int telefono, String nombre, String apellido,
            Direccion direccion, String contrasenia) {
        super(cuil, dni, fechaNacimiento, email, telefono, nombre, apellido, direccion);
        this.contrasenia = contrasenia;
    }
}
