package com.software.backend.models;

public class Medicamento implements Identifiable<Integer>{

    private Integer codigo;
    private String descripcion;
    private String formato;
    
    public Medicamento(Integer codigo, String descripcion, String formato) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.formato = formato;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Override
    public Integer getId() {
        return codigo;
    }
}
