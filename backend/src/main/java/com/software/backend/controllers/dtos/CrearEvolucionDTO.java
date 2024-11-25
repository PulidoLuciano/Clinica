package com.software.backend.controllers.dtos;

import com.software.backend.models.PedidoLaboratorio;
import com.software.backend.models.RecetaDigital;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CrearEvolucionDTO {

    @NotBlank(message = "El texto de la evolución no puede estar vacío")
    private String texto;

    @Min(value = 1, message = "El cuil no puede ser menor que uno")
    @NotNull(message = "El CUIL del médico es requerido")
    private Long cuilMedico;

    private RecetaDigital receta;

    private PedidoLaboratorio pedidoLaboratorio;

    public CrearEvolucionDTO() {
    }

    public CrearEvolucionDTO(@NotBlank(message = "El texto de la evolución no puede estar vacío") String texto,
            @Min(value = 1, message = "El cuil no puede ser menor que uno") @NotNull(message = "El CUIL del médico es requerido") Long cuilMedico,
            RecetaDigital receta, PedidoLaboratorio pedidoLaboratorio) {
        this.texto = texto;
        this.cuilMedico = cuilMedico;
        this.receta = receta;
        this.pedidoLaboratorio = pedidoLaboratorio;
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
