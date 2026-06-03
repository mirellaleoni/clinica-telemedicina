CREATE TABLE receitas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    consulta_id UUID NOT NULL,
    medicamentos TEXT NOT NULL,
    instrucoes TEXT,
    emitida_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_receitas_consulta
        FOREIGN KEY (consulta_id)
        REFERENCES consultas(id)
);

