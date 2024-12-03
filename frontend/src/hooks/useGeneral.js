import { useState } from 'react'
import { generalService } from '../service/generalService'
const useGeneral = () => {

    const [medicamentos, setMedicamentos] = useState([])
    const [obrasSociales, setObrasSociales] = useState([])
    const [diagnosticos, setDiagnosticos] = useState([])
    const [loadingGeneral, setLoadingGeneral] = useState(null)
    const [errorGeneral, setErrorGeneral] = useState(null)


    const getAllMedicamentos = async (descripcion) => {
        setLoadingGeneral(true);
        setErrorGeneral(null)
        try {
            let data;
            if (descripcion) {
                data = generalService.getMedicamentoByDescription(descripcion)
            }
            else {
                const url = "http://localhost:8080/medicamentos";
                data = generalService.getAll(url);
            }
            setMedicamentos(data);
        } catch (error) {
            setErrorGeneral(error.message);
        } finally {
            setLoadingGeneral(false);
        }
    }


    const getAllDiagnosticos = async () => {
        setLoadingGeneral(true);
        setErrorGeneral(null)
        try {
            const url = "http://localhost:8080/diagnosticos";
            const data = generalService.getAll(url);
            setDiagnosticos(data)
        } catch (error) {
            setErrorGeneral(error.message);
        } finally {
            setLoadingGeneral(false);
        }
    }


    const getAllObrasSociales = async () => {
        setLoadingGeneral(true);
        setErrorGeneral(null)
        try {
            const url = "http://localhost:8080/obras-sociales";
            const data = generalService.getAll(url);
            setObrasSociales(data);
        } catch (error) {
            setErrorGeneral(error.message);
        } finally {
            setLoadingGeneral(false);
        }
    }



    return {
        medicamentos,
        diagnosticos,
        obrasSociales,
        loadingGeneral,
        errorGeneral,
        getAllDiagnosticos,
        getAllMedicamentos,
        getAllObrasSociales
    }
}

export default useGeneral