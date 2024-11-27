import React from 'react';
import '../Modal/ModalCrearPaciente.css';

function ModalCrearPaciente({
  visible,
  onClose,
  onCreate,
  pacienteData,
  onChange,
}) {
  if (!visible) return null; // No renderiza el modal si no está visible

  return (
    <div className="modal">
      <div className="modal-content">
        <h3>Paciente no encontrado</h3>
        <p>Complete los datos para crear un nuevo paciente:</p>
        <form>
          <input
            type="text"
            name="nombre"
            placeholder="Nombre"
            value={pacienteData.nombre}
            onChange={onChange}
          />
          <input
            type="text"
            name="apellido"
            placeholder="Apellido"
            value={pacienteData.apellido}
            onChange={onChange}
          />
          <input
            type="text"
            name="dni"
            placeholder="DNI"
            value={pacienteData.dni}
            onChange={onChange}
          />
          <input
            type="text"
            name="cuil"
            placeholder="CUIL"
            value={pacienteData.cuil}
            onChange={onChange}
          />
          <input
            type="date"
            name="fechaNacimiento"
            placeholder="Fecha de Nacimiento"
            value={pacienteData.fechaNacimiento}
            onChange={onChange}
          />
          <input
            type="text"
            name="email"
            placeholder="Email"
            value={pacienteData.email}
            onChange={onChange}
          />
          <input
            type="text"
            name="telefono"
            placeholder="Teléfono"
            value={pacienteData.telefono}
            onChange={onChange}
          />
          <input
            type="text"
            name="numeroAfiliado"
            placeholder="Número Afiliado"
            value={pacienteData.numeroAfiliado}
            onChange={onChange}
          />
          <input
            type="text"
            name="obraSocial"
            placeholder="Obra Social"
            value={pacienteData.obraSocial}
            onChange={onChange}
          />
          <div className="modal-buttons">
            <button type="button" onClick={onCreate}>
              Crear Paciente
            </button>
            <button type="button" onClick={onClose}>
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ModalCrearPaciente;
