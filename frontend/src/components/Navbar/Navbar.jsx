import { useAuth } from "../../contexts/AuthContext";

function Navbar() {
  const auth = useAuth();

  const handleLogout = () => {
    if (window.confirm("¿Estás seguro de que deseas cerrar sesión?")) {
      auth.logout();
    }
  };

  return (
    <nav className="bg-white shadow-lg flex justify-between items-center h-16 max-w-7xl w-full mx-auto px-4">
      {/* Encabezado de la clínica */}
      <div className="flex items-center">
        <div className="flex-shrink-0 flex items-center">
          <span className="text-xl font-semibold text-gray-800">Clínica</span>
        </div>
      </div>
      <button
        onClick={handleLogout}
        className="h-10 bg-red-600 text-white px-4 py-2 rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
      >
        Cerrar sesión
      </button>
    </nav>
  );
}

export default Navbar;
