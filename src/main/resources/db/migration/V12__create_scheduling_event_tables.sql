CREATE TABLE agendamento_criado (
    id BIGSERIAL PRIMARY KEY,
    agendamento_id UUID,
    data_criacao TIMESTAMP,
    CONSTRAINT fk_agendamento_criado_agendamento
        FOREIGN KEY (agendamento_id)
        REFERENCES agenda(id)
);

CREATE TABLE agendamento_cancelado (
    id BIGSERIAL PRIMARY KEY,
    agendamento_id UUID,
    motivo VARCHAR(255),
    usuario_cancelamento_id BIGINT,
    data_cancelamento TIMESTAMP,
    CONSTRAINT fk_agendamento_cancelado_agendamento
        FOREIGN KEY (agendamento_id)
        REFERENCES agenda(id)
);

CREATE TABLE agendamento_concluido (
    id BIGSERIAL PRIMARY KEY,
    agendamento_id UUID,
    usuario_conclusao_id BIGINT,
    data_conclusao TIMESTAMP,
    CONSTRAINT fk_agendamento_concluido_agendamento
        FOREIGN KEY (agendamento_id)
        REFERENCES agenda(id)
);
