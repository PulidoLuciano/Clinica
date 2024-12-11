import { useState } from "react";
import Navbar from "../components/Navbar/Navbar";
import DatosPaciente from "../components/MainPage/DatosPaciente";
import SeccionHistoriaClinica from "../components/MainPage/SeccionHistoriaClinica";
import SeccionRecetas from "../components/MainPage/SeccionRecetas";
import SeccionPedidos from "../components/MainPage/SeccionPedidos";

function HistoriaClinicaPage() {
  const [cuilPacienteActivo, setCuilPacienteActivo] = useState(null);

  /*useEffect(() => {
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
      else{
        setHistoriaClinica(null);
      }
    };
    obtenerHistoriaClinica();
  }, [paciente]);*/

  return (
    <main className="flex flex-col h-screen">
      <Navbar/>
      <DatosPaciente setCuilPaciente={setCuilPacienteActivo}/>
      <section className="flex flex-1 gap-5 bg-white overflow-y-auto p-4">
        <SeccionHistoriaClinica/>
        <div className="flex-1 bg-gray-100 p-4 border border-gray-300 rounded-md overflow-y-auto relative">
          <div className="flex flex-col h-full">
            <SeccionRecetas/>
            <SeccionPedidos/>
          </div>
        </div>
      </section>
    </main>
  );
}

export default HistoriaClinicaPage;

/*

      <section className="flex flex-1 gap-5 bg-white overflow-y-auto p-4">
        <DatosPaciente paciente={paciente}/>
        <SeccionHistoriaClinica historiaClinica={historiaClinica} paciente={paciente}/>
        <div className="flex-1 bg-gray-100 p-4 border border-gray-300 rounded-md overflow-y-auto relative">
          <div className="flex flex-col h-full">
            <SeccionRecetas historiaClinica={historiaClinica}/>
            <SeccionPedidos historiaClinica={historiaClinica}/>
          </div>
        </div>
      </section>

*/
