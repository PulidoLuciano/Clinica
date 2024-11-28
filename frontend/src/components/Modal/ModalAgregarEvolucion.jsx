import React, { useState, useEffect } from "react";

function ModalAgregarEvolucion({ visible, onClose, onSubmit }) {
    const [texto, setTexto] = useState("");
    const [medicamentos, setMedicamentos] = useState([]);
    const [textoPedidoLaboratorio, setTextoPedidoLaboratorio] = useState("");
    const [busquedaMedicamento, setBusquedaMedicamento] = useState("");
    const [medicamentosEncontrados, setMedicamentosEncontrados] = useState([]);
    const [tipoEvolucion, setTipoEvolucion] = useState(""); // 'receta' o 'laboratorio'
    const [timeoutId, setTimeoutId] = useState(null);
    const [buscando, setBuscando] = useState(false);

    useEffect(() => {
        return () => {
            if (timeoutId) clearTimeout(timeoutId);
        };
    }, [timeoutId]);

    const buscarMedicamentos = async (descripcion) => {
        if (descripcion.length < 3) return;
        if (!descripcion.trim()) {
            setMedicamentosEncontrados([]);
            return;
        }

        setBuscando(true);
        try {
            const descripcionCodificada = encodeURIComponent(descripcion);
            const url = `https://istp1service.azurewebsites.net/api/servicio-salud/medicamentos?descripcion=${descripcionCodificada}`;
            const proxyUrl = `https://cors-anywhere.herokuapp.com/${url}`;
            console.log("URL de búsqueda:", proxyUrl); // Agregar log para depuración

            const response = await fetch(proxyUrl, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    accept: "*/*",
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

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!texto.trim()) {
            alert("El texto de la evolución no puede estar vacío");
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
                    : [],
            textoPedidoLaboratorio:
                tipoEvolucion === "laboratorio" ? textoPedidoLaboratorio : null,
        };

        console.log("Evolución a enviar:", evolucion); // Agregar log para depuración

        onSubmit(evolucion);
        limpiarFormulario();
    };

    const limpiarFormulario = () => {
        setTexto("");
        setMedicamentos([]);
        setTextoPedidoLaboratorio("");
        setBusquedaMedicamento("");
        setMedicamentosEncontrados([]);
        setTipoEvolucion("");
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
