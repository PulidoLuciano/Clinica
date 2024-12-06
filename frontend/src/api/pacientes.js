const API_URL = "http://localhost:8080";

export const fetchPaciente = async (cuil) => {
    const token = sessionStorage.getItem("jwt");
    const response = await fetch(`${API_URL}/pacientes/${cuil}`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });

    if (!response.ok) {
        if (response.status === 404) {
            return null; // Paciente no encontrado
        }
        throw new Error("Error al obtener los pacientes.");
    }

    return await response.json();
};

export const createPaciente = async (nuevoPaciente) => {
    const token = sessionStorage.getItem("jwt");
    const response = await fetch(`${API_URL}/pacientes`, {
        method: "POST",
        headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
        },
        body: JSON.stringify(nuevoPaciente),
    });

    if (!response.ok) {
        throw new Error("Error al crear el paciente");
    }

    return await response.json();
};
