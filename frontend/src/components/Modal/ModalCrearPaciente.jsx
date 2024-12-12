import { useForm } from "react-hook-form";
import "../Modal/ModalCrearPaciente.css";
import { useState, useEffect } from "react";
import calcularEdad from "../../utils/calcularEdad";
import { pacienteService } from "../../service/pacienteService";
import { generalService } from "../../service/generalService";

function ModalCrearPaciente({ cuilNuevo, onClose, setPaciente }) {
  const [obrasSociales, setObrasSociales] = useState([]);
  const [generalError, setGeneralError] = useState(null);

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm();

  useEffect(() => {
    const cargarObrasSociales = async () => {
      try {
        const data = await generalService.getAll("obras-sociales");
        setObrasSociales(data);
      } catch (error) {
        console.error(error.message);
      }
    };

    cargarObrasSociales();
  }, []);

  const handleCrearPaciente = async (data) => {
    try {
      data.obraSocial = {
        codigo: obrasSociales.find(
          (obra) => obra.denominacion == data.obraSocial
        )?.codigo,
      };
      const pacienteCreado = await pacienteService.createPaciente(data);
      onClose();
      setGeneralError(null);
      setPaciente({
        ...pacienteCreado,
        edad: calcularEdad(data.fechaNacimiento),
      });
    } catch (e) {
      setGeneralError(e.message);
    }
  };

  if (!cuilNuevo) return null;

  return (
    <div className="modal">
      <div className="modal-content">
        <h3>Paciente no encontrado</h3>
        <p>Complete los datos para crear un nuevo paciente:</p>
        <form onSubmit={handleSubmit(handleCrearPaciente)}>
          <div>
            <label htmlFor="nombre">Nombre*:</label>
            <input
              type="text"
              id="nombre"
              {...register("nombre", { required: "El nombre es necesario" })}
            />
            {errors.nombre && (
              <label htmlFor="nombre" className="text-red-500 text-xs mt-1">
                {errors.nombre.message}
              </label>
            )}
          </div>

          <div>
            <label htmlFor="apellido">Apellido*:</label>
            <input
              type="text"
              id="apellido"
              {...register("apellido", {
                required: "El apellido es necesario",
              })}
            />
            {errors.apellido && (
              <label htmlFor="apellido" className="text-red-500 text-xs mt-1">
                {errors.apellido.message}
              </label>
            )}
          </div>

          <div>
            <label htmlFor="dni">DNI*:</label>
            <input
              type="text"
              id="dni"
              {...register("dni", {
                required: "El DNI es necesario",
                pattern: {
                  value: /^\d+$/,
                  message: "El DNI debe contener solo números",
                },
              })}
            />
            {errors.dni && (
              <label htmlFor="dni" className="text-red-500 text-xs mt-1">
                {errors.dni.message}
              </label>
            )}
          </div>

          <div>
            <label htmlFor="cuil">CUIL*:</label>
            <input
              type="text"
              id="cuil"
              defaultValue={cuilNuevo}
              {...register("cuil", {
                required: "El CUIL es necesario",
                pattern: {
                  value: /^\d+$/,
                  message: "El CUIL debe contener solo números",
                },
              })}
            />
            {errors.cuil && (
              <label htmlFor="cuil" className="text-red-500 text-xs mt-1">
                {errors.cuil.message}
              </label>
            )}
          </div>

          <div>
            <label htmlFor="fechaNacimiento">Fecha de Nacimiento*:</label>
            <input
              type="date"
              max={new Date().toISOString().split("T")[0]}
              id="fechaNacimiento"
              {...register("fechaNacimiento", {
                required: "La fecha de nacimiento es necesaria",
                validate: (value) => {
                  return (
                    new Date() > new Date(value) ||
                    "La fecha no puede ser posterior a hoy"
                  );
                },
              })}
            />
            {errors.fechaNacimiento && (
              <label
                htmlFor="fechaNacimiento"
                className="text-red-500 text-xs mt-1"
              >
                {errors.fechaNacimiento.message}
              </label>
            )}
          </div>

          <div>
            <label htmlFor="email">Email*:</label>
            <input
              type="text"
              id="email"
              {...register("email", {
                required: "El email es necesario",
                pattern: {
                  value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                  message: "El email no es válido",
                },
              })}
            />
            {errors.email && (
              <label htmlFor="email" className="text-red-500 text-xs mt-1">
                {errors.email.message}
              </label>
            )}
          </div>

          <div>
            <label htmlFor="telefono">Teléfono*:</label>
            <input
              type="text"
              id="telefono"
              {...register("telefono", {
                required: "El teléfono es necesario",
                pattern: {
                  value: /^\d+$/,
                  message: "El teléfono debe contener solo números",
                },
              })}
            />
            {errors.telefono && (
              <label htmlFor="telefono" className="text-red-500 text-xs mt-1">
                {errors.telefono.message}
              </label>
            )}
          </div>

          <div>
            <label htmlFor="obraSocial">Obra Social*:</label>
            <input
              type="text"
              id="obraSocial"
              list="obras-sociales"
              {...register("obraSocial", {
                required: "La obra social es necesaria",
                validate: (value) => {
                  return (
                    obrasSociales.find((obra) => obra.denominacion == value) ||
                    "La obra social no es válida"
                  );
                },
              })}
            />
            <datalist id="obras-sociales">
              {obrasSociales.map((obra) => (
                <option value={obra.denominacion} key={obra.id}>
                  {obra.sigla}
                </option>
              ))}
            </datalist>
            {errors.obraSocial && (
              <label htmlFor="obraSocial" className="text-red-500 text-xs mt-1">
                {errors.obraSocial.message}
              </label>
            )}
          </div>

          <div>
            <label htmlFor="numeroAfiliado">Número Afiliado*:</label>
            <input
              type="text"
              id="numeroAfiliado"
              {...register("numeroAfiliado", {
                required: "El número de afiliado es necesario",
                pattern: {
                  value: /^\d+$/,
                  message: "El número de afiliado debe contener solo números",
                },
              })}
            />
            {errors.numeroAfiliado && (
              <label
                htmlFor="numeroAfiliado"
                className="text-red-500 text-xs mt-1"
              >
                {errors.numeroAfiliado.message}
              </label>
            )}
          </div>

          <div className="modal-buttons">
            <button type="submit" className="bg-blue-500 hover:bg-blue-600">
              Crear Paciente
            </button>
            <button
              type="reset"
              className="bg-red-500 hover:bg-red-600"
              onClick={() => {
                reset();
                onClose();
                setGeneralError(null);
              }}
            >
              Cancelar
            </button>
          </div>
          {generalError &&
            <label className="text-red-500 text-xs mt-1">{generalError}</label>
          }
        </form>
      </div>
    </div>
  );
}

export default ModalCrearPaciente;
