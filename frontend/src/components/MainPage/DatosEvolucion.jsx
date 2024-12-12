import React, { useState } from 'react'
import flechaAbajo from "../../icons/flechaAbajo.svg"
import flechaArriba from "../../icons/flechaArriba.svg"

const DatosEvolucion = ({ evolucion }) => {
    const [visible, setVisible] = useState(false);
    function handleVisible(){
        setVisible(!visible)
    }
    
    return (

        <div className='border border-gray-300 p-3 rounded-md my-2'>
            <button onClick={handleVisible} className='flex gap-1'>
                <p className='font-semibold'>Fecha:</p>
                <p>{evolucion.fecha}</p>
                <img src={visible? flechaArriba : flechaAbajo} />
             </button>
            {visible && 
            <div className='flex flex-col'>
                <div className='flex gap-1'>
                    <label className='font-semibold'> Realizada por:</label>
                    <p>{evolucion.medico.nombre + " " + evolucion.medico.apellido}</p>
                </div>
                <div className='flex gap-1'>
                    <label className='font-semibold'> Texto:</label>
                    <p>{evolucion.texto}</p>
                </div>
                <div className='flex gap-1'>
                    <label className='font-semibold'>Fecha: </label>
                    <p>{evolucion.fecha}</p>
                </div>
                {evolucion.pedidoLaboratio &&
                    <div className='flex gap-1'>
                        <label className='font-semibold'>Pedido Laboratorio: </label>
                        <p>{evolucion.pedidoLaboratorio}</p>
                    </div>}
                {evolucion.receta &&
                    <div className='flex flex-col  justify-items-start gap-1'>
                        <label className='font-semibold'>Medicamentos Recetados:</label>
                        <ul>
                            {evolucion.receta.detalles.map((detalle, index) => {
                                return <li key={index} className='flex gap-1'><p className='font-semibold'>{detalle.medicamentos.descripcion}:</p><p>{detalle.cantidad}</p></li>
                            })}

                        </ul>
                    </div>
                }
            </div>}

        </div>
    )
}

export default DatosEvolucion

