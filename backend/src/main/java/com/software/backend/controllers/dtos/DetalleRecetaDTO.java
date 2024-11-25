package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;


public class DetalleRecetaDTO {

    @NotNull(message = "El detalle de la receta debe tener un medicamento")
    @Valid
    private MedicamentoDTO medicamento;

    @NotNull(message="El medicamento debe tener una cantidad asociada")
    @Min(value=1,message="Se debe recetar al menos una unidad")
    private Integer cantidad;

    


    public DetalleRecetaDTO(MedicamentoDTO medicamento, Integer cantidad) {
        this.medicamento = medicamento;
        this.cantidad = cantidad;
    }

    public DetalleRecetaDTO() {
    }

    public MedicamentoDTO getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(MedicamentoDTO medicamento) {
        this.medicamento = medicamento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }



}
