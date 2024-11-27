import { useForm } from "react-hook-form";
import { useNavigate } from 'react-router-dom';
import useAuthenticateUser from "../../api/auth"; // Importar el hook personalizado
import './LogIn.css';

function LoginPage() {
  const navigate = useNavigate();
  const { register, handleSubmit, formState: { errors } } = useForm();
  const { authenticateUser, error } = useAuthenticateUser(); // Usar el hook de autenticación

  const onSubmit = async (data) => {
    const success = await authenticateUser(data.username, data.password);
    console.log(data)
    if (success) {
      navigate('/inicio');
    }
  };

  return (
    <div className="login-container">
      {/* Imagen de fondo ocupando toda la pantalla */}
      <div className="left"></div>
      <div className="right">
        <form onSubmit={handleSubmit(onSubmit)} className="login-form">

          <h2>Iniciar sesión</h2>

          {/* Campo Usuario */}
          <div className="input-group">
            <label htmlFor="username">Usuario</label>
            <input
              id="username"
              type="text"
              placeholder="Ingrese su usuario"
              {...register('username', { required: "El usuario es obligatorio" })}
            />
            {errors.username && <p className="error">{errors.username.message}</p>}
          </div>

          {/* Campo Contraseña */}
          <div className="input-group">
            <label htmlFor="password">Contraseña</label>
            <input
              id="password"
              type="password"
              placeholder="Ingrese su contraseña"
              {...register('password', { required: "La contraseña es obligatoria" })}
            />
            {errors.password && <p className="error">{errors.password.message}</p>}
          </div>

          {/* Mostrar error de autenticación */}
          {error && <p className="error">{error}</p>}

          {/* Botón de Ingreso */}
          <button type="submit" className="login-btn">Ingresar</button>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;


