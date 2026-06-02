CREATE TABLE disponibilidades (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    medico_id UUID NOT NULL,
    dia_semana SMALLINT NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,
    CONSTRAINT fk_disponibilidades_medico
        FOREIGN KEY (medico_id)
        REFERENCES medicos(id),
    CONSTRAINT chk_disponibilidades_dia_semana
        CHECK (dia_semana BETWEEN 0 AND 7),
    CONSTRAINT chk_disponibilidades_horario
        CHECK (hora_fim > hora_inicio)
);
