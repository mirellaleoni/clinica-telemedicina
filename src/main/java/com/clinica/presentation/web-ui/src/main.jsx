import React from "react";
import ReactDOM from "react-dom/client";
import { Navigate, RouterProvider, createBrowserRouter } from "react-router-dom";
import UserProvider from "./Context/UserContext.jsx";
import { GlobalStyled } from "./GlobalStyled.jsx";
import "./index.css";
import Login from "./Pages/Login/Login.jsx";
import Cadastro from "./Pages/Cadastro/Cadastro.jsx";
import ProtectedRoute from "./Components/ProtectedRoute.jsx";
import AppLayout from "./Components/Layout/AppLayout.jsx";
import Dashboard from "./Pages/Dashboard/Dashboard.jsx";
import Medicos from "./Pages/Medicos/Medicos.jsx";
import Pacientes from "./Pages/Pacientes/Pacientes.jsx";
import Agendamentos from "./Pages/Agendamentos/Agendamentos.jsx";
import Consultas from "./Pages/Consultas/Consultas.jsx";
import Prontuarios from "./Pages/Prontuarios/Prontuarios.jsx";
import Receitas from "./Pages/Receitas/Receitas.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/cadastro",
    element: <Cadastro />,
  },
  {
    element: <ProtectedRoute />,
    children: [
      {
        element: <AppLayout />,
        children: [
          { path: "dashboard", element: <Dashboard /> },
          { path: "medicos", element: <Medicos /> },
          { path: "pacientes", element: <Pacientes /> },
          { path: "agendamentos", element: <Agendamentos /> },
          { path: "consultas", element: <Consultas /> },
          { path: "prontuarios", element: <Prontuarios /> },
          { path: "receitas", element: <Receitas /> },
        ],
      },
    ],
  },
  {
    path: "*",
    element: <Navigate to="/dashboard" replace />,
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <GlobalStyled />
    <UserProvider>
      <RouterProvider router={router} />
    </UserProvider>
  </React.StrictMode>
);
