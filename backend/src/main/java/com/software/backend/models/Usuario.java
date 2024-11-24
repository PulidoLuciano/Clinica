package com.software.backend.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Usuario implements Identifiable<String>{

    private String contrasenia;
    private Persona persona;
    private ROL rol;

    public Usuario(String contrasenia, Persona persona, ROL rol) {
        this.contrasenia = new BCryptPasswordEncoder().encode(contrasenia);
        this.persona = persona;
        this.rol = rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = new BCryptPasswordEncoder().encode(contrasenia);;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ROL getRol() {
        return rol;
    }

    public void setRol(ROL rol) {
        this.rol = rol;
    }

    @Override
    public String getId() {
        return persona.getEmail();
    }

    public String getPassword() {
        return contrasenia;
    }

    public String getUsername() {
        return getId();
    }
}
