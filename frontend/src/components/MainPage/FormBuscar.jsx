import { useState } from "react";
import ModalCrearPaciente from "../Modal/ModalCrearPaciente";
import { useForm } from "react-hook-form";
import calcularEdad from "../../utils/calcularEdad";
import { pacienteService } from "../../service/pacienteService";

export default function FormBuscar({ setPaciente }) {
  const [modalCrearVisible, setModalCrearVisible] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const handleSearch = async (data) => {
    const cuilBuscado = data.cuil;
    if (cuilBuscado) {
      const pacienteEncontrado = await pacienteService.getPacienteByCuil(cuilBuscado);
      if(!pacienteEncontrado){
        setModalCrearVisible(true);
        return;
      }
      console.log(pacienteEncontrado)
      setPaciente({
        ...pacienteEncontrado,
        edad: calcularEdad(pacienteEncontrado.fechaNacimiento),
      });
    }
  };

  return (
    <>
      <form className="py-2 flex justify-center" onSubmit={handleSubmit(handleSearch)}>
        <label>
            <input
            type="text"
            name="cuil"
            placeholder="Buscar por CUIL"
            {...register("cuil", {
                required: "Se debe ingresar un CUIL",
                pattern: {
                    message: "El CUIL solo debe contener números",
                    value: /^\d+$/
                }
            })}
            className="mr-1 px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
            />
            {errors.cuil && (
            <p className="text-red-500 text-xs mt-1 absolute">{errors.cuil.message}</p>
            )}
        </label>
        <button
          type="submit"
          className="bg-indigo-600 text-white px-4 py-2 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
        >
          Buscar
        </button>
      </form>
      <ModalCrearPaciente
        visible={modalCrearVisible}
        onClose={() => setModalCrearVisible(false)}
        setPaciente={setPaciente}
      />
    </>
  );
}
