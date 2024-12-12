import React from 'react'

const DatosPedidosLab = ({textoPedido,fechaEvolucion}) => {
  return (
    <div key={fechaEvolucion} className='border rounded-md my-2 border-gray-300 p-2 flex flex-col'>
        <p><b>Fecha:</b> {fechaEvolucion}</p>
        <p><b>Texto:</b> {textoPedido}</p>
    </div>
  )
}

export default DatosPedidosLab