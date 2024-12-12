import { useState, useEffect } from "react";
import { useFieldArray, useForm } from "react-hook-form";
import { generalService } from "../../service/generalService";
import { pacienteService } from "../../service/pacienteService";
import "../Modal/ModalCrearPaciente.css";
import {
  PlantillasEvoluciones,
  PlantillasPedidos,
} from "../../data/plantillas";
import PlantillasTextArea from "./inputs/PlantillasTextArea";

function ModalAgregarEvolucion({
  visible,
  onClose,
  cuilPaciente,
  diagnosticoActivo,
  setDiagnosticoActivo,
}) {
  const [diagnosticos, setDiagnosticos] = useState([]);
  const [tipoEvolucion, setTipoEvolucion] = useState("Ninguno");
  const [busquedaMedicamento, setBusquedaMedicamento] = useState("");
  const [timeoutId, setTimeoutId] = useState(null);
  const [buscando, setBuscando] = useState(false);
  const [medicamentosEncontrados, setMedicamentosEncontrados] = useState([]);

  const {
    register,
    handleSubmit,
    reset,
    control,
    setValue,
    formState: { errors },
  } = useForm();

  const { fields, append, remove } = useFieldArray({
    control,
    name: "medicamentosReceta",
    rules: {
      required:
        tipoEvolucion === "Receta"
          ? "Se debe recetar al menos un medicamento"
          : false,
      maxLength: {
        value: 2,
        message: "No se puede recetar más de dos medicamentos",
      },
    },
  });

  useEffect(() => {
    const cargarDiagnosticos = async () => {
      try {
        const data = await generalService.getAll("diagnosticos");
        setDiagnosticos(data);
      } catch (error) {
        console.error(error.message);
      }
    };

    cargarDiagnosticos();
  }, []);

  useEffect(() => {
    if (!visible) {
      setTipoEvolucion("Ninguno");
      remove();
      reset();
    }
  }, [visible, reset]);

  const onSubmit = async (data) => {
    let { texto, textoPedidoLaboratorio, diagnostico, medicamentosReceta } =
      data;
    let requestBody;
    switch (tipoEvolucion) {
      case "Ninguno":
        requestBody = { texto };
        await pacienteService.createEvolucion(
          cuilPaciente,
          diagnostico,
          requestBody
        );
        break;
      case "Receta":
        medicamentosReceta = medicamentosReceta.map((medicamento) => ({
          codigoMedicamento: medicamento.codigo,
          cantidad: medicamento.cantidad,
        }));
        requestBody = { texto, medicamentosReceta };
        await pacienteService.createEvolucion(
          cuilPaciente,
          diagnostico,
          requestBody
        );
        break;
      case "Laboratorio":
        requestBody = { texto, textoPedidoLaboratorio };
        await pacienteService.createEvolucion(
          cuilPaciente,
          diagnostico,
          requestBody
        );
        break;
    }
    setDiagnosticoActivo("");
    onClose();
  };

  const buscarMedicamentos = async (descripcion) => {
    if (!descripcion.trim()) {
      setMedicamentosEncontrados([]);
      return;
    }
    setBuscando(true);
    try {
      const data = await generalService.getMedicamentoByDescription(
        descripcion
      );
      setMedicamentosEncontrados(data);
    } catch (error) {
      console.error(error.message);
      setMedicamentosEncontrados([]);
    } finally {
      setBuscando(false);
    }
  };

  const handleOnChangeBusquedaMedicamento = (e) => {
    const valor = e.target.value;
    setBusquedaMedicamento(valor);

    if (timeoutId) clearTimeout(timeoutId);

    const nuevoTimeoutId = setTimeout(() => {
      buscarMedicamentos(valor);
    }, 300);

    setTimeoutId(nuevoTimeoutId);
  };

  const agregarMedicamento = (medicamento) => {
    append({ ...medicamento, cantidad: 1 });
    setMedicamentosEncontrados([]);
    setBusquedaMedicamento("");
  };

  const eliminarMedicamento = (index) => {
    remove(index);
  };

  if (!visible) return null;

  return (
    <div className="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex justify-center items-center z-50">
      <div className="bg-white p-6 rounded-lg shadow-lg w-[500px] max-h-[90vh] overflow-y-auto">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-bold">Agregar Evolución</h2>
          <button
            onClick={() => {
              onClose();
            }}
            className="text-gray-500 hover:text-gray-700"
          >
            ✕
          </button>
        </div>

        <form onSubmit={handleSubmit(onSubmit)}>
          <PlantillasTextArea
            label={"Texto de la evolución*"}
            placeholder={"Ingrese el texto de la evolución"}
            register={register}
            name={"texto"}
            validations={{
              required: "El texto de la evolución es obligatorio.",
            }}
            error={errors.texto}
            plantillas={PlantillasEvoluciones}
            setValue={setValue}
          />

          <div className="mb-4">
            <label
              htmlFor="diagnostico"
              className="block text-gray-700 text-sm font-bold mb-2"
            >
              Diagnóstico*:
            </label>
            <input
              type="text"
              id="diagnostico"
              list="diagnosticos"
              defaultValue={
                diagnosticoActivo == "Todos" ? "" : diagnosticoActivo
              }
              className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              {...register("diagnostico", {
                required: "El diagnóstico es necesario",
                validate: (value) => {
                  return (
                    diagnosticos.find(
                      (diagnostico) => diagnostico.nombre == value
                    ) || "El diagnóstico no es válido"
                  );
                },
              })}
            />
            <datalist id="diagnosticos">
              {diagnosticos.map((diagnostico, index) => (
                <option value={diagnostico.nombre} key={index} />
              ))}
            </datalist>
            {errors.diagnostico && (
              <label
                htmlFor="diagnostico"
                className="text-red-500 text-xs mt-1"
              >
                {errors.diagnostico.message}
              </label>
            )}
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Documento asociado
            </label>
            <button
              type="button"
              onClick={() => {
                setTipoEvolucion("Ninguno");
                remove();
              }}
              className={`flex-1 px-4 py-2 rounded-lg w-full mb-2 ${
                tipoEvolucion === "Ninguno"
                  ? "bg-blue-500 text-white"
                  : "bg-gray-200 hover:bg-gray-300"
              }`}
            >
              Ninguno
            </button>
            <div className="flex gap-4">
              <button
                type="button"
                onClick={() => {
                  setTipoEvolucion("Receta");
                }}
                className={`flex-1 px-4 py-2 rounded-lg ${
                  tipoEvolucion === "Receta"
                    ? "bg-blue-500 text-white"
                    : "bg-gray-200 hover:bg-gray-300"
                }`}
              >
                Receta
              </button>
              <button
                type="button"
                onClick={() => {
                  setTipoEvolucion("Laboratorio");
                  remove();
                }}
                className={`flex-1 px-4 py-2 rounded-lg ${
                  tipoEvolucion === "Laboratorio"
                    ? "bg-blue-500 text-white"
                    : "bg-gray-200 hover:bg-gray-300"
                }`}
              >
                Laboratorio
              </button>
            </div>
          </div>

          {tipoEvolucion === "Laboratorio" && (
            <PlantillasTextArea
              label={"Texto del pedido de laboratorio"}
              placeholder={"Ingrese el texto del pedido de laboratorio"}
              register={register}
              name={"textoPedidoLaboratorio"}
              validations={{
                required:
                  tipoEvolucion === "Laboratorio"
                    ? "El texto del pedido de laboratorio es obligatorio."
                    : false,
              }}
              error={errors.textoPedidoLaboratorio}
              plantillas={PlantillasPedidos}
              setValue={setValue}
            />
          )}

          {tipoEvolucion === "Receta" && (
            <div className="mb-4">
              <label className="block text-gray-700 text-sm font-bold mb-2">
                Buscar medicamento
              </label>
              <div className="relative">
                <input
                  type="text"
                  value={busquedaMedicamento}
                  onChange={handleOnChangeBusquedaMedicamento}
                  className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  placeholder="Escriba para buscar medicamentos"
                />
                {buscando && (
                  <div className="absolute right-3 top-2">
                    <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-blue-500"></div>
                  </div>
                )}
                {medicamentosEncontrados.length > 0 && (
                  <ul className="border rounded-lg max-h-40 overflow-y-auto mt-2">
                    {medicamentosEncontrados.map((med) => (
                      <li
                        key={med.codigo}
                        onClick={() => agregarMedicamento(med)}
                        className="p-2 hover:bg-gray-100 cursor-pointer"
                      >
                        {med.descripcion} ({med.formato})
                      </li>
                    ))}
                  </ul>
                )}
                {fields.map((item, index) => (
                  <>
                    <div
                      key={item.id}
                      className="flex items-center gap-2 p-2 border rounded-lg mt-2"
                    >
                      <span className="flex-1">
                        {item.descripcion} ({item.formato})
                      </span>
                      <input
                        type="number"
                        {...register(`medicamentosReceta.${index}.cantidad`, {
                          required: "La cantidad de cajas es obligatoria",
                          min: {
                            value: 1,
                            message:
                              "La cantidad de cajas no puede ser menor a uno",
                          },
                          pattern: {
                            value: /^\d+$/,
                            message: "La cantidad debe contener solo números",
                          },
                        })}
                        className="w-20 px-2 py-1 border rounded-lg text-center"
                      />
                      <button
                        type="button"
                        onClick={() => eliminarMedicamento(index)}
                        className="text-red-500 hover:text-red-700"
                      >
                        ✕
                      </button>
                    </div>
                    {errors.medicamentosReceta?.[index] && (
                      <p className="text-red-500 text-sm" key={index}>
                        {errors.medicamentosReceta?.[index].cantidad.message}
                      </p>
                    )}
                  </>
                ))}
                <p className="text-red-500 text-sm">
                  {errors.medicamentosReceta?.root?.message}
                </p>
              </div>
            </div>
          )}

          <div className="flex justify-end gap-2 mt-5">
            <button
              type="button"
              onClick={() => {
                onClose();
              }}
              className="text-white px-4 py-2 bg-red-500 rounded-lg hover:bg-red-600"
            >
              Cancelar
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
            >
              Guardar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ModalAgregarEvolucion;
