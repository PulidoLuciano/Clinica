import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { authenticateUser } from "../../api/auth"; // Importar la función de autenticación

function LoginPage() {
    const navigate = useNavigate();
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm();
    const [authError, setAuthError] = useState(null); // Estado para manejar errores de autenticación

    const onSubmit = async (data) => {
        const { isAuthenticated, error } = await authenticateUser(
            data.username,
            data.password
        );
        if (isAuthenticated) {
            navigate("/inicio");
        } else {
            setAuthError(error);
        }
    };

    return (
        <div
            className="flex h-screen bg-cover bg-center"
            style={{ backgroundImage: "url('/medicina.webp')" }}
        >
            <div className="flex-1 flex items-center justify-center p-5 bg-white bg-opacity-75">
                <form
                    onSubmit={handleSubmit(onSubmit)}
                    className="w-full max-w-md bg-white p-5 rounded-lg shadow-md"
                >
                    <h2 className="text-2xl font-bold mb-5">Iniciar sesión</h2>

                    {/* Campo Usuario */}
                    <div className="mb-4">
                        <label
                            htmlFor="username"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Usuario
                        </label>
                        <input
                            id="username"
                            type="text"
                            placeholder="Ingrese su usuario"
                            {...register("username", {
                                required: "El usuario es obligatorio",
                            })}
                            className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                        />
                        {errors.username && (
                            <p className="text-red-500 text-xs mt-1">
                                {errors.username.message}
                            </p>
                        )}
                    </div>

                    {/* Campo Contraseña */}
                    <div className="mb-4">
                        <label
                            htmlFor="password"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Contraseña
                        </label>
                        <input
                            id="password"
                            type="password"
                            placeholder="Ingrese su contraseña"
                            {...register("password", {
                                required: "La contraseña es obligatoria",
                            })}
                            className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                        />
                        {errors.password && (
                            <p className="text-red-500 text-xs mt-1">
                                {errors.password.message}
                            </p>
                        )}
                    </div>

                    {/* Mostrar error de autenticación */}
                    {authError && (
                        <p className="text-red-500 text-xs mt-1">{authError}</p>
                    )}

                    {/* Botón de Ingreso */}
                    <button
                        type="submit"
                        className="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                    >
                        Ingresar
                    </button>
                </form>
            </div>
        </div>
    );
}

export default LoginPage;
