import React from 'react'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { AuthContext } from './contexts/AuthContext';
import { routes } from './utils/router';

const createRoutes = () => {
    return routes.map((route) => {
      return route;
    });
  };


export const App = () => {
    const router = createBrowserRouter([...routes]);

    return (
        <AuthContext>
            <RouterProvider router={router} />
        </AuthContext>
    )
}
