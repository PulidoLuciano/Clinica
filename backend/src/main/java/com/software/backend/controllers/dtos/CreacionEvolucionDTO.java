package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreacionEvolucionDTO {

    @NotEmpty(message = "El texto de la evolución no puede estar vacío")
    @NotNull(message = "El texto de la evolución no puede ser nulo")
    private String texto;
}
