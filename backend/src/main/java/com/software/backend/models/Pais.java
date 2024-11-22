package com.software.backend.models;

import java.util.List;



public class Pais {

    private long id;
    private String nombre;
    private List<Provincia> provincias;

    public Pais() {
    }

    public Pais(long id, String nombre, List<Provincia> provincias) {
        this.id = id;
        this.nombre = nombre;
        this.provincias = provincias;
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

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    
}
