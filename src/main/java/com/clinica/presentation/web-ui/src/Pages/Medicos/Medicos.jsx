import { useCallback, useEffect, useState } from "react";
import { createMedico, deleteMedico, listMedicos, updateMedico } from "../../services/clinicService";
import { readApiError, shortId } from "../../utils/formatters";
import { Badge, DangerButton, DataTable, EmptyState, Field, FormGrid, Message, Panel, PanelHeader, PrimaryButton, Row, SecondaryButton, SplitGrid, TableWrap } from "../../Components/UI/Surface";

const emptyForm = {
    nome: "",
    cpf: "",
    crmNumero: "",
    crmUf: "",
    email: "",
    especialidade: "",
    ativo: true,
    usuarioId: "",
};

function Medicos() {
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
            setItems(await listMedicos());
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
        const { name, value, type, checked } = event.target;
        setForm((current) => ({ ...current, [name]: type === "checkbox" ? checked : value }));
    }

    function edit(item) {
        setEditing(item.id);
        setForm({
            ...emptyForm,
            nome: item.nome || "",
            email: item.email || "",
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
                await updateMedico(editing, form);
                setMessage("Medico atualizado com sucesso.");
            } else {
                await createMedico(form);
                setMessage("Medico cadastrado com sucesso.");
            }
            reset();
            await load(true);
        } catch (err) {
            setError(readApiError(err));
        }
    }

    async function remove(id) {
        try {
            await deleteMedico(id);
            setMessage("Medico desativado com sucesso.");
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
                        <h2>{editing ? "Editar medico" : "Novo medico"}</h2>
                        <p>{editing ? "A API permite alterar nome e e-mail." : "Informe os dados obrigatorios para cadastro."}</p>
                    </div>
                </PanelHeader>

                <FormGrid onSubmit={submit}>
                    <Field>
                        Nome
                        <input name="nome" value={form.nome} onChange={change} required />
                    </Field>
                    <Field>
                        E-mail
                        <input name="email" type="email" value={form.email} onChange={change} required />
                    </Field>

                    {!editing && (
                        <>
                            <Field>
                                CPF
                                <input name="cpf" value={form.cpf} onChange={change} required />
                            </Field>
                            <Row>
                                <Field>
                                    CRM
                                    <input name="crmNumero" value={form.crmNumero} onChange={change} required />
                                </Field>
                                <Field>
                                    UF
                                    <input name="crmUf" value={form.crmUf} onChange={change} maxLength="2" required />
                                </Field>
                            </Row>
                            <Field>
                                Especialidade
                                <input name="especialidade" value={form.especialidade} onChange={change} required />
                            </Field>
                            <Field>
                                Usuario ID
                                <input name="usuarioId" value={form.usuarioId} onChange={change} placeholder="Opcional" />
                            </Field>
                            <Field>
                                <Row>
                                    <input name="ativo" type="checkbox" checked={form.ativo} onChange={change} />
                                    Ativo
                                </Row>
                            </Field>
                        </>
                    )}

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
                        <h2>Lista de medicos</h2>
                        <p>{loading ? "Carregando..." : `${items.length} registro(s)`}</p>
                    </div>
                </PanelHeader>

                {items.length === 0 ? (
                    <EmptyState>Nenhum medico cadastrado.</EmptyState>
                ) : (
                    <TableWrap>
                        <DataTable>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nome</th>
                                    <th>CRM</th>
                                    <th>E-mail</th>
                                    <th>Status</th>
                                    <th>Acoes</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items.map((item) => (
                                    <tr key={item.id}>
                                        <td>{shortId(item.id)}</td>
                                        <td>{item.nome}</td>
                                        <td>{item.crm || "-"}</td>
                                        <td>{item.email}</td>
                                        <td><Badge $tone={item.ativo ? "success" : "danger"}>{item.ativo ? "Ativo" : "Inativo"}</Badge></td>
                                        <td>
                                            <Row>
                                                <SecondaryButton type="button" onClick={() => edit(item)}>Editar</SecondaryButton>
                                                <DangerButton type="button" onClick={() => remove(item.id)}>Desativar</DangerButton>
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

export default Medicos;
