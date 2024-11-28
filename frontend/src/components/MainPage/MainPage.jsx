import React, { useState } from "react";
import Navbar from "../Navbar/Navbar";
import ModalCrearPaciente from "../Modal/ModalCrearPaciente";
import { fetchPaciente, createPaciente } from "../../api/pacientes";

function MainPage() {
    const [paciente, setPaciente] = useState({
        nombre: "Nombre del paciente",
        dni: "DNI",
        edad: "Edad",
        obraSocial: "Obra Social",
    });

    const [modalVisible, setModalVisible] = useState(false); // Control del modal
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

    // Función para calcular la edad a partir de la fecha de nacimiento
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
                edad: calcularEdad(pacienteEncontrado.fechaNacimiento),
                obraSocial: pacienteEncontrado.obraSocial.nombre,
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

    return (
        <div className="flex flex-col h-screen">
            <Navbar paciente={paciente} onSearch={handleSearch} />

            {/* Contenido principal */}
            <div className="flex flex-1 gap-5 bg-white overflow-y-auto p-4">
                {/* Izquierda */}
                <div className="flex-1 flex flex-col gap-2 p-2">
                    <div className="flex-1 bg-gray-100 p-2 border border-gray-300 rounded-md">
                        <h3 className="bg-blue-500 text-white text-center p-2 rounded-md">
                            Diagnósticos previos
                        </h3>
                        <div className="diagnosticos"></div>
                    </div>
                    <div className="flex-1 bg-gray-100 p-2 border border-gray-300 rounded-md">
                        <h3 className="bg-blue-500 text-white text-center p-2 rounded-md">
                            Historial Clínico
                        </h3>
                    </div>
                </div>

                {/* Derecha */}
                <div className="flex-1 flex flex-col gap-2 p-2">
                    <div className="grid grid-cols-2 gap-2">
                        <div className="bg-white p-2 border border-gray-300 rounded-md shadow-md">
                            <h3 className="bg-blue-500 text-white text-center p-2 rounded-md">
                                Pedidos Laboratorio
                            </h3>
                        </div>
                        <div className="bg-white p-2 border border-gray-300 rounded-md shadow-md">
                            <h3 className="bg-blue-500 text-white text-center p-2 rounded-md">
                                Informes Anteriores
                            </h3>
                        </div>
                        <div className="bg-white p-2 border border-gray-300 rounded-md shadow-md">
                            <h3 className="bg-blue-500 text-white text-center p-2 rounded-md">
                                Receta Digital
                            </h3>
                        </div>
                        <div className="bg-white p-2 border border-gray-300 rounded-md shadow-md">
                            <h3 className="bg-blue-500 text-white text-center p-2 rounded-md">
                                Plantillas Evolución
                            </h3>
                        </div>
                    </div>
                    <div className="flex-1 bg-gray-100 p-2 border border-gray-300 rounded-md flex items-center justify-center">
                        Comentarios
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
        </div>
    );
}

export default MainPage;
