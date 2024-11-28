package com.software.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.software.backend.models.*;
import com.software.backend.persistence.repositories.interfaces.ApiSalud;
import com.software.backend.persistence.repositories.interfaces.DiagnosticoRepository;
import com.software.backend.persistence.repositories.interfaces.MedicoRepository;
import com.software.backend.persistence.repositories.interfaces.PacienteRepository;
import com.software.backend.services.implementations.PacienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class PacienteServiceTest {

    Paciente paciente;
    Medico medico;
    Diagnostico diagnostico;

    @Mock
    private DiagnosticoRepository diagnosticoRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private ApiSalud apiSalud;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        paciente = new Paciente(
                20123456789L,
                0L,
                new Date(),
                null,
                0,
                "Juan",
                "López",
                null,
                30134,
                new ObraSocial(
                        119708,
                        "OBRA SOCIAL DEL PERSONAL DE SEGURIDAD COMERCIAL, INDUSTRIAL E INVESTIGACIONES PRIVADAS",
                        "OSPSIP"
                )
        );
        medico = new Medico(
                30123456789L,
                0,
                new Date(),
                null,
                0,
                "Juan",
                "Perez",
                null,
                34351,
                new Especialidad("Neurólogo")
        );
        Medicamento medicamento1 = new Medicamento(30134, "IBUPROFENO", "400mg x 6 pastillas");
        Medicamento medicamento2 = new Medicamento(30135, "PARACETAMOL", "600mg x 6 pastillas");
        Medicamento medicamento3 = new Medicamento(30136, "ACTIFEDRIN NASAL", "0.1/ gts.x 15 ml");
        diagnostico = new Diagnostico("Gripe");

        //Mock repositorios ids incorrectos
        when(pacienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        when(medicoRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        when(apiSalud.getMedicamentobyCode(AdditionalMatchers.not(AdditionalMatchers.or(AdditionalMatchers.or(eq(30134), eq(30135)), eq(30136)))))
                .thenThrow(new IllegalArgumentException("El medicamento no existe en la api"));
        when(diagnosticoRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        //Mock repositorios ids correctos: sobreescriben para los casos felices
        when(pacienteRepository.findById(20123456789L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(30123456789L)).thenReturn(Optional.of(medico));
        when(diagnosticoRepository.findById("Gripe")).thenReturn(Optional.of(diagnostico));
        when(apiSalud.getMedicamentobyCode(30134)).thenReturn(medicamento1);
        when(apiSalud.getMedicamentobyCode(30135)).thenReturn(medicamento2);
        when(apiSalud.getMedicamentobyCode(30136)).thenReturn(medicamento3);

        pacienteService.setRepositorio(pacienteRepository);
    }

    @Test
    void crearEvolucionSoloConTextoDeberiaDevolverLaEvolucionConRecetaYPedidoNull() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";

        Evolucion result = pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto);

        assertEvolucionCreada(result);
        assertThat(result.getTexto()).isEqualTo(texto);
        assertThat(result.getPedidoLaboratorio()).isNull();
        assertThat(result.getReceta()).isNull();
    }

    @Test
    void crearEvolucionConRecetaDigitalYCantidadCorrectaDeMedicamentos() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        List<Map<String, Integer>> medicamentos = List.of(
                Map.of("codigoMedicamento", 30134, "cantidad", 2),
                Map.of("codigoMedicamento", 30136, "cantidad", 3)
        );

        Evolucion result = pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto, medicamentos);

        assertEvolucionCreada(result);
        assertThat(result.getTexto()).isEqualTo(texto);
        assertThat(result.getPedidoLaboratorio()).isNull();
        RecetaDigital receta = result.getReceta();
        assertThat(receta).isNotNull();
        assertThat(receta.getDetalles().size()).isEqualTo(2);
        assertThat(receta.getDetalles().get(0).getCantidad()).isEqualTo(2);
        assertThat(receta.getDetalles().get(1).getCantidad()).isEqualTo(3);
    }

    @Test
    void crearEvolucionConRecetaDigitalYMedicamentoRepetidoDeberiaCrearSoloUnDetalleReceta() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        List<Map<String, Integer>> medicamentos = List.of(
                Map.of("codigoMedicamento", 30134, "cantidad", 2),
                Map.of("codigoMedicamento", 30134, "cantidad", 3)
        );

        Evolucion result = pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto, medicamentos);

        assertEvolucionCreada(result);
        assertThat(result.getTexto()).isEqualTo(texto);
        assertThat(result.getPedidoLaboratorio()).isNull();
        RecetaDigital receta = result.getReceta();
        assertThat(receta).isNotNull();
        assertThat(receta.getDetalles().size()).isEqualTo(1);
        assertThat(receta.getDetalles().get(0).getCantidad()).isEqualTo(5);
    }

    @Test
    void crearEvolucionConPedidoLaboratorio() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        String textoPedidoLaboratorio = "Hacer análisis de sangre.";

        Evolucion result = pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto, textoPedidoLaboratorio);

        assertEvolucionCreada(result);
        assertThat(result.getTexto()).isEqualTo(texto);
        assertThat(result.getReceta()).isNull();
        PedidoLaboratorio pedido = result.getPedidoLaboratorio();
        assertThat(pedido).isNotNull();
        assertThat(pedido.getTexto()).isEqualTo(textoPedidoLaboratorio);
    }

    @Test
    void crearEvolucionConCuilPacienteInvalidoDeberiaGenerarIllegalArgumentException() {
        Long cuilPacienteInvalido = 99999999999L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPacienteInvalido, cuilMedico, nombreDiagnostico, texto));
        assertEquals("No existe un paciente con ese cuil", exception.getMessage());
    }

    @Test
    void crearEvolucionConCuilMedicoInvalido() {
        // Arrange
        Long cuilPaciente = 20123456789L;
        Long cuilMedicoInvalido = 99999999999L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedicoInvalido, nombreDiagnostico, texto));
        assertEquals("No existe un médico con ese CUIL en el sistema", exception.getMessage());
    }

    @Test
    void crearEvolucionConDiagnosticoInvalido() {
        // Arrange
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String diagnosticoInvalido = "DiagnosticoInexistente";
        String texto = "Paciente presenta fiebre alta.";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, diagnosticoInvalido, texto));
        assertEquals("No existe un diagnóstico con ese nombre en el sistema", exception.getMessage());
    }

    @Test
    void crearEvolucionConMedicamentoInvalido() {
        // Arrange
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String diagnosticoInvalido = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        Integer codigoIncorrecto = 0;
        List<Map<String, Integer>> medicamentos = List.of(
                Map.of("codigoMedicamento", codigoIncorrecto, "cantidad", 2),
                Map.of("codigoMedicamento", 30134, "cantidad", 3)
        );

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, diagnosticoInvalido, texto, medicamentos));
        assertEquals("El medicamento no existe en la api", exception.getMessage());
    }

    @Test
    void crearEvolucionDeberiaFallarCuandoElTextoEstaVacio() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto));
        assertEquals("El texto de la evolución no puede estar vacío", exception.getMessage());
    }

    @Test
    void crearEvolucionConRecetaDigitalYMasDeDosMedicamentosDeberiaFallar() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        List<Map<String, Integer>> medicamentos = List.of(
                Map.of("codigoMedicamento", 30134, "cantidad", 2),
                Map.of("codigoMedicamento", 30136, "cantidad", 3),
                Map.of("codigoMedicamento", 30135, "cantidad", 3)
        );

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto, medicamentos));
        assertEquals("El número de medicamentos en la receta debe ser dos o menos", exception.getMessage());
    }

    @Test
    void crearEvolucionConRecetaDigitalYNingunMedicamentoDeberiaFallar() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        List<Map<String, Integer>> medicamentos = List.of(
        );

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto, medicamentos));
        assertEquals("El número de medicamentos recetados no puede ser menor que uno", exception.getMessage());
    }

    @Test
    void crearEvolucionConRecetaDigitalYListaDeMedicamentosNullDeberiaFallar() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        List<Map<String, Integer>> medicamentos = null;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto, medicamentos));
        assertEquals("La lista de medicamentos recetados no puede ser null", exception.getMessage());
    }

    @Test
    void crearEvolucionDeberiaFallarCuandoElTextoEsNull() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = null;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto));
        assertEquals("El texto de la evolución no puede ser null", exception.getMessage());
    }

    @Test
    void crearEvolucionConPedidoLaboratorioConTextoDeLaboratorioVacioDeberiaFallar() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        String textoPedidoLaboratorio = "";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto, textoPedidoLaboratorio));
        assertEquals("El texto del pedido de laboratorio no puede ser vacío", exception.getMessage());
    }

    @Test
    void crearEvolucionConPedidoLaboratorioConTextoDeLaboratorioNullDeberiaFallar() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        String textoPedidoLaboratorio = null;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto, textoPedidoLaboratorio));
        assertEquals("El texto del pedido de laboratorio no puede ser null", exception.getMessage());
    }

    @Test
    void crearEvolucionConRecetaDigitalYUnMedicamentoConCantidadMenorQueUnoDeberiaFallar() {
        Long cuilPaciente = 20123456789L;
        Long cuilMedico = 30123456789L;
        String nombreDiagnostico = "Gripe";
        String texto = "Paciente presenta fiebre alta.";
        List<Map<String, Integer>> medicamentos = List.of(
                Map.of("codigoMedicamento", 30134, "cantidad", 2),
                Map.of("codigoMedicamento", 30136, "cantidad", -1)
        );

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pacienteService.createEvolucion(cuilPaciente, cuilMedico, nombreDiagnostico, texto, medicamentos));
        assertEquals("No se puede recetar una cantidad menor a uno de un medicamento", exception.getMessage());
    }

    private void assertEvolucionCreada(Evolucion result) {
        List<Evolucion> evolucionesPaciente = paciente.getHistoriaClinica().getDetalles().get(0).getEvoluciones();
        assertThat(evolucionesPaciente.size()).isEqualTo(1);
        assertThat(evolucionesPaciente.get(0)).isEqualTo(result);
        assertThat(result).isNotNull();
    }
}

