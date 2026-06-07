import { useState } from "react";
import { createReceita, getReceita } from "../../services/clinicService";
import { formatDateTime, readApiError, shortId } from "../../utils/formatters";
import { Field, FormGrid, Message, Panel, PanelHeader, PrimaryButton, SecondaryButton, SplitGrid } from "../../Components/UI/Surface";

const emptyForm = {
    consultaId: "",
    medicamentos: "",
    instrucoes: "",
};

function Receitas() {
    const [form, setForm] = useState(emptyForm);
    const [searchId, setSearchId] = useState("");
    const [receita, setReceita] = useState(null);
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
            const data = await createReceita(form);
            setReceita(data);
            setSearchId(data.id || "");
            setForm(emptyForm);
            setMessage("Receita emitida com sucesso.");
        } catch (err) {
            setError(readApiError(err));
        }
    }

    async function search(event) {
        event.preventDefault();
        setMessage("");
        setError("");

        try {
            setReceita(await getReceita(searchId));
        } catch (err) {
            setError(readApiError(err, "Receita nao encontrada."));
        }
    }

    return (
        <SplitGrid>
            <Panel>
                <PanelHeader>
                    <div>
                        <h2>Emitir receita</h2>
                        <p>A consulta precisa estar finalizada no backend.</p>
                    </div>
                </PanelHeader>

                <FormGrid onSubmit={submit}>
                    <Field>
                        Consulta ID
                        <input name="consultaId" value={form.consultaId} onChange={change} required />
                    </Field>
                    <Field>
                        Medicamentos
                        <textarea name="medicamentos" value={form.medicamentos} onChange={change} required />
                    </Field>
                    <Field>
                        Instrucoes
                        <textarea name="instrucoes" value={form.instrucoes} onChange={change} required />
                    </Field>

                    {error && <Message $type="error">{error}</Message>}
                    {message && <Message>{message}</Message>}

                    <PrimaryButton type="submit">Emitir receita</PrimaryButton>
                </FormGrid>
            </Panel>

            <Panel>
                <PanelHeader>
                    <div>
                        <h2>Buscar receita</h2>
                        <p>Consulte uma prescricao emitida pelo ID.</p>
                    </div>
                </PanelHeader>

                <FormGrid onSubmit={search}>
                    <Field>
                        Receita ID
                        <input value={searchId} onChange={(event) => setSearchId(event.target.value)} required />
                    </Field>
                    <SecondaryButton type="submit">Buscar</SecondaryButton>
                </FormGrid>

                {receita && (
                    <FormGrid as="div" style={{ marginTop: 18 }}>
                        <p><strong>ID:</strong> {shortId(receita.id)} ({receita.id})</p>
                        <p><strong>Consulta:</strong> {receita.consultaId}</p>
                        <p><strong>Emitida em:</strong> {formatDateTime(receita.emitidaEm)}</p>
                        <p><strong>Medicamentos:</strong> {receita.medicamentos}</p>
                        <p><strong>Instrucoes:</strong> {receita.instrucoes}</p>
                    </FormGrid>
                )}
            </Panel>
        </SplitGrid>
    );
}

export default Receitas;
