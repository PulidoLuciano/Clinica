package com.software.backend;

import com.software.backend.models.*;
import com.software.backend.persistence.repositories.interfaces.UsuariosRepository;
import com.software.backend.security.JwtTokenProvider;
import com.software.backend.services.implementations.AuthServiceImpl;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.*;

public class InicioSesionStepDefinitions {

    String token;
    Usuario usuarioIntento;
    Exception errorLogueo;

    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    UsuariosRepository usuariosRepository;
    @Mock
    JwtTokenProvider jwtTokenProvider;
    @InjectMocks
    AuthServiceImpl service;

    Authentication successAuthentication;

    @Before
    public void setup(){
        token = null;
        usuarioIntento = null;
        MockitoAnnotations.initMocks(this);
        successAuthentication = mock(Authentication.class);
        when(usuariosRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(Mockito.any(String.class))).thenReturn("HashIncorrecto");
    }

    @Given("existen los siguientes usuarios en el sistema:")
    public void existenLosSiguientesUsuariosEnElSistema(DataTable usuarios) throws Exception{
        List<Map<String, String>> userList = usuarios.asMaps(String.class, String.class);
        for (Map<String, String> user : userList) {
            String cuilString = user.get("cuil");
            Long cuil = Long.parseLong(cuilString);
            String email = user.get("email");
            String nombre = user.get("nombre");
            String apellido = user.get("apellido");
            String password = user.get("contrasenia");
            String role = user.get("rol");
            ROL rol = (Objects.equals(role, "MEDICO")) ? ROL.MEDICO : ROL.RECEPCIONISTA;
            Persona persona;
            if(rol == ROL.MEDICO){
                persona = new Medico(cuil, 0, new Date(), email, 0, nombre, apellido, null, 54372, new Especialidad("Neurólogo"));
            }else {
                persona = new Recepcionista(cuil, 1L, new Date(), email, 1, nombre, apellido, null);
            }
            when(passwordEncoder.encode(password)).thenReturn("Hash" + cuil);
            Usuario usuario = new Usuario(password, persona, rol, passwordEncoder);
            when(usuariosRepository.findById(email)).thenReturn(Optional.of(usuario));
            when(authenticationManager.authenticate(Mockito.argThat(token ->
                    token instanceof UsernamePasswordAuthenticationToken &&
                            email.equals(token.getPrincipal()) &&
                            password.equals(token.getCredentials())
            ))).thenReturn(successAuthentication);
            when(jwtTokenProvider.generateToken(successAuthentication, usuariosRepository.findById(email).get())).thenReturn("token" + cuil);
            when(jwtTokenProvider.getCuil("token" + cuil)).thenReturn(cuil);
        }

        when(authenticationManager.authenticate(Mockito.argThat(token -> {
            if (!(token instanceof UsernamePasswordAuthenticationToken)) {
                return false;
            }
            UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken) token;
            String email = (String) upat.getPrincipal();
            String password = passwordEncoder.encode((String) upat.getCredentials());
            Optional<Usuario> usuario = usuariosRepository.findById(email);

            usuarioIntento = usuario.isPresent() ? usuario.get() : null;

            return !(usuario.isPresent() && password.equals(usuario.get().getContrasenia()));
        }))).thenThrow(new BadCredentialsException("El e-mail o contraseña son incorrectos"));
    }

    @When("el usuario intenta ingresar al sistema con el email {string} y contraseña {string}")
    public void elUsuarioIntentaIngresarAlSistemaConEmailYContrasenia(String email, String password) {
        try {
            token = service.login(email, password);
        } catch (Exception e) {
            errorLogueo = e;
        }
    }

    @Then("el usuario obtiene acceso al sistema con el rol {string}")
    public void elUsuarioObtieneAccesoAlSistemaConElRol(String role) {
        assertThat(token).isEqualTo(jwtTokenProvider.generateToken(successAuthentication, usuarioIntento));
        assertThat(usuarioIntento.getRol().toString()).isEqualTo(role);
    }

    @And("el cuil del usuario que ha iniciado sesión es {long}")
    public void elCuilDelUsuarioQueHaIniciadoSesiónEs(Long cuilEsperado) {
        assertThat(token).isEqualTo(jwtTokenProvider.generateToken(successAuthentication, usuarioIntento));
        Long cuilToken = jwtTokenProvider.getCuil(token);
        assertThat(Objects.equals(cuilToken, cuilEsperado)).isTrue();
    }

    @Then("el médico no obtiene acceso al sistema y se indica que las credenciales son incorrectas")
    public void elMédicoNoObtieneAccesoAlSistemaYSeIndicaQueLasCredencialesSonIncorrectas() {
        assertThat(token).isBlank();
        assertThat(errorLogueo).isNotNull().isInstanceOf(BadCredentialsException.class);
        assertThat(errorLogueo.getMessage()).isEqualTo("El e-mail o contraseña son incorrectos");
    }
}
