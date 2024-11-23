package com.software.backend.models;

import java.util.List;



public class Pais implements Identifiable<String> {

  
    private String nombre;
    private List<Provincia> provincias;

    public Pais() {
    }

    public Pais(String nombre, List<Provincia> provincias) {
       
        this.nombre = nombre;
        this.provincias = provincias;
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

    @Override
    public String getId() {
        return nombre;
    }

    
}
