import { useEffect, useState } from "react";
import FormBuscar from "./FormBuscar";

export default function DatosPaciente({ setCuilPaciente }) {
  const [paciente, setPaciente] = useState(null);

  useEffect(() => {
    setCuilPaciente(paciente?.cuil || null);
  }, [paciente]);

  return (
    <section className="bg-gray-100 m-2 p-4 border border-gray-300 rounded-md  relative">
      <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
        Datos del paciente
      </h3>
      {paciente ? (
        <div className="grid grid-flow-row lg:grid-cols-3 p-2">
          <p>
            <strong>Nombre:</strong> {paciente.nombre} {paciente.apellido}
          </p>
          <p>
            <strong>DNI:</strong> {paciente.dni}
          </p>
          <p>
            <strong>CUIL:</strong> {paciente.cuil}
          </p>
          <p>
            <strong>Fecha de Nacimiento:</strong>{" "}
            {new Date(paciente.fechaNacimiento).toLocaleDateString()}
          </p>
          <p>
            <strong>Edad:</strong> {paciente.edad}
          </p>
          <p>
            <strong>Email:</strong> {paciente.email}
          </p>
          <p>
            <strong>Teléfono:</strong> {paciente.telefono}
          </p>
          <p>
            <strong>Número de Afiliado:</strong> {paciente.numeroAfiliado}
          </p>
          <p>
            <strong>Obra Social:</strong> {paciente.obraSocial.denominacion}{" "}
            {paciente.obraSocial.sigla !== ""
              ? `(${paciente.obraSocial.sigla})`
              : null}
          </p>
          <button className="max-w-44 mt-2 text-md bg-indigo-600 text-white px-2 py-1 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2" onClick={() => setPaciente(null)}>Buscar nuevo paciente</button>
        </div>
      ) : (
        <FormBuscar setPaciente={setPaciente} />
      )}
    </section>
  );
}
