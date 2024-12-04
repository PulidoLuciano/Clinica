import React from 'react'
import { Navigate } from 'react-router-dom'
import { useAuth } from '../contexts/AuthContext';


const ProtectedRoutes = ({element}) => {
  const {isAuthenticated} = useAuth();
    if(!isAuthenticated) return <Navigate to={"/login"} replace/>;
    return element;
}

export default ProtectedRoutes