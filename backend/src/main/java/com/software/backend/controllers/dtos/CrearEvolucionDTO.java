package com.software.backend.controllers.dtos;

import com.software.backend.models.PedidoLaboratorio;
import com.software.backend.models.RecetaDigital;

import jakarta.validation.constraints.NotBlank;


public class CrearEvolucionDTO {

    @NotBlank(message = "El texto de la evolución no puede estar vacío")
    private String texto;

    private RecetaDigital receta;

    private PedidoLaboratorio pedidoLaboratorio;

    public CrearEvolucionDTO() {
    }

    public CrearEvolucionDTO(@NotBlank(message = "El texto de la evolución no puede estar vacío") String texto,
            RecetaDigital receta, PedidoLaboratorio pedidoLaboratorio) {
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

    public RecetaDigital getReceta() {
        return receta;
    }

    public void setReceta(RecetaDigital receta) {
        this.receta = receta;
    }

    public PedidoLaboratorio getPedidoLaboratorio() {
        return pedidoLaboratorio;
    }

    public void setPedidoLaboratorio(PedidoLaboratorio pedidoLaboratorio) {
        this.pedidoLaboratorio = pedidoLaboratorio;
    }
    
}
