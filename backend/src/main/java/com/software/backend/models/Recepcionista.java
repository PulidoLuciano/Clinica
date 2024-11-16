package com.software.backend.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "recepcionistas")
public class Recepcionista extends Usuario{
    public Recepcionista(int cuil, int dni, Date fechaNacimiento, String email, int telefono, String nombre, String apellido,
            Direccion direccion, int numeroAfiliado, ObraSocial obraSocial, HistoriaClinica historiaClinica, String contrasenia) {
        super(cuil, dni, fechaNacimiento, email, telefono, nombre, apellido, direccion, contrasenia);
    }
}
