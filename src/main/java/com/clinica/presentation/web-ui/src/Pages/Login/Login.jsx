import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { useNavigate } from "react-router-dom";

import { loginSchema } from "../../schemas/loginSchema.js";

import { loginService } from "../../services/authService";

import Button from "../../Components/Button/Button";
import Input from "../../Components/Input/Input";

function Login() {
    const navigate = useNavigate();
    const [serverError, setServerError] = useState("");

    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: zodResolver(loginSchema)
    });

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            navigate("/home");
        }
    }, [navigate]);

    async function onSubmit(data) {
        try {
            setServerError("");
            console.log(data);
            const response = await loginService(data);
            
            localStorage.setItem("token", response.token); 
            
            navigate("/home");

        } catch (error) {
            console.error(error);
            if (error.response && error.response.data) {
                setServerError(error.response.data);
            } else {
                setServerError("Erro ao conectar com o servidor.");
            }
        }
    }

    return (
        <main>
            <h1>Sistema Médico - Login</h1>
            
            <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <Input 
                        name="email"
                        type="email"
                        placeholder="Digite seu e-mail"
                        register={register}
                    />
                    {errors.email && <p style={{ color: 'red' }}>{errors.email.message}</p>}
                </div>

                <div>
                    <Input 
                        name="senha"
                        type="password"
                        placeholder="Digite sua senha"
                        register={register}
                    />
                    {errors.senha && <p style={{ color: 'red' }}>{errors.senha.message}</p>}
                </div>

                {serverError && <p style={{ color: 'red', fontWeight: 'bold' }}>{serverError}</p>}

                <Button text="Entrar" type="submit" />
            </form>
        </main>
    );
}

export default Login;