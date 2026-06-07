import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { createUsuario } from "../../services/clinicService";
import { readApiError } from "../../utils/formatters";
import { Field, FormGrid, Message, PrimaryButton } from "../../Components/UI/Surface";
import { LoginCard, LoginPage, LoginPanel } from "../Login/LoginStyled";

const emptyForm = {
    nome: "",
    email: "",
    senha: "",
    papel: "RECEPCIONISTA",
};

function Cadastro() {
    const navigate = useNavigate();
    const [form, setForm] = useState(emptyForm);
    const [error, setError] = useState("");

    function change(event) {
        const { name, value } = event.target;
        setForm((current) => ({ ...current, [name]: value }));
    }

    async function submit(event) {
        event.preventDefault();
        setError("");

        try {
            await createUsuario(form);
            navigate("/");
        } catch (err) {
            setError(readApiError(err));
        }
    }

    return (
        <LoginPage>
            <LoginPanel>
                <span>CT</span>
                <h1>Criar acesso</h1>
                <p>Cadastre um usuario para acessar o sistema da clinica.</p>
            </LoginPanel>

            <LoginCard>
                <h2>Novo usuario</h2>
                <FormGrid onSubmit={submit}>
                    <Field>
                        Nome
                        <input name="nome" value={form.nome} onChange={change} required />
                    </Field>
                    <Field>
                        E-mail
                        <input name="email" type="email" value={form.email} onChange={change} required />
                    </Field>
                    <Field>
                        Senha
                        <input name="senha" type="password" minLength="6" value={form.senha} onChange={change} required />
                    </Field>
                    <Field>
                        Papel
                        <select name="papel" value={form.papel} onChange={change} required>
                            <option value="ADMINISTRADOR">Administrador</option>
                            <option value="RECEPCIONISTA">Recepcionista</option>
                            <option value="MEDICO">Medico</option>
                            <option value="PACIENTE">Paciente</option>
                        </select>
                    </Field>

                    {error && <Message $type="error">{error}</Message>}

                    <PrimaryButton type="submit">Cadastrar</PrimaryButton>
                    <Link to="/">Voltar para login</Link>
                </FormGrid>
            </LoginCard>
        </LoginPage>
    );
}

export default Cadastro;
