const API_URL = "http://localhost:8080";

const getPacienteByCuil = async (cuil) => {
    const token = sessionStorage.getItem("jwt");
    if (!token) throw new Error("No estas autorizado para realizar esta acción.")

    const response = await fetch(`${API_URL}/pacientes/${cuil}`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    const data = await response.json();
    if (!response.ok) {
        if(response.status == 404){
            return null;
        }else throw new Error(`${data.message}`);
    }
    return data
}

const createPaciente = async (nuevoPaciente) => {

    const token = sessionStorage.getItem("jwt");
    if (!token) throw new Error("No estas autorizado para realizar esta acción.")

    const response = await fetch(`${API_URL}/pacientes`, {
        method: "POST",
        headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
        },
        body: JSON.stringify(nuevoPaciente),
    });

    const data = await response.json();
    if (!response.ok) {
        throw new Error(`${data.details.message}`);
    }
    return data;
};

const getHistoriaClinicaPaciente = async (cuil) => {
    const token = sessionStorage.getItem("jwt");
    if (!token) throw new Error("No estas autorizado para realizar esta acción.")

    const response = await fetch(`${API_URL}/pacientes/${cuil}/historia-clinica`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    const data = await response.json();
    if (!response.ok) {

        throw new Error(`${data.message}`);
    }
    return data

}

const getRecetasPaciente = async (cuil) => {
    const token = sessionStorage.getItem("jwt");
    if (!token) throw new Error("No estas autorizado para realizar esta acción.")

    const response = await fetch(`${API_URL}/pacientes/${cuil}/historia-clinica/recetas`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    const data = await response.json();
    if (!response.ok) {

        throw new Error(`${data.message}`);
    }
    return data
}

const getPedidosLaboratorioPaciente = async (cuil) => {
    const token = sessionStorage.getItem("jwt");
    if (!token) throw new Error("No estas autorizado para realizar esta acción.")

    const response = await fetch(`${API_URL}/pacientes/${cuil}/historia-clinica/pedidos`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    const data = await response.json();
    if (!response.ok) {

        throw new Error(`${data.message}`);
    }
    return data
}

const getDiagnosticosPaciente = async (cuil) => {
    const token = sessionStorage.getItem("jwt");
    if (!token) throw new Error("No estas autorizado para realizar esta acción.")

    const response = await fetch(`${API_URL}/pacientes/${cuil}/historia-clinica/diagnosticos`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    const data = await response.json();
    if (!response.ok) {

        throw new Error(`${data.details.message}`);
    }
    return data

}


const createEvolucion = async (cuil,diagnostico,evolucion) => {
    const token = sessionStorage.getItem("jwt");
    if (!token) throw new Error("No estas autorizado para realizar esta acción.")
    
    const url = `http://localhost:8080/pacientes/${cuil}/historia-clinica/${diagnostico}/evolucion`;
    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(evolucion),
    });
    const data = await response.json();
    if (!response.ok) {
       
        throw new Error(`Error al crear evolución: ${errorData.message}`);
    }
    return data;

}

const getEvoluciones =  async(cuil,diagnostico)  =>{

    const token = sessionStorage.getItem("jwt");
    if (!token) throw new Error("No estas autorizado para realizar esta acción.")

        const url = `http://localhost:8080/pacientes/${cuil}/historia-clinica/evoluciones`;
        const response = await fetch(url, {
            method: "Get",
            headers: {
                Authorization: `Bearer ${token}`,
            }
        });
        const data = await response.json();
        if (!response.ok) {
            throw new Error(`Error al buscar evoluciones: ${data.details.message}`);
        }
        return data;

}

const getEvolucionesPorDiagnostico = async(cuil,diagnostico) =>{

    const token = sessionStorage.getItem("jwt");
    if (!token) throw new Error("No estas autorizado para realizar esta acción.")

        const url = `http://localhost:8080/pacientes/${cuil}/${diagnostico}/evoluciones`;
        const response = await fetch(url, {
            method: "Get",
            headers: {
                Authorization: `Bearer ${token}`,
            }
        });
        const data = await response.json();
        if (!response.ok) {
            throw new Error(`Error al buscar evoluciones: ${data.details.message}`);
        }
        return data;

}


export const pacienteService = {
    getPacienteByCuil,
    createPaciente,
    getHistoriaClinicaPaciente,
    getRecetasPaciente,
    getPedidosLaboratorioPaciente,
    getDiagnosticosPaciente,
    createEvolucion,
    getEvoluciones,
    getEvolucionesPorDiagnostico
}
