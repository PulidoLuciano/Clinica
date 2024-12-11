export default function SeccionRecetas({ historiaClinica }) {
  return (
    <div className="flex-1 mb-4 overflow-y-auto">
      <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
        Recetas
      </h3>
      {historiaClinica ? (
        <div className="p-2">
          {historiaClinica.recetas.map((receta, index) => (
            <div
              key={index}
              className="mb-2 border-t border-gray-300 pt-2 mt-2"
            >
              <p>
                <strong>Fecha:</strong>{" "}
                {new Date(receta.fecha).toLocaleDateString()}
              </p>
              <p>
                <strong>Código:</strong> {receta.codigo}
              </p>
              {receta.detalles.map((detalleReceta, idr) => (
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
                    <strong>Cantidad:</strong> {detalleReceta.cantidad}
                  </p>
                </div>
              ))}
            </div>
          ))}
        </div>
      ) : (
        <p className="text-center">No hay recetas disponibles</p>
      )}
    </div>
  );
}
