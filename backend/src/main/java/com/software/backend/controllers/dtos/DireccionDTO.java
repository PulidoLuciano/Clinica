package com.software.backend.controllers.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DireccionDTO {

    @NotNull(message = "La calle no puede ser nula")
    @NotEmpty(message = "La calle no puede estar vacía")
    private String calle;

    @NotNull(message = "La altura no puede ser nula")
    @Min(value = 1, message = "La altura debe ser un número positivo")
    private int altura;

    private Integer piso;

    private String departamento;

    @NotNull(message = "La localidad no puede ser nula")
    private LocalidadDTO localidad;

    public DireccionDTO() {
    }

    public DireccionDTO(
            @NotNull(message = "La calle no puede ser nula") @NotEmpty(message = "La calle no puede estar vacía") String calle,
            @NotNull(message = "La altura no puede ser nula") @Min(value = 1, message = "La altura debe ser un número positivo") int altura,
            Integer piso, String departamento, @NotNull LocalidadDTO localidad) {
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.departamento = departamento;
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public LocalidadDTO getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadDTO localidad) {
        this.localidad = localidad;
    }

    
}

