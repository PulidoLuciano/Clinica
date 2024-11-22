package com.software.backend.models;

import java.util.List;


public class Provincia {

    private long id;
    private String nombre;
    private List<Localidad> localidades;
    private Pais pais;

    public Provincia() {
    }

    public Provincia(long id, String nombre, List<Localidad> localidades, Pais pais) {
        this.id = id;
        this.nombre = nombre;
        this.localidades = localidades;
        this.pais = pais;
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

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
}
