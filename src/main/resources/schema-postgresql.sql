CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    email VARCHAR(100),
    senha VARCHAR(255)
    );


CREATE TABLE IF NOT EXISTS jogo (
    id SERIAL PRIMARY KEY,
    nomeJogo VARCHAR(100) NOT NULL,
    plataforma VARCHAR(50) NOT NULL,
    descricao TEXT,
    nota NUMERIC(3, 1) DEFAULT 0.0,
    estadojogo VARCHAR(20) NOT NULL,
    usuario_id INT NOT NULL,

    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);
