import { useState } from "react";
import Navbar from "../components/Navbar/Navbar";
import DatosPaciente from "../components/MainPage/DatosPaciente";
import SeccionHistoriaClinica from "../components/MainPage/SeccionHistoriaClinica";

function HistoriaClinicaPage() {
  const [cuilPacienteActivo, setCuilPacienteActivo] = useState(null);

  return (
    <main className="flex flex-col h-screen">
      <Navbar/>
      <DatosPaciente setCuilPaciente={setCuilPacienteActivo}/>
      <SeccionHistoriaClinica cuilPaciente={cuilPacienteActivo}/>
    </main>
  );
}

export default HistoriaClinicaPage;
