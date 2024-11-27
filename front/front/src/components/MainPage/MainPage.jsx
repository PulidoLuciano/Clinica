import React, { useState } from 'react';
import ModalCrearPaciente from '../Modal/ModalCrearPaciente';
import './MainPage.css';

function MainPage() {
  const [paciente, setPaciente] = useState({
    nombre: "Nombre del paciente",
    dni: "DNI",
    edad: "Edad",
    obraSocial: "Obra Social",
  });

  const [modalVisible, setModalVisible] = useState(false); // Control del modal
  const [nuevoPaciente, setNuevoPaciente] = useState({
    cuil: "",
    dni: "",
    fechaNacimiento: "",
    email: "",
    telefono: "",
    nombre: "",
    apellido: "",
    numeroAfiliado: "",
    obraSocial: "",
  });

  // Función para calcular la edad a partir de la fecha de nacimiento
  const calcularEdad = (fechaNacimiento) => {
    const hoy = new Date();
    const nacimiento = new Date(fechaNacimiento);
    let edad = hoy.getFullYear() - nacimiento.getFullYear();
    const mes = hoy.getMonth() - nacimiento.getMonth();
    if (mes < 0 || (mes === 0 && hoy.getDate() < nacimiento.getDate())) {
      edad--;
    }
    return edad;
  };

  
  const handleCreatePaciente = async () => {
    try {
      const token = sessionStorage.getItem("jwt");
      const response = await fetch("http://localhost:8080/pacientes", {
        method: "POST",
        headers: {
          "Authorization": `Bearer ${token}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(nuevoPaciente),
      });

      if (response.ok) {
        alert("Paciente creado con éxito");
        setModalVisible(false);
      } else {
        alert("Error al crear el paciente");
      }
    } catch (error) {
      console.error("Error al crear el paciente:", error);
    }
  };

  const handleChangeNuevoPaciente = (e) => {
    const { name, value } = e.target;
    setNuevoPaciente({ ...nuevoPaciente, [name]: value });
  };

  const fetchPaciente = async (cuil) => {
    try {
      const token = sessionStorage.getItem("jwt");
      const response = await fetch(`http://localhost:8080/pacientes/${cuil}`, {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token}`,
        },
      });

      if (!response.ok) {
        if (response.status === 404) {
          setModalVisible(true); // Mostrar modal si no existe el paciente
        }
        throw new Error("Error al obtener los pacientes.");
      }

      const pacienteEncontrado = await response.json();
      setPaciente({
        nombre: `${pacienteEncontrado.nombre} ${pacienteEncontrado.apellido}`,
        dni: pacienteEncontrado.dni,
        edad: calcularEdad(pacienteEncontrado.fechaNacimiento),
        obraSocial: pacienteEncontrado.obraSocial.nombre,
      });
    } catch (error) {
      console.error("Error al obtener el paciente:", error);
    }
  };

  const handleSearch = (event) => {
    event.preventDefault();
    const cuil = event.target.cuil.value.trim();
    if (cuil) {
      fetchPaciente(cuil);
    }
  };

  return (
    <>
      <div className="container">
        {/* Parte superior */}
        <div className="header">
          <div className="pacient">
            <div className="row">
              <h5>Nombre: {paciente.nombre}</h5>
              <h5>DNI: {paciente.dni}</h5>
            </div>
            <div className="row">
              <h5>Edad: {paciente.edad}</h5>
              <h5>Obra Social: {paciente.obraSocial}</h5>
            </div>
          </div>

          {/* Formulario de búsqueda */}
          <div className="search-form">
            <form onSubmit={handleSearch}>
              <label htmlFor="cuil">Buscar por CUIL</label>
              <input
                type="text"
                id="cuil"
                name="cuil"
                placeholder="Ingrese el CUIL"
              />
              <button type="submit">Buscar</button>
            </form>
          </div>
        </div>

        {/* Parte inferior */}
        <div className="main-container">
          {/* Izquierda */}
          <div className="left">
            <div className="diagnosticos-container">
              <h3>Diagnósticos previos</h3>
              <div className='diagnosticos'></div>
            </div>
            <div className="historial-container">
              <h3>Historial Clínico</h3>
            </div>
          </div>

          {/* Derecha */}
          <div className="right">
            <div className="cards-container">
              <div className="card">
                <h3>Pedidos Laboratorio</h3>
              </div>
              <div className="card">
                <h3>Informes Anteriores</h3>
              </div>
              <div className="card">
                <h3>Receta Digital</h3>
              </div>
              <div className="card">
                <h3>Plantillas Evolución</h3>
              </div>
            </div>
            <div className="comments-container">Comentarios</div>
          </div>
        </div>
        {/* Modal para crear paciente */}
        <ModalCrearPaciente
          visible={modalVisible}
          onClose={() => setModalVisible(false)}
          onCreate={handleCreatePaciente}
          pacienteData={nuevoPaciente}
          onChange={handleChangeNuevoPaciente}
        />
      </div>
    </>
  );
}

export default MainPage;
