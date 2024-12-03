import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";




const RedirectIfLoggedIn = ({children}) => {
  const { isAuthenticated } = useAuth();

  if (isAuthenticated) {
    return <Navigate to="/historiaClinica" replace />;
  }

  return <>{children}</>;
};

export default RedirectIfLoggedIn;