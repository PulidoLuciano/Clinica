package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
public class PedidoLaboratorioDTO {
    
    @NotEmpty(message="El pedido de laboratorio no puede estar vacio")
    @NotBlank(message="El pedido de laboratorio no puede estar vacio")
    private String texto;

    public PedidoLaboratorioDTO() {
    }

    public PedidoLaboratorioDTO(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
