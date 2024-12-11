import { useEffect, useState } from "react";
import DatosPedidosLab from "./DatosPedidosLab";
import { pacienteService } from "../../service/pacienteService";

export default function SeccionPedidos({ evoluciones, diagnosticoActivo }) {

  const [pedidos, setPedidos] = useState(null)
  const [error, setError] = useState(null)

  useEffect(() => {

    if(evoluciones){
      const pedidosData = evoluciones.map(evolucion => {
        if (evolucion.pedidoLaboratorio)
          return {
            texto: evolucion.pedidoLaboratorio.texto,
            fechaEvolucion: evolucion.fecha,
          }
      });
      console.log(pedidosData);
      
      if(pedidosData){
        setPedidos(pedidosData)
      }
    }

  }, [diagnosticoActivo]);

  console.log(pedidos);

  return (
    <div className="flex-1 overflow-y-auto">
      <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
        Pedidos de laboratorio
      </h3>

      {error ?
        <p>{error}</p>
        :
        pedidos? pedidos.map(pedido => <DatosPedidosLab textoPedido={pedido.texto} fechaEvolucion={pedido.fechaEvolucion} />)
        : 
        <></>
      } 

      
    </div>
  );
}

/*

{historiaClinica ? (
        <div className="p-2">
          {historiaClinica.pedidosLaboratorio.map((pedido, index) => (
            <div
              key={index}
              className="mb-2 border-t border-gray-300 pt-2 mt-2"
            >
              <p>{pedido.texto}</p>
            </div>
          ))}
        </div>
      ) : (
        <p className="text-center">No hay pedidos de laboratorio disponibles</p>
      )}
*/
