import React from 'react'
import { Link } from 'react-router-dom'
const UnauthorizedPage = () => {
  return (
    <>
    <div>No tienes autorizacion para ingresar a esta pagina</div>
    <Link to={"/"}>IniciarSesion</Link>
    </>
  )
}

export default UnauthorizedPage