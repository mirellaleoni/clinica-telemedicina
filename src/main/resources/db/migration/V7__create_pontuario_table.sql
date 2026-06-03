CREATE TABLE prontuarios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    consulta_id UUID NOT NULL,
    observacoes TEXT,
    prescricao TEXT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_prontuarios_consulta
        FOREIGN KEY (consulta_id)
        REFERENCES consultas(id)
);

