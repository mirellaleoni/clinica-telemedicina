CREATE TABLE pagamentos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    agendamento_id UUID NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    forma_pagamento VARCHAR(50) NOT NULL,
    status VARCHAR(30) NOT NULL,
    CONSTRAINT fk_pagamentos_agendamento
        FOREIGN KEY (agendamento_id)
        REFERENCES agenda(id)
);
