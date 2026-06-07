import { useCallback, useEffect, useState } from "react";
import { createPaciente, deletePaciente, listPacientes, updatePaciente } from "../../services/clinicService";
import { formatDate, readApiError, shortId } from "../../utils/formatters";
import { DangerButton, DataTable, EmptyState, Field, FormGrid, Message, Panel, PanelHeader, PrimaryButton, Row, SecondaryButton, SplitGrid, TableWrap } from "../../Components/UI/Surface";

const emptyForm = {
    nome: "",
    cpf: "",
    dataNascimento: "",
    telefone: "",
};

function Pacientes() {
    const [items, setItems] = useState([]);
    const [form, setForm] = useState(emptyForm);
    const [editing, setEditing] = useState(null);
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const load = useCallback(async (showLoading = false) => {
        if (showLoading) {
            setLoading(true);
        }
        try {
            setItems(await listPacientes());
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
            nome: item.nome || "",
            cpf: item.cpf || "",
            dataNascimento: item.dataNascimento || "",
            telefone: item.telefone || "",
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
            if (editing) {
                await updatePaciente(editing, form);
                setMessage("Paciente atualizado com sucesso.");
            } else {
                await createPaciente(form);
                setMessage("Paciente cadastrado com sucesso.");
            }
            reset();
            await load(true);
        } catch (err) {
            setError(readApiError(err));
        }
    }

    async function remove(id) {
        try {
            await deletePaciente(id);
            setMessage("Paciente removido com sucesso.");
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
                        <h2>{editing ? "Editar paciente" : "Novo paciente"}</h2>
                        <p>{editing ? "A API permite alterar nome e telefone." : "Cadastro basico para atendimento."}</p>
                    </div>
                </PanelHeader>

                <FormGrid onSubmit={submit}>
                    <Field>
                        Nome
                        <input name="nome" value={form.nome} onChange={change} required />
                    </Field>
                    {!editing && (
                        <>
                            <Field>
                                CPF
                                <input name="cpf" value={form.cpf} onChange={change} required />
                            </Field>
                            <Field>
                                Data de nascimento
                                <input name="dataNascimento" type="date" value={form.dataNascimento} onChange={change} required />
                            </Field>
                        </>
                    )}
                    <Field>
                        Telefone
                        <input name="telefone" value={form.telefone} onChange={change} required />
                    </Field>

                    {error && <Message $type="error">{error}</Message>}
                    {message && <Message>{message}</Message>}

                    <Row>
                        <PrimaryButton type="submit">{editing ? "Salvar" : "Cadastrar"}</PrimaryButton>
                        {editing && <SecondaryButton type="button" onClick={reset}>Cancelar</SecondaryButton>}
                    </Row>
                </FormGrid>
            </Panel>

            <Panel>
                <PanelHeader>
                    <div>
                        <h2>Lista de pacientes</h2>
                        <p>{loading ? "Carregando..." : `${items.length} registro(s)`}</p>
                    </div>
                </PanelHeader>

                {items.length === 0 ? (
                    <EmptyState>Nenhum paciente cadastrado.</EmptyState>
                ) : (
                    <TableWrap>
                        <DataTable>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nome</th>
                                    <th>CPF</th>
                                    <th>Nascimento</th>
                                    <th>Telefone</th>
                                    <th>Acoes</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items.map((item) => (
                                    <tr key={item.id}>
                                        <td>{shortId(item.id)}</td>
                                        <td>{item.nome}</td>
                                        <td>{item.cpf}</td>
                                        <td>{formatDate(item.dataNascimento)}</td>
                                        <td>{item.telefone}</td>
                                        <td>
                                            <Row>
                                                <SecondaryButton type="button" onClick={() => edit(item)}>Editar</SecondaryButton>
                                                <DangerButton type="button" onClick={() => remove(item.id)}>Remover</DangerButton>
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

export default Pacientes;
