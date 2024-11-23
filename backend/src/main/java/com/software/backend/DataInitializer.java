package com.software.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.software.backend.models.Diagnostico;
import com.software.backend.models.Especialidad;
import com.software.backend.models.Medicamento;
import com.software.backend.models.Medico;
import com.software.backend.models.ObraSocial;
import com.software.backend.models.Paciente;
import com.software.backend.persistence.repositories.interfaces.DiagnosticoRepository;
import com.software.backend.persistence.repositories.interfaces.MedicamentoRepository;
import com.software.backend.persistence.repositories.interfaces.MedicoRepository;
import com.software.backend.persistence.repositories.interfaces.ObraSocialRepository;
import com.software.backend.persistence.repositories.interfaces.PacienteRepository;

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

    public void initializeData() {
        initializeObrasSociales();
        initializeMedicamentos();
        initializeDiagnosticos();
        initializePacientes();
        initializeMedicos();
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
        Random random = new Random();

        List<Medico> medicos = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Medico medico = new Medico(
                30506070L + i, // CUIL
                40506070L + i, // DNI
                new Date(80, random.nextInt(12), random.nextInt(28) + 1), // Fecha de nacimiento
                "medico" + i + "@email.com", // Email
                1150000000 + i, // Teléfono
                "MedicoNombre" + i, // Nombre
                "Apellido" + i, // Apellido
                null, // Dirección
                "password" + i, // Contraseña
                1000 + i, // Matrícula
                especialidades.get(i - 1) // Especialidad
            );
            medicos.add(medico);
        }

        medicos.forEach(medicoRepository::save);
    }

    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }
}

