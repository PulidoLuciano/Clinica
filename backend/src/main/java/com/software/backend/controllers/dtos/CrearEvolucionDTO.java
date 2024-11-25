package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

public class CrearEvolucionDTO {

    @NotBlank(message = "El texto de la evolución no puede estar vacío")
    private String texto;

    public CrearEvolucionDTO() {
    }

    public CrearEvolucionDTO(@NotBlank(message = "El texto de la evolución no puede estar vacío") String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
