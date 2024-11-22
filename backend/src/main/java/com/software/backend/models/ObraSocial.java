package com.software.backend.models;

import java.util.List;

public class ObraSocial {

    private long id;
    private String nombre;
    private List<Paciente> pacientes;

    public ObraSocial(){}

    public ObraSocial(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    
}
