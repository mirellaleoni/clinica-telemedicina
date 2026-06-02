CREATE TABLE contas (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    paciente_id UUID NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    vencimento DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    CONSTRAINT fk_contas_paciente
        FOREIGN KEY (paciente_id)
        REFERENCES pacientes(id)
);

