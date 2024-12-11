import { useState } from "react";

export default function Diagnostico({ diagnostico }) {
  const [diagnosticoVisible, setDiagnosticoVisible] = useState({});

  return (
    <div>
      <p
        className="cursor-pointer text-blue-500"
      >
        {diagnostico.nombre}
      </p>
    </div>
  );
}

/*
Esto era el toggle

{diagnosticoVisible[index] && (
        <div className="ml-4">
          {historiaClinica.detalles
            .filter((detalle) => detalle.diagnostico.id === diagnostico.id)
            .map((detalle, idx) => (
              <div key={idx} className="mb-2">
                {detalle.evoluciones.map((evolucion, idy) => (
                  <div
                    key={idy}
                    className="ml-4 border-t border-gray-300 pt-2 mt-2"
                  >
                    <p>
                      <strong>Fecha:</strong>{" "}
                      {new Date(evolucion.fecha).toLocaleDateString()}
                    </p>
                    <p>
                      <strong>Texto:</strong> {evolucion.texto}
                    </p>
                    {evolucion.receta && (
                      <div className="ml-4">
                        <h5 className="font-semibold">Receta:</h5>
                        <p>
                          <strong>Código:</strong> {evolucion.receta.codigo}
                        </p>
                        {evolucion.receta.detalles.map((detalleReceta, idr) => (
                          <div key={idr} className="ml-4">
                            <p>
                              <strong>Medicamento:</strong>{" "}
                              {detalleReceta.medicamentos.descripcion}
                            </p>
                            <p>
                              <strong>Formato:</strong>{" "}
                              {detalleReceta.medicamentos.formato}
                            </p>
                            <p>
                              <strong>Cantidad:</strong>{" "}
                              {detalleReceta.cantidad}
                            </p>
                          </div>
                        ))}
                      </div>
                    )}
                    {evolucion.pedidoLaboratorio && (
                      <div className="ml-4">
                        <h5 className="font-semibold">
                          Pedido de Laboratorio:
                        </h5>
                        <p>{evolucion.pedidoLaboratorio.texto}</p>
                      </div>
                    )}
                  </div>
                ))}
              </div>
            ))}
        </div>
      )}

*/
