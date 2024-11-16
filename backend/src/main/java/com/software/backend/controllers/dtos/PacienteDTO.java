package com.software.backend.controllers.dtos;

import java.util.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PacienteDTO {

    @NotNull(message = "El CUIL no puede ser nulo")
    private Integer cuil;

    @NotNull(message = "El DNI no puede ser nulo")
    private Integer dni;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private Date fechaNacimiento;

    @Email(message = "El email debe ser válido")
    @Size(min = 2, max = 255, message = "El apellido debe tener entre 2 y 255 caracteres")
    private String email;

    @NotNull(message = "El número de teléfono no puede ser nulo")
    @Digits(integer = 15, fraction = 0, message = "El número de teléfono debe ser válido")
    private Integer telefono;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 255, message = "El apellido debe tener entre 2 y 255 caracteres")
    private String apellido;

    //private DireccionDTO direccion;

    @NotNull(message = "El número de afiliado no puede ser nulo")
    private Integer numeroAfiliado;
    
    private ObraSocialDTO obraSocial;

    public PacienteDTO(@NotNull(message = "El CUIL no puede ser nulo") Integer cuil,
            @NotNull(message = "El DNI no puede ser nulo") Integer dni,
            @NotNull(message = "La fecha de nacimiento no puede ser nula") Date fechaNacimiento,
            @Email(message = "El email debe ser válido") @Size(min = 2, max = 255, message = "El apellido debe tener entre 2 y 255 caracteres") String email,
            @NotNull(message = "El número de teléfono no puede ser nulo") @Digits(integer = 15, fraction = 0, message = "El número de teléfono debe ser válido") Integer telefono,
            @NotBlank(message = "El nombre no puede estar vacío") @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres") String nombre,
            @NotBlank(message = "El apellido no puede estar vacío") @Size(min = 2, max = 255, message = "El apellido debe tener entre 2 y 255 caracteres") String apellido,
            @NotNull(message = "El número de afiliado no puede ser nulo") Integer numeroAfiliado,
            ObraSocialDTO obraSocial) {
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

    public Integer getCuil() {
        return cuil;
    }

    public void setCuil(Integer cuil) {
        this.cuil = cuil;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
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

    public ObraSocialDTO getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocialDTO obraSocial) {
        this.obraSocial = obraSocial;
    }

    
}
