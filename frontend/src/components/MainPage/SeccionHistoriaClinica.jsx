import { useState } from "react";

import SeccionDiagnosticos from "./SeccionDiagnosticos";
import Secciones from "./Secciones";

export default function SeccionHistoriaClinica({ cuilPaciente }) {
  const [diagnosticoActivo, setDiagnosticoActivo] = useState("Todos");
  
  return (
    <>
      <SeccionDiagnosticos cuilPaciente={cuilPaciente} diagnosticoActivo={diagnosticoActivo} setDiagnosticoActivo={setDiagnosticoActivo}/>
      <Secciones cuilPaciente={cuilPaciente} diagnosticoActivo={diagnosticoActivo}/>
    </>
  );
}
