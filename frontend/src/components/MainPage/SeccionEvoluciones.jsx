import { useState } from "react";
import ModalAgregarEvolucion from "../Modal/ModalAgregarEvolucion";
import DatosEvolucion from "./DatosEvolucion";

export default function SeccionEvoluciones({
  evoluciones,
  cuilPaciente,
  diagnosticoActivo,
  setDiagnosticoActivo
}) {
  const [modalEvolucionVisible, setModalEvolucionVisible] = useState(false);
  
  return (
    <>
      <section className="col-span-6 row-span-10 bg-gray-100 p-4 border border-gray-300 rounded-md relative">
        <div className="flex flex-col  h-full">
          <div className="flex-1 mb-4">
            <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
              Evoluciones
            </h3>
          </div>
          <div className="overflow-y-auto overflow-x-hidden text-wrap">
            {cuilPaciente && evoluciones ? (
              evoluciones.map((evolucion) => {
                return <DatosEvolucion evolucion={evolucion} />;
              })
            ) : (
              <p>No hay evoluciones que mostrar</p>
            )}
          </div>
          <div className="flex-shrink-0">
            {cuilPaciente && (
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
        cuilPaciente={cuilPaciente}
        diagnosticoActivo={diagnosticoActivo}
        setDiagnosticoActivo={setDiagnosticoActivo}
      />
    </>
  );
}
