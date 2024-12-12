import DatosReceta from "./DatosReceta";

export default function SeccionRecetas({ recetas }) {



  return (
    <section className="row-span-5 bg-gray-100">
      <h3 className="bg-blue-500 text-white text-center p-2 rounded-md sticky top-0">
        Recetas
      </h3>
      <div className=" h-4/5 overflow-y-auto">
        <div className=" mb-4 ">
          {recetas ? (
            <div className="p-2">
              {recetas.map((receta, index) => (
                <DatosReceta receta={receta} index={index} />
              ))}
            </div>
          ) : (
            <p className="text-center">No hay recetas disponibles</p>
          )}
        </div>
      </div>

    </section>

  );
}
