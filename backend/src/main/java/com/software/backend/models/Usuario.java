package com.software.backend.models;

import java.util.Date;

public abstract class Usuario extends Persona{

    private String contrasenia;

    public Usuario(long cuil, long dni, Date fechaNacimiento, String email, int telefono, String nombre, String apellido,
            Direccion direccion, String contrasenia) {
        super(cuil, dni, fechaNacimiento, email, telefono, nombre, apellido, direccion);
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
