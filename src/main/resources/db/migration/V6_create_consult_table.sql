CREATE TABLE consultas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    agendamento_id UUID NOT NULL,
    link_video VARCHAR(255) NOT NULL,
    iniciada_em TIMESTAMP,
    finalizada_em TIMESTAMP,
    CONSTRAINT fk_consultas_agendamento
        FOREIGN KEY (agendamento_id)
        REFERENCES agenda(id)
);
