import React, { useEffect, useState } from "react";
import SeccionRecetas from "../../components/MainPage/SeccionRecetas";
import SeccionPedidos from "../../components/MainPage/SeccionPedidos";
import SeccionEvoluciones from "./SeccionEvoluciones";
const Secciones = ({ cuilPaciente, diagnosticoActivo }) => {
  const [evoluciones, setEvoluciones] = useState([]);
  
  const getPedidos = () => {
    var pedidos = [];
    if (evoluciones) {
      evoluciones.forEach(evolucion => {
        const pedido = evolucion.pedidoLaboratorio;
        if(pedido) pedidos = pedidos.concat({...pedido, fechaEvolucion: evolucion.fecha}); 
      })
    }
    console.log(pedidos)
    return pedidos;
  };

  return (
    <section className="flex flex-1 gap-2 bg-white overflow-y-auto p-4">
      <SeccionEvoluciones
        evoluciones={evoluciones}
        setEvoluciones={setEvoluciones}
        cuilPaciente={cuilPaciente}
        diagnosticoActivo={diagnosticoActivo}
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
