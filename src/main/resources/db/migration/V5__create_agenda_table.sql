CREATE TABLE agenda (
    id SERIAL PRIMARY KEY,
    paciente_id INTEGER NOT NULL,
    medico_id INTEGER NOT NULL,
    data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    criado_em TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL
    ADD CONSTRAINT fk_agenda_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    ADD CONSTRAINT fk_agenda_medico FOREIGN KEY (medico_id) REFERENCES medicos(id);
);
