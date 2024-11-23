package com.software.backend.models;

public class Medicamento implements Identifiable<String>{

    private String nombreComercial;
    private String nombreGenerico;

    public Medicamento() {
    }

    public Medicamento(long id, String nombreComercial, String nombreGenerico) {
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    @Override
    public String getId() {
        return this.nombreComercial;
    }
}
