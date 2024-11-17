package com.software.backend.controllers.dtos;

import com.software.backend.models.Medicamento;

public class DetalleRecetaDTO {

    private long id;

    private Medicamento medicamento;

    private int cantidad;
    
    public DetalleRecetaDTO() {
    }

    public DetalleRecetaDTO(long id, Medicamento medicamento, int cantidad) {
        this.id = id;
        this.medicamento = medicamento;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
