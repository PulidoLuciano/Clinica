package com.software.backend.controllers.dtos;

public class PedidoLaboratorioDTO {
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
