CREATE TABLE medicos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id UUID NOT NULL UNIQUE,
    crm VARCHAR(20) NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true,
    CONSTRAINT fk_medicos_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
);
