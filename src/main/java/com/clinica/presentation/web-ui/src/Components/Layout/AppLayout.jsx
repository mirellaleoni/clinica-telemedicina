import { NavLink, Outlet, useLocation, useNavigate } from "react-router-dom";
import {
    Actions,
    AppShell,
    Brand,
    Content,
    Header,
    HeaderText,
    Nav,
    Sidebar,
    SignOutButton,
} from "./AppLayoutStyled";

const menuItems = [
    { path: "/dashboard", label: "Painel" },
    { path: "/medicos", label: "Medicos" },
    { path: "/pacientes", label: "Pacientes" },
    { path: "/agendamentos", label: "Agendamentos" },
    { path: "/consultas", label: "Consultas" },
    { path: "/prontuarios", label: "Prontuarios" },
    { path: "/receitas", label: "Receitas" },
];

const titles = {
    "/dashboard": ["Painel da clinica", "Resumo rapido da operacao de telemedicina"],
    "/medicos": ["Medicos", "Cadastro e manutencao dos profissionais"],
    "/pacientes": ["Pacientes", "Base de pacientes atendidos pela clinica"],
    "/agendamentos": ["Agendamentos", "Agenda de consultas presenciais e online"],
    "/consultas": ["Consultas", "Controle de inicio, link e finalizacao"],
    "/prontuarios": ["Prontuarios", "Registro clinico vinculado a consulta"],
    "/receitas": ["Receitas", "Emissao e busca de prescricoes"],
};

function AppLayout() {
    const navigate = useNavigate();
    const location = useLocation();
    const [title, subtitle] = titles[location.pathname] || titles["/dashboard"];

    function logout() {
        localStorage.removeItem("token");
        navigate("/");
    }

    return (
        <AppShell>
            <Sidebar>
                <Brand>
                    <span>CT</span>
                    <strong>Clinica Telemedicina</strong>
                </Brand>

                <Nav>
                    {menuItems.map((item) => (
                        <NavLink key={item.path} to={item.path}>
                            {item.label}
                        </NavLink>
                    ))}
                </Nav>
            </Sidebar>

            <Content>
                <Header>
                    <HeaderText>
                        <h1>{title}</h1>
                        <p>{subtitle}</p>
                    </HeaderText>
                    <Actions>
                        <SignOutButton type="button" onClick={logout}>
                            Sair
                        </SignOutButton>
                    </Actions>
                </Header>

                <Outlet />
            </Content>
        </AppShell>
    );
}

export default AppLayout;
