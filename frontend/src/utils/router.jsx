import { lazy } from "react"
const RedirectIfLoggedIn = lazy(()=> import("../components/RedirectIfLogin.jsx"))
const ProtectedRoutes = lazy(()=> import("../components/ProtectedRoutes.jsx"))
const LoginPage = lazy(() => import("../pages/LoginPage.jsx"));
const HistoriaClinicaPage = lazy(() => import("../pages/HistoriaClinicaPage.jsx"));
const UnauthorizedPage = lazy(()=> import("../pages/UnauthorizedPage.jsx"))

export const routes = [
    {
        path:"/login",
        element: <RedirectIfLoggedIn><LoginPage/></RedirectIfLoggedIn>
    },
    {
        path:"/",
        element: <ProtectedRoutes element={<HistoriaClinicaPage/>}/>
    }
];