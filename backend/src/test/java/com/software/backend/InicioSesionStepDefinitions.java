package com.software.backend;

import java.util.List;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class InicioSesionStepDefinitions {

    @Before
    public void setup(){
    }
    
    @Given("existen los siguientes usuarios en el sistema:")
    public void existenLosSiguientesUsuariosEnElSistema(List<?> usuarios) {
    }

    @When("el medico intenta ingresar al sistema con el email {string} y contraseña {string}")
    public void elMedicoIntentaIngresarAlSistemaConEmailYContrasenia(String email, String password) {

    }

}
