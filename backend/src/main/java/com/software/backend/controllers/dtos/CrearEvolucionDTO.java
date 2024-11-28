package com.software.backend.controllers.dtos;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class CrearEvolucionDTO {

    @NotBlank(message = "El texto de la evolución no puede estar vacío")
    private String texto;

    @Valid
    @Size(min=1,max=2,message="La receta debe tener uno o dos medicamentos")
    private List<DetalleRecetaDTO> medicamentosReceta;

    private String textoPedidoLaboratorio;

    public CrearEvolucionDTO() {
    }

    public CrearEvolucionDTO(@NotBlank(message = "El texto de la evolución no puede estar vacío") String texto,
            @Valid List<DetalleRecetaDTO> medicamentosReceta, String textoPedidoLaboratorio) {
        this.texto = texto;
        this.medicamentosReceta = medicamentosReceta;
        this.textoPedidoLaboratorio = textoPedidoLaboratorio;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<DetalleRecetaDTO> getMedicamentosReceta() {
        return medicamentosReceta;
    }

    public void setMedicamentosReceta(List<DetalleRecetaDTO> medicamentosReceta) {
        this.medicamentosReceta = medicamentosReceta;
    }

    public String getTextoPedidoLaboratorio() {
        return textoPedidoLaboratorio;
    }

    public void setTextoPedidoLaboratorio(String textoPedidoLaboratorio) {
        this.textoPedidoLaboratorio = textoPedidoLaboratorio;
    }
}
