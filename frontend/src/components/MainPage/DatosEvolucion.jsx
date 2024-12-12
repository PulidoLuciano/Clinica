import React, { useState } from 'react'
import flechaAbajo from "../../icons/flechaAbajo.svg"
import flechaArriba from "../../icons/flechaArriba.svg"

const DatosEvolucion = ({ evolucion }) => {
    const [visible, setVisible] = useState(false);
    function handleVisible() {
        setVisible(!visible)
    }

    return (

        <div className='border border-gray-300 p-3 rounded-md my-2'>
            <div>
                <div className='flex justify-between'>
                    <p><b>Fecha:</b> {evolucion.fecha}</p>
                    <button onClick={handleVisible} className=''>
                        <img src={visible ? flechaArriba : flechaAbajo} />
                    </button>
                </div>

                {!visible &&  <p className=''><b>Texto:</b> {evolucion.texto.length>80? `${evolucion.texto.substring(0,80)}...` :evolucion.texto}</p>}
                {console.log(evolucion.texto.length)}

            </div>

            {visible &&
                <div className='flex flex-col text-wrap '>
                    
                    <p className=''><b>Texto:</b> {evolucion.texto}</p>
                    {evolucion.pedidoLaboratio &&
                        <div className='flex gap-1'>
                            <label className='font-bold'>Pedido Laboratorio: </label>
                            <p>{evolucion.pedidoLaboratorio}</p>
                        </div>
                    }
                    {evolucion.receta &&
                        <div className='flex flex-col justify-items-start gap-1'>
                            <label className='font-bold'>Medicamentos Recetados:</label>
                            <ul>
                                {evolucion.receta.detalles.map((detalle, index) => {
                                    return <li key={index} className='flex gap-1'><p className='font-semibold'>{detalle.medicamentos.descripcion}:</p><p>{detalle.cantidad}</p></li>
                                })}

                            </ul>
                        </div>
                    }
                    <p><b>Médico:</b> {evolucion.medico.nombre + " " + evolucion.medico.apellido}</p>
                    
                </div>}

        </div>
    )
}

export default DatosEvolucion

