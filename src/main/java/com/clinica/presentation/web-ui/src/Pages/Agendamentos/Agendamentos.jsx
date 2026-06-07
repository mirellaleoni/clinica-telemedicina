import { useCallback, useEffect, useMemo, useState } from "react";
import { createAgendamento, deleteAgendamento, listAgendamentos, listMedicos, listPacientes, updateAgendamento } from "../../services/clinicService";
import { formatDateTime, readApiError, shortId, toApiDateTime, toInputDateTime } from "../../utils/formatters";
import { Badge, DangerButton, DataTable, EmptyState, Field, FormGrid, Message, Panel, PanelHeader, PrimaryButton, Row, SecondaryButton, SplitGrid, TableWrap } from "../../Components/UI/Surface";

const emptyForm = {
    pacienteId: "",
    medicoId: "",
    dataHora: "",
    tipo: "ONLINE",
};

function Agendamentos() {
    const [items, setItems] = useState([]);
    const [pacientes, setPacientes] = useState([]);
    const [medicos, setMedicos] = useState([]);
    const [form, setForm] = useState(emptyForm);
    const [editing, setEditing] = useState(null);
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const pacienteById = useMemo(() => Object.fromEntries(pacientes.map((item) => [item.id, item.nome])), [pacientes]);
    const medicoById = useMemo(() => Object.fromEntries(medicos.map((item) => [item.id, item.nome])), [medicos]);

    const load = useCallback(async (showLoading = false) => {
        if (showLoading) {
            setLoading(true);
        }
        try {
            const [agendamentos, pacientesData, medicosData] = await Promise.all([
                listAgendamentos(),
                listPacientes(),
                listMedicos(),
            ]);
            setItems(agendamentos);
            setPacientes(pacientesData);
            setMedicos(medicosData);
            setError("");
        } catch (err) {
            setError(readApiError(err));
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        const timer = setTimeout(() => {
            void load();
        }, 0);

        return () => clearTimeout(timer);
    }, [load]);

    function change(event) {
        const { name, value } = event.target;
        setForm((current) => ({ ...current, [name]: value }));
    }

    function edit(item) {
        setEditing(item.id);
        setForm({
            pacienteId: item.pacienteId,
            medicoId: item.medicoId,
            dataHora: toInputDateTime(item.dataHora),
            tipo: item.tipo || "ONLINE",
        });
        setMessage("");
        setError("");
    }

    function reset() {
        setEditing(null);
        setForm(emptyForm);
    }

    async function submit(event) {
        event.preventDefault();
        setMessage("");
        setError("");

        try {
            const payload = { ...form, dataHora: toApiDateTime(form.dataHora) };
            if (editing) {
                await updateAgendamento(editing, payload);
                setMessage("Agendamento remarcado com sucesso.");
            } else {
                await createAgendamento(payload);
                setMessage("Agendamento criado com sucesso.");
            }
            reset();
            await load(true);
        } catch (err) {
            setError(readApiError(err));
        }
    }

    async function cancel(id) {
        try {
            await deleteAgendamento(id);
            setMessage("Agendamento cancelado com sucesso.");
            await load(true);
        } catch (err) {
            setError(readApiError(err));
        }
    }

    return (
        <SplitGrid>
            <Panel>
                <PanelHeader>
                    <div>
                        <h2>{editing ? "Remarcar" : "Novo agendamento"}</h2>
                        <p>{editing ? "A API de edicao altera apenas data e hora." : "Selecione paciente, medico, horario e tipo."}</p>
                    </div>
                </PanelHeader>

                <FormGrid onSubmit={submit}>
                    {!editing && (
                        <>
                            <Field>
                                Paciente
                                <select name="pacienteId" value={form.pacienteId} onChange={change} required>
                                    <option value="">Selecione</option>
                                    {pacientes.map((item) => (
                                        <option key={item.id} value={item.id}>{item.nome}</option>
                                    ))}
                                </select>
                            </Field>
                            <Field>
                                Medico
                                <select name="medicoId" value={form.medicoId} onChange={change} required>
                                    <option value="">Selecione</option>
                                    {medicos.map((item) => (
                                        <option key={item.id} value={item.id}>{item.nome} - {item.especialidade}</option>
                                    ))}
                                </select>
                            </Field>
                            <Field>
                                Tipo
                                <select name="tipo" value={form.tipo} onChange={change} required>
                                    <option value="ONLINE">Online</option>
                                    <option value="PRESENCIAL">Presencial</option>
                                </select>
                            </Field>
                        </>
                    )}
                    <Field>
                        Data e hora
                        <input name="dataHora" type="datetime-local" value={form.dataHora} onChange={change} required />
                    </Field>

                    {error && <Message $type="error">{error}</Message>}
                    {message && <Message>{message}</Message>}

                    <Row>
                        <PrimaryButton type="submit">{editing ? "Salvar horario" : "Agendar"}</PrimaryButton>
                        {editing && <SecondaryButton type="button" onClick={reset}>Cancelar</SecondaryButton>}
                    </Row>
                </FormGrid>
            </Panel>

            <Panel>
                <PanelHeader>
                    <div>
                        <h2>Agenda</h2>
                        <p>{loading ? "Carregando..." : `${items.length} registro(s)`}</p>
                    </div>
                </PanelHeader>

                {items.length === 0 ? (
                    <EmptyState>Nenhum agendamento cadastrado.</EmptyState>
                ) : (
                    <TableWrap>
                        <DataTable>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Paciente</th>
                                    <th>Medico</th>
                                    <th>Data</th>
                                    <th>Tipo</th>
                                    <th>Status</th>
                                    <th>Acoes</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items.map((item) => (
                                    <tr key={item.id}>
                                        <td>{shortId(item.id)}</td>
                                        <td>{pacienteById[item.pacienteId] || shortId(item.pacienteId)}</td>
                                        <td>{medicoById[item.medicoId] || shortId(item.medicoId)}</td>
                                        <td>{formatDateTime(item.dataHora)}</td>
                                        <td>{item.tipo}</td>
                                        <td><Badge $tone={item.status === "CANCELADO" ? "danger" : "success"}>{item.status}</Badge></td>
                                        <td>
                                            <Row>
                                                <SecondaryButton type="button" onClick={() => edit(item)}>Remarcar</SecondaryButton>
                                                <DangerButton type="button" onClick={() => cancel(item.id)}>Cancelar</DangerButton>
                                            </Row>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </DataTable>
                    </TableWrap>
                )}
            </Panel>
        </SplitGrid>
    );
}

export default Agendamentos;
