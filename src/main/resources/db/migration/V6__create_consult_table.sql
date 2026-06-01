CREATE TABLE consultas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    agendamento_id UUID NOT NULL,
    link_video VARCHAR(255),
    iniciada_em TIMESTAMP,
    finalizada_em TIMESTAMP,
    CONSTRAINT fk_consultas_agendamento
        FOREIGN KEY (agendamento_id)
        REFERENCES agendamentos(id)
);
