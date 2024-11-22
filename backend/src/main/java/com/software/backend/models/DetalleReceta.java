package com.software.backend.models;

public class DetalleReceta {

    private long id;
    private RecetaDigital receta;
    private Medicamento medicamento;
    private int cantidad;
    
    public DetalleReceta() {
    }

    public DetalleReceta(long id, RecetaDigital receta, Medicamento medicamento, int cantidad) {
        this.id = id;
        this.receta = receta;
        this.medicamento = medicamento;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RecetaDigital getReceta() {
        return receta;
    }

    public void setReceta(RecetaDigital receta) {
        this.receta = receta;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
