CREATE TABLE agendamentos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    paciente_id UUID NOT NULL,
    medico_id UUID NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_agendamentos_paciente
        FOREIGN KEY (paciente_id)
        REFERENCES pacientes(id),
    CONSTRAINT fk_agendamentos_medico
        FOREIGN KEY (medico_id)
        REFERENCES medicos(id)
);
