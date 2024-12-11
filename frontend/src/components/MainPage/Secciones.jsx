import React, { useState } from 'react'
import SeccionRecetas from "../../components/MainPage/SeccionRecetas";
import SeccionPedidos from "../../components/MainPage/SeccionPedidos";
import SeccionEvoluciones from "./SeccionEvoluciones";
const Secciones = ({cuilPaciente,diagnosticoActivo}) => {
  const [evoluciones,setEvoluciones] = useState(null);
    return (
    <section className="flex flex-1 gap-2 bg-white overflow-y-auto p-4">
        <SeccionEvoluciones evoluciones={evoluciones} setEvoluciones={setEvoluciones} cuilPaciente={cuilPaciente} diagnosticoActivo={diagnosticoActivo}/>
        <div className="flex-1 bg-gray-100 p-4 border border-gray-300 rounded-md overflow-y-auto relative">
          <div className="flex flex-col h-full">
            <SeccionRecetas evoluciones={evoluciones} diagnosticoActivo={diagnosticoActivo}  />
            <SeccionPedidos  evoluciones={evoluciones} diagnosticoActivo={diagnosticoActivo} />
          </div>
        </div>
      </section>
  )
}

export default Secciones