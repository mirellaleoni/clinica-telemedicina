import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Link, useNavigate } from "react-router-dom";
import { loginSchema } from "../../schemas/loginSchema.js";
import { loginService } from "../../services/authService";
import Button from "../../Components/Button/Button";
import Input from "../../Components/Input/Input";
import { LoginCard, LoginPage, LoginPanel } from "./LoginStyled";

function Login() {
    const navigate = useNavigate();
    const [serverError, setServerError] = useState("");

    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: zodResolver(loginSchema),
    });

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            navigate("/dashboard");
        }
    }, [navigate]);

    async function onSubmit(data) {
        try {
            setServerError("");
            const response = await loginService(data);
            localStorage.setItem("token", response.token);
            navigate("/dashboard");
        } catch (error) {
            if (error.response && error.response.data) {
                setServerError(error.response.data);
            } else {
                setServerError("Erro ao conectar com o servidor.");
            }
        }
    }

    return (
        <LoginPage>
            <LoginPanel>
                <span>CT</span>
                <h1>Clinica Telemedicina</h1>
                <p>Acesse o painel para gerenciar medicos, pacientes, agenda e atendimentos online.</p>
            </LoginPanel>

            <LoginCard>
                <h2>Entrar</h2>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div>
                        <Input
                            name="email"
                            type="email"
                            placeholder="Digite seu e-mail"
                            register={register}
                        />
                        {errors.email && <small>{errors.email.message}</small>}
                    </div>

                    <div>
                        <Input
                            name="senha"
                            type="password"
                            placeholder="Digite sua senha"
                            register={register}
                        />
                        {errors.senha && <small>{errors.senha.message}</small>}
                    </div>

                    {serverError && <strong>{serverError}</strong>}

                    <Button text="Entrar" type="submit" />
                    <Link to="/cadastro">Criar novo acesso</Link>
                </form>
            </LoginCard>
        </LoginPage>
    );
}

export default Login;
