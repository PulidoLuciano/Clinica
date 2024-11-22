package com.software.backend.models;

import java.util.List;

public class Especialidad {

    private long id;
    private String nombre;
    private List<Medico> medicos;

    public Especialidad() {
    }

    public Especialidad(long id, String nombre, List<Medico> medicos) {
        this.id = id;
        this.nombre = nombre;
        this.medicos = medicos;
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

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    
}
