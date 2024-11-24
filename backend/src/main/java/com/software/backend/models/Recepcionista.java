package com.software.backend.models;

import java.util.Date;

public class Recepcionista extends Persona{
    public Recepcionista(Long cuil, Long dni, Date fechaNacimiento, String email, int telefono, String nombre, String apellido,
            Direccion direccion) {
        super(cuil, dni, fechaNacimiento, email, telefono, nombre, apellido, direccion);
    }
}
