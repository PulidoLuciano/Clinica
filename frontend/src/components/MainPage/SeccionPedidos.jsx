import { useEffect, useState } from "react";
import DatosPedidosLab from "./DatosPedidosLab";

export default function SeccionPedidos({ pedidos }) {
  return (
    <div className="flex-1 ">
      <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
        Pedidos de laboratorio
      </h3>
      <div className="overflow-y-auto">
        {!pedidos ? (
          <p>No hay pedidos para mostrar</p>
        ) : (
          pedidos.map((pedido) =>
          (
            <DatosPedidosLab
              textoPedido={pedido.texto}
              fechaEvolucion={pedido.fechaEvolucion}
            />
          )
          )
        )}
      </div>

    </div>
  );
}
