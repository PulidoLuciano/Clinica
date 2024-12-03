import React from 'react'
import { Navigate, useNavigate } from 'react-router-dom'
import { useAuth } from '../contexts/AuthContext';


const ProtectedRoutes = ({element}) => {
  const navigate = useNavigate();
  const {isAuthenticated} = useAuth();
    if(!isAuthenticated) return <Navigate to={"/unauthorized"} replace/>;
    return element;
}

export default ProtectedRoutes