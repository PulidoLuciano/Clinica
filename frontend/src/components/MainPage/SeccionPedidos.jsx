import { useEffect, useState } from "react";
import DatosPedidosLab from "./DatosPedidosLab";

export default function SeccionPedidos({ pedidos }) {
  return (
    <section className="row-span-5 bg-gray-100 p-2">

      <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
        Pedidos de laboratorio
      </h3>
      <div className="h-4/5 overflow-y-auto">
        <div className="">
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

    </section>

  );
}
