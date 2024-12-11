import { useState } from "react";
import SeccionRecetas from "../../components/MainPage/SeccionRecetas";
import SeccionPedidos from "../../components/MainPage/SeccionPedidos";
import SeccionEvoluciones from "./SeccionEvoluciones";
import SeccionDiagnosticos from "./SeccionDiagnosticos";

export default function SeccionHistoriaClinica({ cuilPaciente }) {
  const [diagnosticoActivo, setDiagnosticoActivo] = useState("Todos");
  
  return (
    <>
      <SeccionDiagnosticos cuilPaciente={cuilPaciente} diagnosticoActivo={diagnosticoActivo} setDiagnosticoActivo={setDiagnosticoActivo}/>
      <section className="flex flex-1 gap-2 bg-white overflow-y-auto p-4">
        <SeccionEvoluciones cuilPaciente={cuilPaciente} diagnosticoActivo={diagnosticoActivo}/>
        <div className="flex-1 bg-gray-100 p-4 border border-gray-300 rounded-md overflow-y-auto relative">
          <div className="flex flex-col h-full">
            <SeccionRecetas />
            <SeccionPedidos />
          </div>
        </div>
      </section>
    </>
  );
}
