package com.software.backend.models;

public class ObraSocial implements Identifiable<Integer>{

    private Integer codigo;
    private String denominacion;
    private String sigla;
    
    public ObraSocial() {
    }

    public ObraSocial(Integer codigo, String denominacion, String sigla) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.sigla = sigla;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public Integer getId() {
        return codigo;
    }
}
