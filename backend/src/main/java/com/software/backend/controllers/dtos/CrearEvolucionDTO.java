package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CrearEvolucionDTO {

    @NotBlank(message = "El texto de la evolución no puede estar vacío")
    private String texto;

    @Min(value = 1, message = "El cuil no puede ser menor que uno")
    @NotNull(message = "El CUIL del médico es requerido")
    private Long cuilMedico;

    public CrearEvolucionDTO() {
    }

    public CrearEvolucionDTO(@NotBlank(message = "El texto de la evolución no puede estar vacío") String texto,
            @Min(value = 1, message = "El cuil no puede ser menor que uno") @NotNull(message = "El CUIL del médico es requerido") Long cuilMedico) {
        this.texto = texto;
        this.cuilMedico = cuilMedico;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getCuilMedico() {
        return cuilMedico;
    }

    public void setCuilMedico(Long cuilMedico) {
        this.cuilMedico = cuilMedico;
    }
}
