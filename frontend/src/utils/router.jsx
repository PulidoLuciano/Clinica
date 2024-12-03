import { lazy } from "react"
const RedirectIfLoggedIn = lazy(()=> import("../components/RedirectIfLogin.jsx"))
const ProtectedRoutes = lazy(()=> import("../components/ProtectedRoutes.jsx"))
const LoginPage = lazy(() => import("../pages/LoginPage.jsx"));
const HistoriaClinicaPage = lazy(() => import("../pages/HistoriaClinicaPage.jsx"));
const UnauthorizedPage = lazy(()=> import("../pages/UnauthorizedPage.jsx"))

export const routes = [
    {
        path:"/",
        element: <RedirectIfLoggedIn><LoginPage/></RedirectIfLoggedIn>
    },
    {
        path:"/historiaClinica",
        element: <ProtectedRoutes element={<HistoriaClinicaPage/>}/>
    },
    {
        path:"/unauthorized",
        element: <UnauthorizedPage/>
    }
];