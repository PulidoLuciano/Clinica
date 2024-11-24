package com.software.backend.models;

import java.util.Date;

public class Medico extends Persona{

    private int matricula;
    private Especialidad especialidad;

    public Medico(long cuil, long dni, Date fechaNacimiento, String email, int telefono, String nombre, String apellido,
            Direccion direccion, int matricula, Especialidad especialidad) {
        super(cuil, dni, fechaNacimiento, email, telefono, nombre, apellido, direccion);
        this.matricula = matricula;
        this.especialidad = especialidad;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
