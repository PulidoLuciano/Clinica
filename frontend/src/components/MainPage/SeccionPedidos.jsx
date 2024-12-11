export default function SeccionPedidos({ historiaClinica }) {
  return (
    <div className="flex-1 overflow-y-auto">
      <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
        Pedidos de laboratorio
      </h3>
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
    </div>
  );
}
