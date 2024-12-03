
const getAll = async(url)=>{
    
    const token = sessionStorage.getItem("jwt");
    if(!token) throw new Error("No estas autorizado para realizar esta acción");

    const response = await fetch(`${url}`,{
        method:"GET",
        headers:{
            Autorization: `Bearer ${token}`
        }
    });

    const data = await response.json();
    if(!response.ok) throw new Error(`${data.message}`);
    return data;

}

const getMedicamentoByDescription = async(descripcion)=>{
    const token = sessionStorage.getItem("jwt");
    if(!token) throw new Error("No estas autorizado para realizar esta acción");

    const response = await fetch(`http://localhost:8080/medicamentos?descripcion=${descripcion}`,{
        method:"GET",
        headers:{
            Autorization: `Bearer ${token}`
        }
    })

    const data = response.json();
    if(!response.ok) throw new Error(`${data.message}`);
    return data;

}

export const generalService = {
    getAll,
    getMedicamentoByDescription
}