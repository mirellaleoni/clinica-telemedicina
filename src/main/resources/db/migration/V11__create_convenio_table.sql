CREATE TABLE convenios (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    cobertura TEXT NOT NULL
);
