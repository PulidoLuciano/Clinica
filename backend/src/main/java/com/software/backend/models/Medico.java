package com.software.backend.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicos")
public class Medico extends Usuario{

    @Column(nullable = false)
    private int matricula;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    public Medico(int cuil, int dni, Date fechaNacimiento, String email, int telefono, String nombre, String apellido,
            Direccion direccion, int numeroAfiliado, ObraSocial obraSocial, HistoriaClinica historiaClinica, String contrasenia, int matricula, Especialidad especialidad) {
        super(cuil, dni, fechaNacimiento, email, telefono, nombre, apellido, direccion, contrasenia);
        this.matricula = matricula;
        this.especialidad = especialidad;
    }
}
