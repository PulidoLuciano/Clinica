import React, { useState } from "react";
import { useAuth } from "../../contexts/AuthContext";
import { fetchPaciente } from "../../api/pacientes";
import ModalCrearPaciente from "../Modal/ModalCrearPaciente";

function Navbar({ setPaciente }) {
    
    const auth = useAuth();
    const [modalVisible, setModalVisible] = useState(false);
    
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
    
    const handleSearch = async (event) => {
        event.preventDefault();
        const cuil = event.target.cuil.value.trim();
        if (cuil) {
            const pacienteEncontrado = await fetchPaciente(cuil);
      
            if (!pacienteEncontrado) {
              setModalVisible(true); // Mostrar modal si no existe el paciente
              return;
            }
            setPaciente(
                {
                    ...pacienteEncontrado,
                    edad: calcularEdad(pacienteEncontrado.fechaNacimiento)
                }
            );
        }
    };

    const handleLogout = () => {
        if (window.confirm("¿Estás seguro de que deseas cerrar sesión?")) {
          auth.logout();
        }
    };

    const handleCreatePaciente = async () => {
        try {
          await createPaciente(nuevoPaciente);
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
    
    return(
        <>
            <nav className="bg-white shadow-lg flex justify-between h-16 max-w-7xl w-full mx-auto px-4">
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
                        <form onSubmit={handleSearch} className="flex gap-2">
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
            </nav>

            {/* Modal para crear paciente */}
            <ModalCrearPaciente
            visible={modalVisible}
            onClose={() => setModalVisible(false)}
            onCreate={handleCreatePaciente}
            pacienteData={nuevoPaciente}
            onChange={handleChangeNuevoPaciente}
            />
        </>
    );
}

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

export default Navbar;
