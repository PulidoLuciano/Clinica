package com.software.backend;

import com.software.backend.models.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InicioSesionStepDefinitions {

    @Given("existen los siguientes usuarios en el sistema:")
    public void existenLosSiguientesUsuariosEnElSistema(DataTable usuarios) {
        List<Map<String, String>> userList = usuarios.asMaps(String.class, String.class);
        for (Map<String, String> user : userList) {
            String cuilString = user.get("cuil");
            Long cuil = Long.parseLong(cuilString);
            String email = user.get("email");
            String nombre = user.get("nombre");
            String apellido = user.get("apellido");
            String password = user.get("password");
            String role = user.get("role");
            ROL rol = (Objects.equals(role, "MEDICO")) ? ROL.MEDICO : ROL.RECEPCIONISTA;
            Persona persona;
            if(rol == ROL.MEDICO){
                persona = new Medico(cuil, 0, new Date(), email, 0, nombre, apellido, null, 54372, new Especialidad("Neurólogo"));
            }else {
                persona = new Recepcionista(cuil, 1L, new Date(), email, 1, nombre, apellido, null);
            }
            //Usuario usuario = new Usuario(password, persona, rol, passwordEncoder);
            //usuariosRepositorio.add();
        }
    }

    @When("el medico intenta ingresar al sistema con el email {string} y contraseña {string}")
    public void elMedicoIntentaIngresarAlSistemaConEmailYContrasenia(String email, String password) {

    }

    @Then("el médico obtiene acceso al sistema con el rol {string}")
    public void elMédicoObtieneAccesoAlSistemaConElRol(String arg0) {
    }

    @When("el recepcionista intenta ingresar al sistema con el email {string} y contraseña {string}")
    public void elRecepcionistaIntentaIngresarAlSistemaConElEmailYContraseña(String arg0, String arg1) {
    }

    @Then("el recepcionista obtiene acceso al sistema con el rol {string}")
    public void elRecepcionistaObtieneAccesoAlSistemaConElRol(String arg0) {
    }

    @When("el médico intenta ingresar al sistema con el email {string} y contraseña {string}")
    public void elMédicoIntentaIngresarAlSistemaConElEmailYContraseña(String arg0, String arg1) {
    }

    @Then("el médico no obtiene acceso al sistema y se indica que las credenciales son incorrectas")
    public void elMédicoNoObtieneAccesoAlSistemaYSeIndicaQueLasCredencialesSonIncorrectas() {
    }

    @When("el médico intenta ingresar al sistema con el email {string} y {string}")
    public void elMédicoIntentaIngresarAlSistemaConElEmailY(String arg0, String arg1) {
    }
}
