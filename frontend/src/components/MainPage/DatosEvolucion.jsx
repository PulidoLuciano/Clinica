import React, { useState } from 'react'
import flechaAbajo from "../../icons/flechaAbajo.svg"
import flechaArriba from "../../icons/flechaArriba.svg"
import DatosReceta from './DatosReceta';
import telefono from "../../icons/telefono.svg"
import emailIcon from "../../icons/emailIcon.svg"
import pillIcon from "../../icons/pillIcon.svg"
import pedidoLabIcon from "../../icons/pedidoLabIcon.svg"

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

                {!visible && <p className='truncate'><b>Texto:</b> {evolucion.texto}</p>}
                

            </div>

            {visible &&
                <div className='flex flex-col text-wrap'>

                    <p><b>Texto:</b> {evolucion.texto}</p>
                    {evolucion.pedidoLaboratorio &&
                        <div className='flex gap-1'>
                            <label className='flex font-bold'><img src={pedidoLabIcon}/>Pedido Laboratorio: </label>
                            <p>{evolucion.pedidoLaboratorio.texto}</p>
                        </div>
                    }
                    {evolucion.receta &&
                        <div className='flex flex-col'>
                            <p className='font-bold'>Medicamentos:</p>
                            <ul>
                                {
                                    evolucion.receta.detalles.map((detalleReceta, idr) => (

                                        <li className='flex pl-5' key={idr}><img src={pillIcon}/>{detalleReceta.medicamentos.descripcion} x{detalleReceta.cantidad}</li>

                                    ))
                                }
                            </ul>
                        </div>

                    }
                    <p><b>Médico:</b> {evolucion.medico.nombre + " " + evolucion.medico.apellido}</p>
                    <p><b>Especialidad:</b> {evolucion.medico.especialidad.nombre}</p>
                    <div className='flex flex-col'>
                        <p className='font-bold'>Contactos Médico:</p>
                        <ul className='pl-5'>
                            <li className='flex'><img src={emailIcon} /><b>E-mail:</b> {evolucion.medico.email}</li>
                            <li className='flex'><img src={telefono} /><b>Teléfono:</b> {evolucion.medico.telefono}</li>
                        </ul>
                    </div>

                </div>}

        </div>
    )
}

export default DatosEvolucion

