package com.software.backend.models;

public class Diagnostico implements Identifiable<String>{
    
    private String nombre;

    public Diagnostico() {
    }

    public Diagnostico(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getId() {
        return nombre;
    }
}
