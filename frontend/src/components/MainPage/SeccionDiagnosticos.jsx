import { useEffect, useState } from "react";
import { pacienteService } from "../../service/pacienteService";

export default function SeccionDiagnosticos({ cuilPaciente, diagnosticoActivo, setDiagnosticoActivo }) {
  const [diagnosticos, setDiagnosticos] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const traerDiagnosticos = async (cuilPaciente) => {
      try {
        if (cuilPaciente) {
          const data = await pacienteService.getDiagnosticosPaciente(
            cuilPaciente
          );
          const nombres = data.map((diagnostico) => diagnostico.nombre);
          setError(null);
          setDiagnosticos(["Todos"].concat(nombres));
        } else {
          setError(null);
          setDiagnosticos([]);
        }
      } catch (error) {
        setError(error.message);
        setDiagnosticos([]);
      }
    };
    traerDiagnosticos(cuilPaciente);
  }, [cuilPaciente]);

  return (
    <section className="p-4 border border-gray-300 rounded-md overflow-y-auto">
      <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
        Filtrar por diagnósticos del paciente
      </h3>
      <article className="flex justify-center gap-3 p-2">
        {!cuilPaciente ? (
          <p>Seleccione un paciente</p>
        ) : error ? (
          <p>{error}</p>
        ) : (
          diagnosticos.map((diagnostico, index) => (
            <button
              className={
                diagnosticoActivo != diagnostico
                  ? "bg-gray-500 hover:bg-gray-600 text-white rounded-md px-3 py-1"
                  : "bg-indigo-600 text-white rounded-md px-3 py-1"
              }
              key={index}
              onClick={() => setDiagnosticoActivo(diagnostico)}
            >
              {diagnostico}
            </button>
          ))
        )}
      </article>
    </section>
  );
}
