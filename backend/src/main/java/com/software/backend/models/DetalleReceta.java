package com.software.backend.models;

import java.util.List;

public class DetalleReceta {

    private List<Medicamento> medicamentos;
    private int cantidad;

    public DetalleReceta(List<Medicamento> medicamentos, int cantidad) {
        this.medicamentos = medicamentos;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
