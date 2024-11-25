package com.software.backend.models;

public class DetalleReceta {

    private Medicamento medicamento;
    private int cantidad;

    public DetalleReceta(Medicamento medicamento, int cantidad) {
        this.medicamento = medicamento;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Medicamento getMedicamentos() {
        return medicamento;
    }

    public void setMedicamentos(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}
