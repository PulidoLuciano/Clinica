package com.software.backend.models;

import java.util.List;


public class Provincia implements Identifiable<String>{


    private String nombre;
    private List<Localidad> localidades;
    
    public Provincia() {
    }

    public Provincia( String nombre, List<Localidad> localidades, Pais pais) {
      
        this.nombre = nombre;
        this.localidades = localidades;
      
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

    @Override
    public String getId() {
        return nombre;
    }

    
}
