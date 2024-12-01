import {useState} from 'react'
import { pacienteService } from '../service/pacienteService'

const usePaciente = () => {
  
    const [paciente,setPaciente] = useState(null)
    const [error,setError] = useState(null)
    const [loading,setLoading] = useState(false)
    const [diagnosticosPaciente,setDiagnosticosPaciente] = useState([]);
    const [historiaClinicaPaciente,setHistoriaClinicaPaciente] = useState(null);
    const [pedidosLaboratorioPaciente,setPedidosLaboratorioPaciente] = useState([])


    const getPacienteByCuil = async(cuil)=>{
        setLoading(true);
        setError(null)
        try {
            
            const data = await pacienteService.getPacienteByCuil(cuil);
            setPaciente(data);

        } catch (error) {
            setError(error.message);
        }finally{
            setLoading(false);
        }
    }

    const getDiagnosticosPaciente = async(cuil)=>{
        setLoading(true)
        setError(null)
        try {
            const data = pacienteService.getDiagnosticosPaciente(cuil);
            setDiagnosticosPaciente(data);
        } catch (error) {
            setError(error.message)
        }finally{
            setLoading(false)
        }
    }
    const getHistoriaClinicaPaciente = async(cuil)=>{
        setLoading(true)
        setError(null)
        try {
            const data = pacienteService.getHistoriaClinicaPaciente(cuil);
            setHistoriaClinicaPaciente(data);
        } catch (error) {
            setError(error.message)
        }finally{
            setLoading(false)
        }
    }

    const getPedidosLaboratorioPaciente = async(cuil)=>{
        setLoading(true)
        setError(null)
        try {
            const data = pacienteService.getPedidosLaboratorioPaciente(cuil);
            setPedidosLaboratorioPaciente(data);
        } catch (error) {
            setError(error.message)
        }finally{
            setLoading(false)
        }
    }

    const createEvolucion = async(cuil,diagnostico,evolucion)=>{
        setLoading(true)
        setError(null)
        try {
            await pacienteService.createEvolucion(cuil,diagnostico,evolucion);
            await getHistoriaClinicaPaciente(cuil);
        } catch (error) {
            setError(error.message)
        }finally{
            setLoading(false)
        }
    }

    const createPaciente = async(nuevoPaciente)=>{
        setLoading(true)
        setError(null)
        try {
            await pacienteService.createPaciente(nuevoPaciente);
            await getPacienteByCuil(nuevoPaciente.cuil);
        } catch (error) {
            setError(error.message)
        }finally{
            setLoading(false)
        }
    }

    return {
        paciente,
        historiaClinicaPaciente,
        diagnosticosPaciente,
        pedidosLaboratorioPaciente,
        error,
        loading,
        getPacienteByCuil,
        getDiagnosticosPaciente,
        getHistoriaClinicaPaciente,
        getPedidosLaboratorioPaciente,
        createEvolucion,
        createPaciente
    }

}

export default usePaciente