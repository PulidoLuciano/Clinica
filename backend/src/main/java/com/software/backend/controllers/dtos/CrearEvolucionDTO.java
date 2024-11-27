package com.software.backend.controllers.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


public class CrearEvolucionDTO {

    @NotBlank(message = "El texto de la evolución no puede estar vacío")
    private String texto;

    @Valid
    private RecetaDigitalDTO receta;
    @Valid
    private PedidoLaboratorioDTO pedidoLaboratorio;

    public CrearEvolucionDTO() {
    }

    public CrearEvolucionDTO(@NotBlank(message = "El texto de la evolución no puede estar vacío") String texto,
    RecetaDigitalDTO receta, PedidoLaboratorioDTO pedidoLaboratorio) {
        this.texto = texto;
        this.receta = receta;
        this.pedidoLaboratorio = pedidoLaboratorio;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public RecetaDigitalDTO getReceta() {
        return receta;
    }

    public void setReceta(RecetaDigitalDTO receta) {
        this.receta = receta;
    }

    public PedidoLaboratorioDTO getPedidoLaboratorio() {
        return pedidoLaboratorio;
    }

    public void setPedidoLaboratorio(PedidoLaboratorioDTO pedidoLaboratorio) {
        this.pedidoLaboratorio = pedidoLaboratorio;
    }
    
}
