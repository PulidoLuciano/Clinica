import React from "react";
import { useNavigate } from "react-router-dom";

function Navbar({ paciente, onSearch }) {
    const navigate = useNavigate();

    const handleLogout = () => {
        sessionStorage.removeItem("jwt");
        navigate("/");
    };

    return (
        <nav className="bg-white shadow-lg">
            <div className="max-w-7xl mx-auto px-4">
                <div className="flex justify-between h-16">
                    {/* Logo y nombre de la clínica */}
                    <div className="flex items-center">
                        <div className="flex-shrink-0 flex items-center">
                            <span className="text-xl font-semibold text-gray-800">
                                Clínica
                            </span>
                        </div>
                    </div>

                    {/* Información del paciente */}
                    <div className="flex items-center">
                        {paciente &&
                        paciente.nombre !== "Nombre del paciente" ? (
                            <div className="flex gap-4 items-center">
                                <div className="text-gray-700">
                                    <span className="font-medium">
                                        Paciente:{" "}
                                    </span>
                                    {paciente.nombre}
                                </div>
                                <div className="text-gray-700">
                                    <span className="font-medium">DNI: </span>
                                    {paciente.dni}
                                </div>
                                <div className="text-gray-700">
                                    <span className="font-medium">Edad: </span>
                                    {paciente.edad}
                                </div>
                                <div className="text-gray-700">
                                    <span className="font-medium">
                                        Obra Social:{" "}
                                    </span>
                                    {paciente.obraSocial}
                                </div>
                            </div>
                        ) : null}
                    </div>

                    {/* Buscador de paciente y botón de logout */}
                    <div className="flex items-center gap-4">
                        <form onSubmit={onSearch} className="flex gap-2">
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
                            className="text-gray-700 hover:text-gray-900 focus:outline-none"
                        >
                            Cerrar sesión
                        </button>
                    </div>
                </div>
            </div>
        </nav>
    );
}

export default Navbar;
