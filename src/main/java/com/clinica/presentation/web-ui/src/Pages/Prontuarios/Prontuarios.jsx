import { useState } from "react";
import { createProntuario, getProntuario } from "../../services/clinicService";
import { formatDateTime, readApiError, shortId } from "../../utils/formatters";
import { Field, FormGrid, Message, Panel, PanelHeader, PrimaryButton, SecondaryButton, SplitGrid } from "../../Components/UI/Surface";

const emptyForm = {
    consultaId: "",
    observacoes: "",
    prescricao: "",
};

function Prontuarios() {
    const [form, setForm] = useState(emptyForm);
    const [searchId, setSearchId] = useState("");
    const [prontuario, setProntuario] = useState(null);
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    function change(event) {
        const { name, value } = event.target;
        setForm((current) => ({ ...current, [name]: value }));
    }

    async function submit(event) {
        event.preventDefault();
        setMessage("");
        setError("");

        try {
            const data = await createProntuario(form);
            setProntuario(data);
            setSearchId(data.id || "");
            setForm(emptyForm);
            setMessage("Prontuario criado com sucesso.");
        } catch (err) {
            setError(readApiError(err));
        }
    }

    async function search(event) {
        event.preventDefault();
        setMessage("");
        setError("");

        try {
            setProntuario(await getProntuario(searchId));
        } catch (err) {
            setError(readApiError(err, "Prontuario nao encontrado."));
        }
    }

    return (
        <SplitGrid>
            <Panel>
                <PanelHeader>
                    <div>
                        <h2>Novo prontuario</h2>
                        <p>Crie o registro clinico vinculado a uma consulta.</p>
                    </div>
                </PanelHeader>

                <FormGrid onSubmit={submit}>
                    <Field>
                        Consulta ID
                        <input name="consultaId" value={form.consultaId} onChange={change} required />
                    </Field>
                    <Field>
                        Observacoes
                        <textarea name="observacoes" value={form.observacoes} onChange={change} required />
                    </Field>
                    <Field>
                        Prescricao
                        <textarea name="prescricao" value={form.prescricao} onChange={change} required />
                    </Field>

                    {error && <Message $type="error">{error}</Message>}
                    {message && <Message>{message}</Message>}

                    <PrimaryButton type="submit">Criar prontuario</PrimaryButton>
                </FormGrid>
            </Panel>

            <Panel>
                <PanelHeader>
                    <div>
                        <h2>Buscar prontuario</h2>
                        <p>Consulte um registro existente pelo ID.</p>
                    </div>
                </PanelHeader>

                <FormGrid onSubmit={search}>
                    <Field>
                        Prontuario ID
                        <input value={searchId} onChange={(event) => setSearchId(event.target.value)} required />
                    </Field>
                    <SecondaryButton type="submit">Buscar</SecondaryButton>
                </FormGrid>

                {prontuario && (
                    <FormGrid as="div" style={{ marginTop: 18 }}>
                        <p><strong>ID:</strong> {shortId(prontuario.id)} ({prontuario.id})</p>
                        <p><strong>Consulta:</strong> {prontuario.consultaId}</p>
                        <p><strong>Criado em:</strong> {formatDateTime(prontuario.criadoEm)}</p>
                        <p><strong>Observacoes:</strong> {prontuario.observacoes}</p>
                        <p><strong>Prescricao:</strong> {prontuario.prescricao}</p>
                    </FormGrid>
                )}
            </Panel>
        </SplitGrid>
    );
}

export default Prontuarios;
