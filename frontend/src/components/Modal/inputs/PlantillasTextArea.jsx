import { useState } from "react";

export default function PlantillasTextArea({
  label,
  placeholder,
  plantillas,
  register,
  name,
  validations,
  error,
  setValue
}) {
  const [filteredPlantillas, setFilteredPlantillas] = useState([]);

    console.log(filteredPlantillas)

  const filtrarPlantillas = (value) => {
    const lowerValue = value.toLowerCase();
    console.log(lowerValue)
    setFilteredPlantillas(
      plantillas.filter(
        (plantilla) =>
          plantilla.descripcion.toLowerCase().includes(lowerValue) ||
          plantilla.texto.toLowerCase().includes(lowerValue)
      )
    );
  };

  return (
    <div className="mb-4">
      <label
        className="block text-gray-700 text-sm font-bold mb-2"
        htmlFor={name}
      >
        {label}
      </label>
      <textarea
        {...register(name, validations)}
        className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        rows="4"
        placeholder={placeholder}
        id={name}
        onChange={(e) => filtrarPlantillas(e.target.value)}
      />
      {filteredPlantillas.length > 0 && (
        <ul className="border rounded-lg max-h-40 overflow-y-auto mt-2">
          {filteredPlantillas.map((plantilla) => (
            <li
              key={plantilla.descripcion}
              onClick={() => {setValue(name, plantilla.texto); setFilteredPlantillas([])}}
              className="p-2 hover:bg-gray-100 cursor-pointer"
            >
              {plantilla.descripcion}
            </li>
          ))}
        </ul>
      )}
      {error && <p className="text-red-500 text-sm">{error.message}</p>}
    </div>
  );
}
