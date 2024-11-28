import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ModalCrearPaciente from "../Modal/ModalCrearPaciente";
import { fetchPaciente, createPaciente } from "../../api/pacientes";
import { fetchHistoriaClinica } from "../../api/historiaClinica";
import ModalAgregarEvolucion from "../Modal/ModalAgregarEvolucion";

function MainPage() {
    const navigate = useNavigate();
    const [paciente, setPaciente] = useState(null);
    const [historiaClinica, setHistoriaClinica] = useState(null);
    const [modalVisible, setModalVisible] = useState(false);
    const [modalEvolucionVisible, setModalEvolucionVisible] = useState(false);
    const [nuevoPaciente, setNuevoPaciente] = useState({
        cuil: "",
        dni: "",
        fechaNacimiento: "",
        email: "",
        telefono: "",
        nombre: "",
        apellido: "",
        numeroAfiliado: "",
        obraSocial: "",
    });
    const [diagnosticoVisible, setDiagnosticoVisible] = useState({});
    const [recargar, setRecargar] = useState(false); // Estado para controlar la recarga

    useEffect(() => {
        const obtenerHistoriaClinica = async () => {
            if (paciente) {
                const token = sessionStorage.getItem("jwt");
                try {
                    const historia = await fetchHistoriaClinica(
                        paciente.cuil,
                        token
                    );
                    setHistoriaClinica(historia);
                } catch (error) {
                    console.error(
                        "Error al obtener la historia clínica:",
                        error
                    );
                }
            }
        };

        obtenerHistoriaClinica();
    }, [paciente, recargar]); // Agregar `recargar` como dependencia

    const handleLogout = () => {
        if (window.confirm("¿Estás seguro de que deseas cerrar sesión?")) {
            sessionStorage.removeItem("jwt");
            sessionStorage.removeItem("email");
            navigate("/");
        }
    };

    const calcularEdad = (fechaNacimiento) => {
        const hoy = new Date();
        const nacimiento = new Date(fechaNacimiento);
        let edad = hoy.getFullYear() - nacimiento.getFullYear();
        const mes = hoy.getMonth() - nacimiento.getMonth();
        if (mes < 0 || (mes === 0 && hoy.getDate() < nacimiento.getDate())) {
            edad--;
        }
        return edad;
    };

    const handleFetchPaciente = async (cuil) => {
        try {
            const token = sessionStorage.getItem("jwt");
            const pacienteEncontrado = await fetchPaciente(cuil, token);

            if (!pacienteEncontrado) {
                setModalVisible(true); // Mostrar modal si no existe el paciente
                return;
            }

            setPaciente({
                nombre: `${pacienteEncontrado.nombre} ${pacienteEncontrado.apellido}`,
                dni: pacienteEncontrado.dni,
                cuil: pacienteEncontrado.cuil,
                fechaNacimiento: pacienteEncontrado.fechaNacimiento,
                email: pacienteEncontrado.email,
                telefono: pacienteEncontrado.telefono,
                numeroAfiliado: pacienteEncontrado.numeroAfiliado,
                obraSocial: pacienteEncontrado.obraSocial,
                edad: calcularEdad(pacienteEncontrado.fechaNacimiento),
            });
        } catch (error) {
            console.error("Error al obtener el paciente:", error);
        }
    };

    const handleSearch = (event) => {
        event.preventDefault();
        const cuil = event.target.cuil.value.trim();
        if (cuil) {
            handleFetchPaciente(cuil);
        }
    };

    const handleCreatePaciente = async () => {
        try {
            const token = sessionStorage.getItem("jwt");
            await createPaciente(nuevoPaciente, token);
            alert("Paciente creado con éxito");
            setModalVisible(false);
        } catch (error) {
            console.error("Error al crear el paciente:", error);
            alert("Error al crear el paciente");
        }
    };

    const handleChangeNuevoPaciente = (e) => {
        const { name, value } = e.target;
        setNuevoPaciente({ ...nuevoPaciente, [name]: value });
    };

    const handleCreateEvolucion = async (evolucion) => {
        try {
            const token = sessionStorage.getItem("jwt");
            // Aquí iría la llamada a la API para crear la evolución
            console.log("Nueva evolución:", evolucion);
            setModalEvolucionVisible(false);
            setRecargar(!recargar); // Cambiar el estado para forzar la recarga
        } catch (error) {
            console.error("Error al crear la evolución:", error);
            alert("Error al crear la evolución");
        }
    };

    const toggleDiagnostico = (index) => {
        setDiagnosticoVisible((prevState) => ({
            ...prevState,
            [index]: !prevState[index],
        }));
    };

    return (
        <div className="flex flex-col h-screen">
            <nav className="bg-white shadow-lg">
                <div className="max-w-7xl mx-auto px-4">
                    <div className="flex justify-between h-16">
                        {/* Encabezado de la clínica */}
                        <div className="flex items-center">
                            <div className="flex-shrink-0 flex items-center">
                                <span className="text-xl font-semibold text-gray-800">
                                    Clínica
                                </span>
                            </div>
                        </div>

                        {/* Buscador de paciente y botón de logout */}
                        <div className="flex items-center gap-4">
                            <form
                                onSubmit={handleSearch}
                                className="flex gap-2"
                            >
                                <input
                                    type="text"
                                    name="cuil"
                                    placeholder="Buscar por CUIL"
                                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                                />
                                <button
                                    type="submit"
                                    className="bg-indigo-600 text-white px-4 py-2 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                                >
                                    Buscar
                                </button>
                            </form>
                            <button
                                onClick={handleLogout}
                                className="bg-red-600 text-white px-4 py-2 rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
                            >
                                Cerrar sesión
                            </button>
                        </div>
                    </div>
                </div>
            </nav>

            {/* Contenido principal */}
            <div className="flex flex-1 gap-5 bg-white overflow-y-auto p-4">
                {/* Datos del paciente */}
                <div className="flex-1 bg-gray-100 p-4 border border-gray-300 rounded-md overflow-y-auto relative">
                    <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
                        Datos del paciente
                    </h3>
                    {paciente ? (
                        <div className="p-2">
                            <p>
                                <strong>Nombre:</strong> {paciente.nombre}{" "}
                                {paciente.apellido}
                            </p>
                            <p>
                                <strong>DNI:</strong> {paciente.dni}
                            </p>
                            <p>
                                <strong>CUIL:</strong> {paciente.cuil}
                            </p>
                            <p>
                                <strong>Fecha de Nacimiento:</strong>{" "}
                                {new Date(
                                    paciente.fechaNacimiento
                                ).toLocaleDateString()}
                            </p>
                            <p>
                                <strong>Edad:</strong> {paciente.edad}
                            </p>
                            <p>
                                <strong>Email:</strong> {paciente.email}
                            </p>
                            <p>
                                <strong>Teléfono:</strong> {paciente.telefono}
                            </p>
                            <p>
                                <strong>Número de Afiliado:</strong>{" "}
                                {paciente.numeroAfiliado}
                            </p>
                            <p>
                                <strong>Obra Social:</strong>{" "}
                                {paciente.obraSocial.denominacion} (
                                {paciente.obraSocial.sigla})
                            </p>
                        </div>
                    ) : (
                        <p className="text-center">Busca tu paciente</p>
                    )}
                </div>

                {/* Historia clínica */}
                <div className="flex-1 bg-gray-100 p-4 border border-gray-300 rounded-md overflow-y-auto relative">
                    <div className="flex flex-col h-full">
                        <div className="flex-1 mb-4 overflow-y-auto">
                            <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
                                Historia clínica
                            </h3>
                            {historiaClinica ? (
                                <div className="p-2">
                                    <p>
                                        <strong>Fecha de Creación:</strong>{" "}
                                        {new Date(
                                            historiaClinica.fechaCreacion
                                        ).toLocaleDateString()}
                                    </p>
                                    <div>
                                        <h4 className="font-semibold">
                                            Diagnósticos:
                                        </h4>
                                        {historiaClinica.diagnosticos.map(
                                            (diagnostico, index) => (
                                                <div key={index}>
                                                    <p
                                                        className="cursor-pointer text-blue-500"
                                                        onClick={() =>
                                                            toggleDiagnostico(
                                                                index
                                                            )
                                                        }
                                                    >
                                                        {diagnostico.nombre}
                                                    </p>
                                                    {diagnosticoVisible[
                                                        index
                                                    ] && (
                                                        <div className="ml-4">
                                                            {historiaClinica.detalles
                                                                .filter(
                                                                    (detalle) =>
                                                                        detalle
                                                                            .diagnostico
                                                                            .id ===
                                                                        diagnostico.id
                                                                )
                                                                .map(
                                                                    (
                                                                        detalle,
                                                                        idx
                                                                    ) => (
                                                                        <div
                                                                            key={
                                                                                idx
                                                                            }
                                                                            className="mb-2"
                                                                        >
                                                                            {detalle.evoluciones.map(
                                                                                (
                                                                                    evolucion,
                                                                                    idy
                                                                                ) => (
                                                                                    <div
                                                                                        key={
                                                                                            idy
                                                                                        }
                                                                                        className="ml-4 border-t border-gray-300 pt-2 mt-2"
                                                                                    >
                                                                                        <p>
                                                                                            <strong>
                                                                                                Fecha:
                                                                                            </strong>{" "}
                                                                                            {new Date(
                                                                                                evolucion.fecha
                                                                                            ).toLocaleDateString()}
                                                                                        </p>
                                                                                        <p>
                                                                                            <strong>
                                                                                                Texto:
                                                                                            </strong>{" "}
                                                                                            {
                                                                                                evolucion.texto
                                                                                            }
                                                                                        </p>
                                                                                        {evolucion.receta && (
                                                                                            <div className="ml-4">
                                                                                                <h5 className="font-semibold">
                                                                                                    Receta:
                                                                                                </h5>
                                                                                                <p>
                                                                                                    <strong>
                                                                                                        Código:
                                                                                                    </strong>{" "}
                                                                                                    {
                                                                                                        evolucion
                                                                                                            .receta
                                                                                                            .codigo
                                                                                                    }
                                                                                                </p>
                                                                                                {evolucion.receta.detalles.map(
                                                                                                    (
                                                                                                        detalleReceta,
                                                                                                        idr
                                                                                                    ) => (
                                                                                                        <div
                                                                                                            key={
                                                                                                                idr
                                                                                                            }
                                                                                                            className="ml-4"
                                                                                                        >
                                                                                                            <p>
                                                                                                                <strong>
                                                                                                                    Medicamento:
                                                                                                                </strong>{" "}
                                                                                                                {
                                                                                                                    detalleReceta
                                                                                                                        .medicamentos
                                                                                                                        .descripcion
                                                                                                                }
                                                                                                            </p>
                                                                                                            <p>
                                                                                                                <strong>
                                                                                                                    Formato:
                                                                                                                </strong>{" "}
                                                                                                                {
                                                                                                                    detalleReceta
                                                                                                                        .medicamentos
                                                                                                                        .formato
                                                                                                                }
                                                                                                            </p>
                                                                                                            <p>
                                                                                                                <strong>
                                                                                                                    Cantidad:
                                                                                                                </strong>{" "}
                                                                                                                {
                                                                                                                    detalleReceta.cantidad
                                                                                                                }
                                                                                                            </p>
                                                                                                        </div>
                                                                                                    )
                                                                                                )}
                                                                                            </div>
                                                                                        )}
                                                                                        {evolucion.pedidoLaboratorio && (
                                                                                            <div className="ml-4">
                                                                                                <h5 className="font-semibold">
                                                                                                    Pedido
                                                                                                    de
                                                                                                    Laboratorio:
                                                                                                </h5>
                                                                                                <p>
                                                                                                    {
                                                                                                        evolucion
                                                                                                            .pedidoLaboratorio
                                                                                                            .texto
                                                                                                    }
                                                                                                </p>
                                                                                            </div>
                                                                                        )}
                                                                                    </div>
                                                                                )
                                                                            )}
                                                                        </div>
                                                                    )
                                                                )}
                                                        </div>
                                                    )}
                                                </div>
                                            )
                                        )}
                                    </div>
                                </div>
                            ) : (
                                <p className="text-center">
                                    No hay historia clínica disponible
                                </p>
                            )}
                        </div>
                        <div className="flex-shrink-0">
                            {paciente && (
                                <button
                                    onClick={() =>
                                        setModalEvolucionVisible(true)
                                    }
                                    className="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2 w-full"
                                >
                                    Agregar evolución
                                </button>
                            )}
                        </div>
                    </div>
                </div>

                {/* Recetas y Pedidos de laboratorio */}
                <div className="flex-1 bg-gray-100 p-4 border border-gray-300 rounded-md overflow-y-auto relative">
                    <div className="flex flex-col h-full">
                        <div className="flex-1 mb-4 overflow-y-auto">
                            <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
                                Recetas
                            </h3>
                            {historiaClinica ? (
                                <div className="p-2">
                                    {historiaClinica.recetas.map(
                                        (receta, index) => (
                                            <div
                                                key={index}
                                                className="mb-2 border-t border-gray-300 pt-2 mt-2"
                                            >
                                                <p>
                                                    <strong>Fecha:</strong>{" "}
                                                    {new Date(
                                                        receta.fecha
                                                    ).toLocaleDateString()}
                                                </p>
                                                <p>
                                                    <strong>Código:</strong>{" "}
                                                    {receta.codigo}
                                                </p>
                                                {receta.detalles.map(
                                                    (detalleReceta, idr) => (
                                                        <div
                                                            key={idr}
                                                            className="ml-4"
                                                        >
                                                            <p>
                                                                <strong>
                                                                    Medicamento:
                                                                </strong>{" "}
                                                                {
                                                                    detalleReceta
                                                                        .medicamentos
                                                                        .descripcion
                                                                }
                                                            </p>
                                                            <p>
                                                                <strong>
                                                                    Formato:
                                                                </strong>{" "}
                                                                {
                                                                    detalleReceta
                                                                        .medicamentos
                                                                        .formato
                                                                }
                                                            </p>
                                                            <p>
                                                                <strong>
                                                                    Cantidad:
                                                                </strong>{" "}
                                                                {
                                                                    detalleReceta.cantidad
                                                                }
                                                            </p>
                                                        </div>
                                                    )
                                                )}
                                            </div>
                                        )
                                    )}
                                </div>
                            ) : (
                                <p className="text-center">
                                    No hay recetas disponibles
                                </p>
                            )}
                        </div>
                        <div className="flex-1 overflow-y-auto">
                            <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
                                Pedidos de laboratorio
                            </h3>
                            {historiaClinica ? (
                                <div className="p-2">
                                    {historiaClinica.pedidosLaboratorio.map(
                                        (pedido, index) => (
                                            <div
                                                key={index}
                                                className="mb-2 border-t border-gray-300 pt-2 mt-2"
                                            >
                                                <p>{pedido.texto}</p>
                                            </div>
                                        )
                                    )}
                                </div>
                            ) : (
                                <p className="text-center">
                                    No hay pedidos de laboratorio disponibles
                                </p>
                            )}
                        </div>
                    </div>
                </div>
            </div>

            {/* Modal para crear paciente */}
            <ModalCrearPaciente
                visible={modalVisible}
                onClose={() => setModalVisible(false)}
                onCreate={handleCreatePaciente}
                pacienteData={nuevoPaciente}
                onChange={handleChangeNuevoPaciente}
            />

            {/* Modal para agregar evolución */}
            <ModalAgregarEvolucion
                visible={modalEvolucionVisible}
                onClose={() => setModalEvolucionVisible(false)}
                onSubmit={handleCreateEvolucion}
                paciente={paciente} // Pasamos el paciente al modal
            />
        </div>
    );
}

export default MainPage;
