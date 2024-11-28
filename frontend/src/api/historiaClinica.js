const API_URL = "http://localhost:8080";

export const fetchHistoriaClinica = async (cuil, token) => {
    const response = await fetch(
        `${API_URL}/pacientes/${cuil}/historia-clinica`,
        {
            method: "GET",
            headers: {
                Authorization: `Bearer ${token}`,
            },
        }
    );

    if (!response.ok) {
        throw new Error("Error al obtener la historia clínica.");
    }

    return await response.json();
};
