import React, { useState, useEffect } from "react";
import { fetchHistoriaClinica } from "../api/historiaClinica";
import ModalAgregarEvolucion from "../components/Modal/ModalAgregarEvolucion";
import Navbar from "../components/Navbar/Navbar";

function HistoriaClinicaPage() {
  const [paciente, setPaciente] = useState(null);
  const [historiaClinica, setHistoriaClinica] = useState(null);
  const [modalEvolucionVisible, setModalEvolucionVisible] = useState(false);
  const [diagnosticoVisible, setDiagnosticoVisible] = useState({});
  const [recargar, setRecargar] = useState(false); // Estado para controlar la recarga

  useEffect(() => {
    const obtenerHistoriaClinica = async () => {
      if (paciente) {
        const token = sessionStorage.getItem("jwt");
        try {
          const historia = await fetchHistoriaClinica(paciente.cuil, token);
          setHistoriaClinica(historia);
        } catch (error) {
          console.error("Error al obtener la historia clínica:", error);
        }
      }
    };

    obtenerHistoriaClinica();
  }, [paciente, recargar]); // Agregar `recargar` como dependencia

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
    <main className="flex flex-col h-screen">
      <Navbar setPaciente={setPaciente}/>
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
                <strong>Nombre:</strong> {paciente.nombre} {paciente.apellido}
              </p>
              <p>
                <strong>DNI:</strong> {paciente.dni}
              </p>
              <p>
                <strong>CUIL:</strong> {paciente.cuil}
              </p>
              <p>
                <strong>Fecha de Nacimiento:</strong>{" "}
                {new Date(paciente.fechaNacimiento).toLocaleDateString()}
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
                <strong>Número de Afiliado:</strong> {paciente.numeroAfiliado}
              </p>
              <p>
                <strong>Obra Social:</strong> {paciente.obraSocial.denominacion}{" "}
                ({paciente.obraSocial.sigla})
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
                    <h4 className="font-semibold">Diagnósticos:</h4>
                    {historiaClinica.diagnosticos.map((diagnostico, index) => (
                      <div key={index}>
                        <p
                          className="cursor-pointer text-blue-500"
                          onClick={() => toggleDiagnostico(index)}
                        >
                          {diagnostico.nombre}
                        </p>
                        {diagnosticoVisible[index] && (
                          <div className="ml-4">
                            {historiaClinica.detalles
                              .filter(
                                (detalle) =>
                                  detalle.diagnostico.id === diagnostico.id
                              )
                              .map((detalle, idx) => (
                                <div key={idx} className="mb-2">
                                  {detalle.evoluciones.map((evolucion, idy) => (
                                    <div
                                      key={idy}
                                      className="ml-4 border-t border-gray-300 pt-2 mt-2"
                                    >
                                      <p>
                                        <strong>Fecha:</strong>{" "}
                                        {new Date(
                                          evolucion.fecha
                                        ).toLocaleDateString()}
                                      </p>
                                      <p>
                                        <strong>Texto:</strong>{" "}
                                        {evolucion.texto}
                                      </p>
                                      {evolucion.receta && (
                                        <div className="ml-4">
                                          <h5 className="font-semibold">
                                            Receta:
                                          </h5>
                                          <p>
                                            <strong>Código:</strong>{" "}
                                            {evolucion.receta.codigo}
                                          </p>
                                          {evolucion.receta.detalles.map(
                                            (detalleReceta, idr) => (
                                              <div key={idr} className="ml-4">
                                                <p>
                                                  <strong>Medicamento:</strong>{" "}
                                                  {
                                                    detalleReceta.medicamentos
                                                      .descripcion
                                                  }
                                                </p>
                                                <p>
                                                  <strong>Formato:</strong>{" "}
                                                  {
                                                    detalleReceta.medicamentos
                                                      .formato
                                                  }
                                                </p>
                                                <p>
                                                  <strong>Cantidad:</strong>{" "}
                                                  {detalleReceta.cantidad}
                                                </p>
                                              </div>
                                            )
                                          )}
                                        </div>
                                      )}
                                      {evolucion.pedidoLaboratorio && (
                                        <div className="ml-4">
                                          <h5 className="font-semibold">
                                            Pedido de Laboratorio:
                                          </h5>
                                          <p>
                                            {evolucion.pedidoLaboratorio.texto}
                                          </p>
                                        </div>
                                      )}
                                    </div>
                                  ))}
                                </div>
                              ))}
                          </div>
                        )}
                      </div>
                    ))}
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
                  onClick={() => setModalEvolucionVisible(true)}
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
                  {historiaClinica.recetas.map((receta, index) => (
                    <div
                      key={index}
                      className="mb-2 border-t border-gray-300 pt-2 mt-2"
                    >
                      <p>
                        <strong>Fecha:</strong>{" "}
                        {new Date(receta.fecha).toLocaleDateString()}
                      </p>
                      <p>
                        <strong>Código:</strong> {receta.codigo}
                      </p>
                      {receta.detalles.map((detalleReceta, idr) => (
                        <div key={idr} className="ml-4">
                          <p>
                            <strong>Medicamento:</strong>{" "}
                            {detalleReceta.medicamentos.descripcion}
                          </p>
                          <p>
                            <strong>Formato:</strong>{" "}
                            {detalleReceta.medicamentos.formato}
                          </p>
                          <p>
                            <strong>Cantidad:</strong> {detalleReceta.cantidad}
                          </p>
                        </div>
                      ))}
                    </div>
                  ))}
                </div>
              ) : (
                <p className="text-center">No hay recetas disponibles</p>
              )}
            </div>
            <div className="flex-1 overflow-y-auto">
              <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
                Pedidos de laboratorio
              </h3>
              {historiaClinica ? (
                <div className="p-2">
                  {historiaClinica.pedidosLaboratorio.map((pedido, index) => (
                    <div
                      key={index}
                      className="mb-2 border-t border-gray-300 pt-2 mt-2"
                    >
                      <p>{pedido.texto}</p>
                    </div>
                  ))}
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

      {/* Modal para agregar evolución */}
      <ModalAgregarEvolucion
        visible={modalEvolucionVisible}
        onClose={() => setModalEvolucionVisible(false)}
        onSubmit={handleCreateEvolucion}
        paciente={paciente} // Pasamos el paciente al modal
      />
    </main>
  );
}

export default HistoriaClinicaPage;
