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
        
        
        
        data.sort((a, b) => {
          const fechaA = new Date(a.fecha);
          const fechaB = new Date(b.fecha);
          return fechaB - fechaA;
      });
        
        data = data.map(e => {
          const fecha = new Date(e.fecha);
          return e = {...e,fecha:fecha.toLocaleDateString()};
        })
        
        
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

  const getRecetas = () =>{
    var recetas = [];
    if(evoluciones){
      evoluciones.forEach(evolucion =>{
        if(evolucion.receta){
          recetas.push(evolucion.receta);
        }
          
      });
    }
    return recetas;
  }

  return (
    <section className="h-3/5 grid grid-cols-10 grid-rows-10 gap-2 bg-white p-4">
      <SeccionEvoluciones
        evoluciones={evoluciones}
        cuilPaciente={cuilPaciente}
        diagnosticoActivo={diagnosticoActivo}
        setDiagnosticoActivo={setDiagnosticoActivo}
      />
      <div className="grid grid-rows-10 grid-cols-1 col-span-4 row-span-10 flex flex-col p-4 border  bg-gray-100 border-gray-300 rounded-md relative">
        
          <SeccionRecetas recetas={getRecetas()} />
          <SeccionPedidos pedidos={getPedidos()} />
       
      </div>
    </section>
  );
};

export default Secciones;
