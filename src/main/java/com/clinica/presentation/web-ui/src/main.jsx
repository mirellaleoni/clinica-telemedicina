import React, { useEffect } from "react";
import ReactDOM from "react-dom/client";
import UserProvider from "./Context/UserContext.jsx";
import { GlobalStyled } from "./GlobalStyled.jsx";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import './index.css'
import App from './App.jsx'
import Login from "./Pages/Login/Login.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/medicos",
    element: <App />,
  },
  {
    path: "/consultas",
    element: <App />,
  },
  {
    path: "/pacientes",
    element: <App />,
  },
  {
    path: "/agendamentos",
    element: <App />,
  },
  {
    path: "/prontuarios",
    element: <App />,
  },
  {
    path: "/receitas",
    element: <App />,
  }
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <GlobalStyled />
    <UserProvider>
      <RouterProvider router={router} />
    </UserProvider>
  </React.StrictMode>
);
