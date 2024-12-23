package com.software.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.software.backend.models.Diagnostico;
import com.software.backend.models.Especialidad;
import com.software.backend.models.Medico;
import com.software.backend.models.Paciente;
import com.software.backend.models.ROL;
import com.software.backend.models.Recepcionista;
import com.software.backend.models.Usuario;
import com.software.backend.persistence.repositories.interfaces.ApiSalud;
import com.software.backend.persistence.repositories.interfaces.DiagnosticoRepository;
import com.software.backend.persistence.repositories.interfaces.MedicoRepository;
import com.software.backend.persistence.repositories.interfaces.PacienteRepository;
import com.software.backend.persistence.repositories.interfaces.RecepcionistaRepository;
import com.software.backend.persistence.repositories.interfaces.UsuariosRepository;

@Component
public class DataInitializer implements CommandLineRunner{

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private UsuariosRepository usuarioRepository;
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApiSalud apiSalud;

    public void initializeData() {
        initializeDiagnosticos();
        initializePacientes();
        initializeMedicos();
        initializeRecepcionistas();
    }

    private void initializeDiagnosticos() {
        List<Diagnostico> diagnosticos = Arrays.asList(
            new Diagnostico("Gripe"),
            new Diagnostico("Hipertensión"),
            new Diagnostico("Diabetes"),
            new Diagnostico("Artritis"),
            new Diagnostico("Migraña")
        );
        diagnosticos.forEach(diagnosticoRepository::save);
    }

    @SuppressWarnings("deprecation")
    private void initializePacientes() {
        Random random = new Random();
        List<Integer> codigosObras = List.of(119708, 123404, 106005, 127109, 106104, 111506, 119708, 123404, 106005, 111506, 106104);

        List<Paciente> pacientes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Paciente paciente = new Paciente(
                20304050L + i, // CUIL
                30040500L + i, // DNI
                new Date(90, random.nextInt(12), random.nextInt(28) + 1), // Fecha de nacimiento
                "paciente" + i + "@email.com", // Email
                1140000000 + i, // Teléfono
                "PacienteNombre" + i, // Nombre
                "Apellido" + i, // Apellido
                null, // Dirección
                random.nextInt(100000), // Número de afiliado
                apiSalud.getObraSocialbyCode(codigosObras.get(i)) // Obra social aleatoria
            );
            pacientes.add(paciente);
        }

        pacientes.forEach(pacienteRepository::save);
    }

    private void initializeMedicos() {
        List<Especialidad> especialidades = Arrays.asList(
            new Especialidad("Cardiología"),
            new Especialidad("Pediatría"),
            new Especialidad("Neurología"),
            new Especialidad("Oncología"),
            new Especialidad("Dermatología")
        );
        
        Medico medico1 = new Medico(
            20123456789L, 12345678, new Date(), "medico1@example.com", 
            1123456789, "Juan", "Pérez", null, 1234, especialidades.get(0));
        usuarioRepository.save(new Usuario("password123", medico1, ROL.MEDICO, passwordEncoder));
        medicoRepository.save(medico1);
            
        Medico medico2 = new Medico(
            20198765432L, 87654321, new Date(), "medico2@example.com", 
            1198765432, "Ana", "García", null, 5678, especialidades.get(1));
        usuarioRepository.save(new Usuario("password123", medico2, ROL.MEDICO, passwordEncoder));
        medicoRepository.save(medico2);
    }

    private void initializeRecepcionistas() {
        Recepcionista recepcionista1 = new Recepcionista(
            20123456789L, 22334455L, new Date(), "recep1@example.com", 
            1123456789, "María", "López", null);
        usuarioRepository.save(new Usuario("password123", recepcionista1, ROL.RECEPCIONISTA, passwordEncoder));
        recepcionistaRepository.save(recepcionista1);

        Recepcionista recepcionista2 = new Recepcionista(
            20198765432L, 55443322L, new Date(), "recep2@example.com", 
            1198765432, "Carlos", "Rodríguez", null);
        usuarioRepository.save(new Usuario("password123", recepcionista2, ROL.RECEPCIONISTA, passwordEncoder));
        recepcionistaRepository.save(recepcionista2);
    }

    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }
}

