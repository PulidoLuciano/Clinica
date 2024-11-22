package com.software.backend.models;

import java.util.List;

public class Localidad {

    private long id;
    private String nombre;
    private List<Direccion> direcciones;
    private Provincia provincia;

    public Localidad() {
    }

    public Localidad(long id, String nombre, List<Direccion> direcciones, Provincia provincia) {
        this.id = id;
        this.nombre = nombre;
        this.direcciones = direcciones;
        this.provincia = provincia;
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

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    
}
