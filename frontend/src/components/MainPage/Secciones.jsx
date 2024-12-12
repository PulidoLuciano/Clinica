import React, { useEffect, useState } from "react";
import SeccionRecetas from "../../components/MainPage/SeccionRecetas";
import SeccionPedidos from "../../components/MainPage/SeccionPedidos";
import SeccionEvoluciones from "./SeccionEvoluciones";
import { pacienteService } from "../../service/pacienteService";
const Secciones = ({ cuilPaciente, diagnosticoActivo, setDiagnosticoActivo }) => {
  const [evoluciones, setEvoluciones] = useState([]);
  
  useEffect(() => {
    async function getEvoluciones(diagnosticoActivo) {
      try {
        let data;
        if (diagnosticoActivo == "Todos") {
          data = await pacienteService.getEvoluciones(cuilPaciente);
        } else {
          data = await pacienteService.getEvolucionesPorDiagnostico(
            cuilPaciente,
            diagnosticoActivo
          );
        }
        setEvoluciones(data);
      } catch (error) {
        console.error(error.message);
      }
    }
    if (cuilPaciente) {
      getEvoluciones(diagnosticoActivo);
    } else {
      setEvoluciones([]);
    }
  }, [cuilPaciente, diagnosticoActivo]);

  const getPedidos = () => {
    var pedidos = [];
    if (evoluciones) {
      evoluciones.forEach(evolucion => {
        const pedido = evolucion.pedidoLaboratorio;
        if(pedido) pedidos = pedidos.concat({...pedido, fechaEvolucion: evolucion.fecha}); 
      })
    }
    return pedidos;
  };

  return (
    <section className="flex flex-1 gap-2 bg-white overflow-y-auto p-4">
      <SeccionEvoluciones
        evoluciones={evoluciones}
        cuilPaciente={cuilPaciente}
        diagnosticoActivo={diagnosticoActivo}
        setDiagnosticoActivo={setDiagnosticoActivo}
      />
      <div className="flex-1 bg-gray-100 p-4 border border-gray-300 rounded-md overflow-y-auto relative">
        <div className="flex flex-col h-full">
          <SeccionRecetas evoluciones={evoluciones} />
          <SeccionPedidos pedidos={getPedidos()} />
        </div>
      </div>
    </section>
  );
};

export default Secciones;
