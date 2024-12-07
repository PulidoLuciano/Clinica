import React, { useState, useEffect } from "react";

function ModalAgregarEvolucion({ visible, onClose, onSubmit, paciente }) {
    const [texto, setTexto] = useState("");
    const [medicamentos, setMedicamentos] = useState([]);
    const [textoPedidoLaboratorio, setTextoPedidoLaboratorio] = useState("");
    const [busquedaMedicamento, setBusquedaMedicamento] = useState("");
    const [medicamentosEncontrados, setMedicamentosEncontrados] = useState([]);
    const [tipoEvolucion, setTipoEvolucion] = useState(""); // 'receta' o 'laboratorio'
    const [diagnosticoSeleccionado, setDiagnosticoSeleccionado] = useState("");
    const [diagnosticos, setDiagnosticos] = useState([]);
    const [nuevoDiagnostico, setNuevoDiagnostico] = useState("");
    const [timeoutId, setTimeoutId] = useState(null);
    const [buscando, setBuscando] = useState(false);

    useEffect(() => {
        const cargarDiagnosticos = async () => {
            try {
                const url = `http://localhost:8080/diagnosticos`;
                const response = await fetch(url, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${sessionStorage.getItem(
                            "jwt"
                        )}`, // Agregar encabezado de autenticación
                    },
                });
                if (!response.ok) {
                    throw new Error("Error en la respuesta del servidor");
                }
                const data = await response.json();
                setDiagnosticos(data);
            } catch (error) {
                console.error("Error al cargar diagnósticos:", error);
            }
        };

        cargarDiagnosticos();
    }, []);

    const buscarMedicamentos = async (descripcion) => {
        if (!descripcion.trim()) {
            setMedicamentosEncontrados([]);
            return;
        }

        setBuscando(true);
        try {
            const url = `http://localhost:8080/medicamentos?descripcion=${descripcion}`;
            const response = await fetch(url, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${sessionStorage.getItem("jwt")}`,
                },
            });
            console.log("Response status:", response.status); // Agregar log para depuración
            const data = await response.json();
            console.log("Datos obtenidos:", data); // Agregar log para depuración
            setMedicamentosEncontrados(data);
        } catch (error) {
            console.error("Error al buscar medicamentos:", error);
            setMedicamentosEncontrados([]);
        } finally {
            setBuscando(false);
        }
    };

    const crearDiagnostico = async (nombre) => {
        try {
            const url = `http://localhost:8080/diagnosticos`;
            const response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${sessionStorage.getItem("jwt")}`, // Agregar encabezado de autenticación
                },
                body: JSON.stringify({ nombre }),
            });
            if (!response.ok) {
                throw new Error("Error al crear diagnóstico");
            }
            const data = await response.json();
            console.log("Diagnóstico creado:", data); // Agregar log para depuración
            setDiagnosticoSeleccionado(data.nombre);
            setDiagnosticos([...diagnosticos, data]);
        } catch (error) {
            console.error("Error al crear diagnóstico:", error);
        }
    };

    const agregarMedicamento = (medicamento) => {
        if (medicamentos.length < 2) {
            setMedicamentos([...medicamentos, { ...medicamento, cantidad: 1 }]);
            setBusquedaMedicamento("");
            setMedicamentosEncontrados([]);
        } else {
            alert("No se pueden agregar más de 2 medicamentos");
        }
    };

    const eliminarMedicamento = (index) => {
        setMedicamentos(medicamentos.filter((_, i) => i !== index));
    };

    const actualizarCantidad = (index, cantidad) => {
        const nuevosMedicamentos = [...medicamentos];
        const cantidadNum = parseInt(cantidad);
        if (cantidadNum > 0) {
            nuevosMedicamentos[index].cantidad = cantidadNum;
            setMedicamentos(nuevosMedicamentos);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!texto.trim()) {
            alert("El texto de la evolución no puede estar vacío");
            return;
        }

        if (!diagnosticoSeleccionado) {
            alert("Debe seleccionar un diagnóstico");
            return;
        }

        if (tipoEvolucion === "") {
            alert(
                "Debe seleccionar un tipo de evolución (Receta o Laboratorio)"
            );
            return;
        }

        if (tipoEvolucion === "receta" && medicamentos.length === 0) {
            alert("Debe agregar al menos un medicamento a la receta");
            return;
        }

        if (tipoEvolucion === "laboratorio" && !textoPedidoLaboratorio.trim()) {
            alert("El texto del pedido de laboratorio no puede estar vacío");
            return;
        }

        const evolucion = {
            texto,
            medicamentosReceta:
                tipoEvolucion === "receta"
                    ? medicamentos.map((m) => ({
                          codigoMedicamento: m.codigo,
                          cantidad: m.cantidad,
                      }))
                    : undefined,
            textoPedidoLaboratorio:
                tipoEvolucion === "laboratorio"
                    ? textoPedidoLaboratorio
                    : undefined,
        };

        console.log("Evolución a enviar:", JSON.stringify(evolucion, null, 2));

        try {
            const url = `http://localhost:8080/pacientes/${paciente.cuil}/historia-clinica/${diagnosticoSeleccionado}/evolucion`;
            console.log("URL de envío:", url);
            const response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${sessionStorage.getItem("jwt")}`,
                },
                body: JSON.stringify(evolucion),
            });
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(
                    `Error al crear evolución: ${
                        errorData.message || response.statusText
                    }`
                );
            }
            const data = await response.json();
            console.log("Evolución creada:", data);
            onSubmit(data);
            limpiarFormulario();
        } catch (error) {
            console.error("Error al crear evolución:", error);
            alert(`Error al crear evolución: ${error.message}`);
        }
    };

    const limpiarFormulario = () => {
        setTexto("");
        setMedicamentos([]);
        setTextoPedidoLaboratorio("");
        setBusquedaMedicamento("");
        setMedicamentosEncontrados([]);
        setTipoEvolucion("");
        setDiagnosticoSeleccionado("");
        setNuevoDiagnostico("");
    };

    if (!visible) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex justify-center items-center z-50">
            <div className="bg-white p-6 rounded-lg shadow-lg w-[500px] max-h-[90vh] overflow-y-auto">
                <div className="flex justify-between items-center mb-4">
                    <h2 className="text-xl font-bold">Agregar Evolución</h2>
                    <button
                        onClick={() => {
                            onClose();
                            limpiarFormulario();
                        }}
                        className="text-gray-500 hover:text-gray-700"
                    >
                        ✕
                    </button>
                </div>

                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2">
                            Texto de la evolución *
                        </label>
                        <textarea
                            value={texto}
                            onChange={(e) => setTexto(e.target.value)}
                            className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            rows="4"
                            required
                            placeholder="Ingrese el texto de la evolución"
                        />
                    </div>

                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2">
                            Diagnóstico *
                        </label>
                        <div className="relative">
                            <select
                                value={diagnosticoSeleccionado}
                                onChange={(e) =>
                                    setDiagnosticoSeleccionado(e.target.value)
                                }
                                className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            >
                                <option value="">
                                    Seleccione un diagnóstico
                                </option>
                                {diagnosticos.map((diag) => (
                                    <option key={diag.id} value={diag.nombre}>
                                        {diag.nombre}
                                    </option>
                                ))}
                            </select>
                        </div>
                        {diagnosticoSeleccionado === "" && (
                            <div className="mt-4">
                                <label className="block text-gray-700 text-sm font-bold mb-2">
                                    Crear nuevo diagnóstico
                                </label>
                                <input
                                    type="text"
                                    value={nuevoDiagnostico}
                                    onChange={(e) =>
                                        setNuevoDiagnostico(e.target.value)
                                    }
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                                    placeholder="Nombre del nuevo diagnóstico"
                                />
                                <button
                                    type="button"
                                    onClick={() =>
                                        crearDiagnostico(nuevoDiagnostico)
                                    }
                                    className="mt-2 px-4 py-2 bg-green-500 text-white rounded-lg hover:bg-green-600"
                                >
                                    Crear Diagnóstico
                                </button>
                            </div>
                        )}
                    </div>

                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2">
                            Tipo de evolución *
                        </label>
                        <div className="flex gap-4">
                            <button
                                type="button"
                                onClick={() => {
                                    setTipoEvolucion("receta");
                                    setTextoPedidoLaboratorio("");
                                }}
                                className={`flex-1 px-4 py-2 rounded-lg ${
                                    tipoEvolucion === "receta"
                                        ? "bg-blue-500 text-white"
                                        : "bg-gray-200 hover:bg-gray-300"
                                }`}
                            >
                                Receta
                            </button>
                            <button
                                type="button"
                                onClick={() => {
                                    setTipoEvolucion("laboratorio");
                                    setMedicamentos([]);
                                }}
                                className={`flex-1 px-4 py-2 rounded-lg ${
                                    tipoEvolucion === "laboratorio"
                                        ? "bg-blue-500 text-white"
                                        : "bg-gray-200 hover:bg-gray-300"
                                }`}
                            >
                                Laboratorio
                            </button>
                        </div>
                    </div>

                    {tipoEvolucion === "receta" && (
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-bold mb-2">
                                Buscar medicamento *
                            </label>
                            <div className="relative">
                                <input
                                    type="text"
                                    value={busquedaMedicamento}
                                    onChange={(e) => {
                                        const valor = e.target.value;
                                        setBusquedaMedicamento(valor);

                                        if (timeoutId) clearTimeout(timeoutId);

                                        const nuevoTimeoutId = setTimeout(
                                            () => {
                                                buscarMedicamentos(valor);
                                            },
                                            300
                                        );

                                        setTimeoutId(nuevoTimeoutId);
                                    }}
                                    className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                                    placeholder="Escriba para buscar medicamentos"
                                />
                                {buscando && (
                                    <div className="absolute right-3 top-2">
                                        <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-blue-500"></div>
                                    </div>
                                )}
                            </div>
                            {medicamentosEncontrados.length > 0 && (
                                <div className="mt-2 border rounded-lg max-h-40 overflow-y-auto">
                                    {medicamentosEncontrados.map((med) => (
                                        <div
                                            key={med.codigo}
                                            onClick={() =>
                                                agregarMedicamento(med)
                                            }
                                            className="p-3 hover:bg-gray-100 cursor-pointer border-b last:border-b-0"
                                        >
                                            <p className="font-medium">
                                                {med.descripcion}
                                            </p>
                                            <p className="text-sm text-gray-600">
                                                {med.formato}
                                            </p>
                                        </div>
                                    ))}
                                </div>
                            )}
                            <div className="mt-4 space-y-2">
                                {medicamentos.map((med, index) => (
                                    <div
                                        key={index}
                                        className="flex items-center gap-2 p-2 border rounded-lg"
                                    >
                                        <div className="flex-1">
                                            <p className="font-medium">
                                                {med.descripcion}
                                            </p>
                                            <p className="text-sm text-gray-600">
                                                {med.formato}
                                            </p>
                                        </div>
                                        <input
                                            type="number"
                                            min="1"
                                            value={med.cantidad}
                                            onChange={(e) =>
                                                actualizarCantidad(
                                                    index,
                                                    e.target.value
                                                )
                                            }
                                            className="w-20 px-2 py-1 border rounded-lg text-center"
                                        />
                                        <button
                                            type="button"
                                            onClick={() =>
                                                eliminarMedicamento(index)
                                            }
                                            className="text-red-500 hover:text-red-700 p-1"
                                        >
                                            ✕
                                        </button>
                                    </div>
                                ))}
                            </div>
                        </div>
                    )}

                    {tipoEvolucion === "laboratorio" && (
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-bold mb-2">
                                Texto del pedido de laboratorio *
                            </label>
                            <textarea
                                value={textoPedidoLaboratorio}
                                onChange={(e) =>
                                    setTextoPedidoLaboratorio(e.target.value)
                                }
                                className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                                rows="4"
                                placeholder="Ingrese el texto del pedido de laboratorio"
                            />
                        </div>
                    )}

                    <div className="flex justify-end gap-2">
                        <button
                            type="button"
                            onClick={() => {
                                onClose();
                                limpiarFormulario();
                            }}
                            className="px-4 py-2 bg-gray-300 text-gray-700 rounded-lg hover:bg-gray-400"
                        >
                            Cancelar
                        </button>
                        <button
                            type="submit"
                            className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
                        >
                            Guardar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default ModalAgregarEvolucion;
