import { useState } from "react";
import ModalAgregarEvolucion from "../../components/Modal/ModalAgregarEvolucion";
import Diagnostico from "./Diagnostico";

export default function SeccionHistoriaClinica({ paciente, historiaClinica }) {
  const [modalEvolucionVisible, setModalEvolucionVisible] = useState(false);
  const [recargar, setRecargar] = useState(false);

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

  return (
    <>
      <section className="flex-1 bg-gray-100 p-4 border border-gray-300 rounded-md overflow-y-auto relative">
        <div className="flex flex-col h-full">
          <div className="flex-1 mb-4 overflow-y-auto">
            <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
              Historia clínica
            </h3>
            {historiaClinica ? (
              <div className="p-2">
                <p>
                  <strong>Fecha de Creación:</strong>{" "}
                  {new Date(historiaClinica.fechaCreacion).toLocaleDateString()}
                </p>
                <div>
                  <h4 className="font-semibold">Diagnósticos:</h4>
                  {historiaClinica.diagnosticos.map((diagnostico, index) => (
                    <Diagnostico diagnostico={diagnostico} key={index}/>
                  ))}
                </div>
              </div>
            ) : (
              <p className="text-center">No hay historia clínica disponible</p>
            )}
          </div>
          <div className="flex-shrink-0">
            {historiaClinica && (
              <button
                onClick={() => setModalEvolucionVisible(true)}
                className="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2 w-full"
              >
                Agregar evolución
              </button>
            )}
          </div>
        </div>
      </section>
      <ModalAgregarEvolucion
        visible={modalEvolucionVisible}
        onClose={() => setModalEvolucionVisible(false)}
        onSubmit={handleCreateEvolucion}
        paciente={paciente} // Pasamos el paciente al modal
      />
    </>
  );
}
