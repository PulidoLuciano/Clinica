package com.software.backend.controllers.dtos;

import java.util.Date;

import com.software.backend.models.ObraSocial;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class PacienteDTO {

    @NotNull(message = "El CUIL es requerido")
    @Min(value = 1, message = "El CUIL debe ser mayor que 0")
    private Long cuil;

    @NotNull(message = "El DNI es requerido")
    @Min(value = 1, message = "El CUIL debe ser mayor que 0")
    private Long dni;

    @NotNull(message = "La fecha de nacimiento es requerida")
    @PastOrPresent(message = "La fecha de nacimiento no puede ser posterior al presente")
    private Date fechaNacimiento;

    @NotNull(message = "El email es requerido")
    @NotBlank(message = "El email es requerido")
    @Email(message = "El email debe ser válido")
    @Size(min = 2, max = 255, message = "El email debe tener entre 2 y 255 caracteres")
    private String email;

    @NotNull(message = "El número de teléfono es requerido")
    @Digits(integer = 15, fraction = 0, message = "El número de teléfono debe ser válido")
    private Long telefono;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 255, message = "El apellido debe tener entre 2 y 255 caracteres")
    private String apellido;

    @NotNull(message = "El número de afiliado no puede ser nulo")
    @Min(value = 1, message = "El número de afiliado debe ser mayor que 0")
    private Integer numeroAfiliado;

    private ObraSocial obraSocial;
    
    public PacienteDTO(@NotNull(message = "El CUIL no puede ser nulo") Long cuil,
            @NotNull(message = "El DNI no puede ser nulo") Long dni,
            @NotNull(message = "La fecha de nacimiento no puede ser nula") @PastOrPresent(message = "La fecha de nacimiento no puede ser posterior al presente") Date fechaNacimiento,
            @Email(message = "El email debe ser válido") @Size(min = 2, max = 255, message = "El email debe tener entre 2 y 255 caracteres") String email,
            @NotNull(message = "El número de teléfono no puede ser nulo") @Digits(integer = 15, fraction = 0, message = "El número de teléfono debe ser válido") Long telefono,
            @NotBlank(message = "El nombre no puede estar vacío") @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres") String nombre,
            @NotBlank(message = "El apellido no puede estar vacío") @Size(min = 2, max = 255, message = "El apellido debe tener entre 2 y 255 caracteres") String apellido,
            @NotNull(message = "El número de afiliado no puede ser nulo") Integer numeroAfiliado,
            @NotNull ObraSocial obraSocial) {
        this.cuil = cuil;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroAfiliado = numeroAfiliado;
        this.obraSocial = obraSocial;
    }

    public PacienteDTO() {
    }

    public long getCuil() {
        return cuil;
    }

    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getNumeroAfiliado() {
        return numeroAfiliado;
    }

    public void setNumeroAfiliado(Integer numeroAfiliado) {
        this.numeroAfiliado = numeroAfiliado;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }
}
