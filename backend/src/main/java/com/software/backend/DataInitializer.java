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
import com.software.backend.models.Medicamento;
import com.software.backend.models.Medico;
import com.software.backend.models.ObraSocial;
import com.software.backend.models.Paciente;
import com.software.backend.models.ROL;
import com.software.backend.models.Recepcionista;
import com.software.backend.models.Usuario;
import com.software.backend.persistence.repositories.interfaces.DiagnosticoRepository;
import com.software.backend.persistence.repositories.interfaces.MedicamentoRepository;
import com.software.backend.persistence.repositories.interfaces.MedicoRepository;
import com.software.backend.persistence.repositories.interfaces.ObraSocialRepository;
import com.software.backend.persistence.repositories.interfaces.PacienteRepository;
import com.software.backend.persistence.repositories.interfaces.RecepcionistaRepository;
import com.software.backend.persistence.repositories.interfaces.UsuariosRepository;

@Component
public class DataInitializer implements CommandLineRunner{

    @Autowired
    private ObraSocialRepository obraSocialRepository;
    @Autowired
    private MedicamentoRepository medicamentoRepository;
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

    public void initializeData() {
        initializeObrasSociales();
        initializeMedicamentos();
        initializeDiagnosticos();
        initializePacientes();
        initializeMedicos();
        initializeRecepcionistas();
    }

    private void initializeObrasSociales() {
        List<ObraSocial> obrasSociales = Arrays.asList(
            new ObraSocial("OSDE"),
            new ObraSocial("Swiss Medical"),
            new ObraSocial("PAMI"),
            new ObraSocial("Medife"),
            new ObraSocial("Galeno")
        );
        obrasSociales.forEach(obraSocialRepository::save);
    }

    private void initializeMedicamentos() {
        List<Medicamento> medicamentos = Arrays.asList(
            new Medicamento(1, "Ibuprofeno", "Ibuprofeno 600mg"),
            new Medicamento(2, "Paracetamol", "Paracetamol 500mg"),
            new Medicamento(3, "Amoxicilina", "Amoxicilina 500mg"),
            new Medicamento(4, "Metformina", "Metformina 850mg"),
            new Medicamento(5, "Losartan", "Losartan 50mg")
        );
        medicamentos.forEach(medicamentoRepository::save);
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

    private void initializePacientes() {
        Random random = new Random();
        List<ObraSocial> obrasSociales = obraSocialRepository.findAll();

        List<Paciente> pacientes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Paciente paciente = new Paciente(
                20304050 + i, // CUIL
                30040500 + i, // DNI
                new Date(90, random.nextInt(12), random.nextInt(28) + 1), // Fecha de nacimiento
                "paciente" + i + "@email.com", // Email
                1140000000 + i, // Teléfono
                "PacienteNombre" + i, // Nombre
                "Apellido" + i, // Apellido
                null, // Dirección
                random.nextInt(100000), // Número de afiliado
                obrasSociales.get(random.nextInt(obrasSociales.size())) // Obra social aleatoria
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

