package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotNull;

public class MedicamentoDTO {

    @NotNull(message = "Es necesario proveer el código del medicamento")
    private Integer codigo;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(@NotNull(message = "Es necesario proveer el código del medicamento") Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
