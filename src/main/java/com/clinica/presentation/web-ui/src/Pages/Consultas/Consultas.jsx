import { useState } from "react";
import { finishConsulta, getConsulta, startConsulta } from "../../services/clinicService";
import { formatDateTime, readApiError, shortId } from "../../utils/formatters";
import { Badge, Field, FormGrid, Message, Panel, PanelHeader, PrimaryButton, Row, SecondaryButton, SplitGrid } from "../../Components/UI/Surface";

function Consultas() {
    const [consultaId, setConsultaId] = useState("");
    const [consulta, setConsulta] = useState(null);
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    async function search(event) {
        event.preventDefault();
        setMessage("");
        setError("");
        setConsulta(null);

        try {
            setConsulta(await getConsulta(consultaId));
        } catch (err) {
            setError(readApiError(err, "Consulta nao encontrada."));
        }
    }

    async function run(action) {
        setMessage("");
        setError("");

        try {
            const data = action === "start" ? await startConsulta(consulta.id) : await finishConsulta(consulta.id);
            setConsulta(data);
            setMessage(action === "start" ? "Consulta iniciada." : "Consulta finalizada.");
        } catch (err) {
            setError(readApiError(err));
        }
    }

    return (
        <SplitGrid>
            <Panel>
                <PanelHeader>
                    <div>
                        <h2>Buscar consulta</h2>
                        <p>Informe o ID da consulta para iniciar ou finalizar atendimento.</p>
                    </div>
                </PanelHeader>

                <FormGrid onSubmit={search}>
                    <Field>
                        Consulta ID
                        <input value={consultaId} onChange={(event) => setConsultaId(event.target.value)} required />
                    </Field>

                    {error && <Message $type="error">{error}</Message>}
                    {message && <Message>{message}</Message>}

                    <PrimaryButton type="submit">Buscar</PrimaryButton>
                </FormGrid>
            </Panel>

            <Panel>
                <PanelHeader>
                    <div>
                        <h2>Atendimento</h2>
                        <p>Dados retornados pela API de consultas.</p>
                    </div>
                </PanelHeader>

                {consulta ? (
                    <FormGrid as="div">
                        <p><strong>ID:</strong> {shortId(consulta.id)} ({consulta.id})</p>
                        <p><strong>Agendamento:</strong> {consulta.agendamentoId}</p>
                        <p><strong>Link:</strong> <a href={consulta.linkVideochamada} target="_blank" rel="noreferrer">{consulta.linkVideochamada}</a></p>
                        <p><strong>Inicio:</strong> {formatDateTime(consulta.iniciadaEm)}</p>
                        <p><strong>Fim:</strong> {formatDateTime(consulta.finalizadaEm)}</p>
                        <p>
                            <Badge $tone={consulta.finalizadaEm ? "success" : consulta.iniciadaEm ? "neutral" : "danger"}>
                                {consulta.finalizadaEm ? "Finalizada" : consulta.iniciadaEm ? "Em atendimento" : "Nao iniciada"}
                            </Badge>
                        </p>
                        <Row>
                            <SecondaryButton type="button" onClick={() => run("start")} disabled={Boolean(consulta.iniciadaEm)}>
                                Iniciar
                            </SecondaryButton>
                            <PrimaryButton type="button" onClick={() => run("finish")} disabled={!consulta.iniciadaEm || Boolean(consulta.finalizadaEm)}>
                                Finalizar
                            </PrimaryButton>
                        </Row>
                    </FormGrid>
                ) : (
                    <p>Busque uma consulta para ver os detalhes.</p>
                )}
            </Panel>
        </SplitGrid>
    );
}

export default Consultas;
