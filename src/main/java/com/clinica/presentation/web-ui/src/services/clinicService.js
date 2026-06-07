import { api } from "./api";

export async function listMedicos() {
    const response = await api.get("/medicos");
    return response.data;
}

export async function createMedico(data) {
    const response = await api.post("/medicos", {
        ...data,
        ativo: data.ativo ?? true,
        usuarioId: data.usuarioId || null,
    });
    return response.data;
}

export async function updateMedico(id, data) {
    const response = await api.put(`/medicos/${id}`, {
        nome: data.nome,
        email: data.email,
    });
    return response.data;
}

export async function deleteMedico(id) {
    const response = await api.delete(`/medicos/${id}`);
    return response.data;
}

export async function listPacientes() {
    const response = await api.get("/pacientes");
    return response.data;
}

export async function createPaciente(data) {
    const response = await api.post("/pacientes", data);
    return response.data;
}

export async function updatePaciente(id, data) {
    const response = await api.put(`/pacientes/${id}`, {
        nome: data.nome,
        telefone: data.telefone,
    });
    return response.data;
}

export async function deletePaciente(id) {
    const response = await api.delete(`/pacientes/${id}`);
    return response.data;
}

export async function listAgendamentos() {
    const response = await api.get("/agendamentos");
    return response.data;
}

export async function createAgendamento(data) {
    const response = await api.post("/agendamentos", data);
    return response.data;
}

export async function updateAgendamento(id, data) {
    const response = await api.put(`/agendamentos/${id}`, {
        dataHora: data.dataHora,
    });
    return response.data;
}

export async function deleteAgendamento(id) {
    const response = await api.delete(`/agendamentos/${id}`);
    return response.data;
}

export async function getConsulta(id) {
    const response = await api.get(`/consultas/${id}`);
    return response.data;
}

export async function startConsulta(id) {
    const response = await api.post(`/consultas/${id}/iniciar`);
    return response.data;
}

export async function finishConsulta(id) {
    const response = await api.put(`/consultas/${id}/finalizar`);
    return response.data;
}

export async function getProntuario(id) {
    const response = await api.get(`/prontuarios/${id}`);
    return response.data;
}

export async function createProntuario(data) {
    const response = await api.post("/prontuarios", data);
    return response.data;
}

export async function getReceita(id) {
    const response = await api.get(`/receitas/${id}`);
    return response.data;
}

export async function createReceita(data) {
    const response = await api.post("/receitas", data);
    return response.data;
}

export async function createUsuario(data) {
    const response = await api.post("/usuarios", data);
    return response.data;
}
