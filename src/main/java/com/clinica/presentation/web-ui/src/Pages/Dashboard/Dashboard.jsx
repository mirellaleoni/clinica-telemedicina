import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { listAgendamentos, listMedicos, listPacientes } from "../../services/clinicService";
import { formatDateTime, readApiError, shortId } from "../../utils/formatters";
import { Badge, DataTable, EmptyState, Message, PageGrid, Panel, PanelHeader, StatCard, StatGrid, TableWrap } from "../../Components/UI/Surface";

function Dashboard() {
    const [state, setState] = useState({
        medicos: [],
        pacientes: [],
        agendamentos: [],
        loading: true,
        error: "",
    });

    useEffect(() => {
        async function load() {
            try {
                const [medicos, pacientes, agendamentos] = await Promise.all([
                    listMedicos(),
                    listPacientes(),
                    listAgendamentos(),
                ]);
                setState({ medicos, pacientes, agendamentos, loading: false, error: "" });
            } catch (error) {
                setState((current) => ({ ...current, loading: false, error: readApiError(error) }));
            }
        }

        load();
    }, []);

    const proximos = [...state.agendamentos]
        .sort((a, b) => new Date(a.dataHora) - new Date(b.dataHora))
        .slice(0, 5);

    return (
        <PageGrid>
            {state.error && <Message $type="error">{state.error}</Message>}

            <StatGrid>
                <StatCard>
                    <span>Medicos</span>
                    <strong>{state.loading ? "-" : state.medicos.length}</strong>
                </StatCard>
                <StatCard>
                    <span>Pacientes</span>
                    <strong>{state.loading ? "-" : state.pacientes.length}</strong>
                </StatCard>
                <StatCard>
                    <span>Agendamentos</span>
                    <strong>{state.loading ? "-" : state.agendamentos.length}</strong>
                </StatCard>
                <StatCard>
                    <span>Online</span>
                    <strong>{state.loading ? "-" : state.agendamentos.filter((item) => item.tipo === "ONLINE").length}</strong>
                </StatCard>
            </StatGrid>

            <Panel>
                <PanelHeader>
                    <div>
                        <h2>Proximos agendamentos</h2>
                        <p>As cinco consultas mais proximas cadastradas na agenda.</p>
                    </div>
                </PanelHeader>

                {proximos.length === 0 ? (
                    <EmptyState>Nenhum agendamento encontrado.</EmptyState>
                ) : (
                    <TableWrap>
                        <DataTable>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Paciente</th>
                                    <th>Medico</th>
                                    <th>Data</th>
                                    <th>Status</th>
                                    <th>Tipo</th>
                                </tr>
                            </thead>
                            <tbody>
                                {proximos.map((item) => (
                                    <tr key={item.id}>
                                        <td>
                                            <Link to="/agendamentos">{shortId(item.id)}</Link>
                                        </td>
                                        <td>{shortId(item.pacienteId)}</td>
                                        <td>{shortId(item.medicoId)}</td>
                                        <td>{formatDateTime(item.dataHora)}</td>
                                        <td><Badge $tone="success">{item.status}</Badge></td>
                                        <td>{item.tipo}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </DataTable>
                    </TableWrap>
                )}
            </Panel>
        </PageGrid>
    );
}

export default Dashboard;
