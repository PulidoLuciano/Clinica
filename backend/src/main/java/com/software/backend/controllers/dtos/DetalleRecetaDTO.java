package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class DetalleRecetaDTO {

    @NotNull(message = "Es necesario proveer el código del medicamento")
    private Integer codigoMedicamento;

    @NotNull(message="El medicamento debe tener una cantidad asociada")
    @Min(value=1,message="Se debe recetar al menos una unidad")
    private Integer cantidad;

    public DetalleRecetaDTO() {
    }

    public DetalleRecetaDTO(
            @NotNull(message = "Es necesario proveer el código del medicamento") Integer codigoMedicamento,
            @NotNull(message = "El medicamento debe tener una cantidad asociada") @Min(value = 1, message = "Se debe recetar al menos una unidad") Integer cantidad) {
        this.codigoMedicamento = codigoMedicamento;
        this.cantidad = cantidad;
    }

    public Integer getCodigoMedicamento() {
        return codigoMedicamento;
    }

    public void setCodigoMedicamento(Integer codigoMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
