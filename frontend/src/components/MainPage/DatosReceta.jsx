import React from 'react'

const DatosReceta = ({receta}) => {
    return (
        <div
            
            className="mb-2 border rounded-md p-2 border-gray-300 mt-2"
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
    )
}

export default DatosReceta