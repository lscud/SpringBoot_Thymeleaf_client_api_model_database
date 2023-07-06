CREATE DATABASE db_lds WITH OWNER = postgres
ENCODING = 'UTF8' CONNECTION LIMIT = -1;

DROP TABLE comentario
DROP TABLE usuario_jogo
DROP TABLE imagem_jogo
DROP TABLE jogo;
DROP TABLE usuario;




CREATE TABLE usuario
(
    id BIGSERIAL,
    nome_usuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR (50) NOT NULL,
    nome_completo VARCHAR (100) NOT NULL,
    email VARCHAR (50) NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    esta_ativo BOOLEAN NOT NULL DEFAULT true,
    data_nascimento TIMESTAMP NOT NULL,
    ultimo_acesso TIMESTAMP NOT NULL,
    criado_em TIMESTAMP NOT NULL,
    avatar bytea,
    PRIMARY KEY(id)
);

CREATE TABLE jogo
(
    id BIGSERIAL,
    genero VARCHAR(50) NOT NULL,
    nome VARCHAR(50) UNIQUE NOT NULL,
    descricao VARCHAR NOT NULL,
    preco DECIMAL DEFAULT 349.90,
    desenvolvido_por VARCHAR(100) NOT NULL,
    data_lancamento VARCHAR(50) DEFAULT '-',
    plataforma VARCHAR(50) NOT NULL,
    imagem_principal VARCHAR,
    PRIMARY KEY(id)
);

CREATE TABLE imagem_jogo
(
    id BIGSERIAL,
    jogo_id_fk BIGINT NOT NULL,
    imagem VARCHAR,
    primary KEY(id)
);

ALTER TABLE imagem_jogo
ADD CONSTRAINT imagem_jogo_jogo_fk
FOREIGN KEY (jogo_id_fk)
REFERENCES jogo(id)
ON DELETE CASCADE;

CREATE TABLE usuario_jogo
(
    usuario_id_fk BIGINT NOT NULL,
    jogo_id_fk BIGINT NOT NULL,
    nota BIGINT NOT NULL,
    PRIMARY KEY(usuario_id_fk, jogo_id_fk)
);

--ALTER TABLE usuario_jogo
--ADD CONSTRAINT usuario_jogo_usuario_fk
--FOREIGN KEY (usuario_id_fk)
--REFERENCES usuario (id)
--ON DELETE SET NULL;
--
--ALTER TABLE usuario_jogo
--ADD CONSTRAINT usuario_jogo_jogo_fk
--FOREIGN KEY (jogo_id_fk)
--REFERENCES jogo (id)
--ON DELETE SET NULL;

CREATE TABLE comentario
(
    id BIGSERIAL,
    jogo_id_fk BIGINT NOT NULL,
    usuario_id_fk BIGINT NOT NULL,
    enviado_em TIMESTAMP NOT NULL,
    texto_do_comentario VARCHAR(300) NOT NULL,
    PRIMARY KEY(id)

);

--ALTER TABLE comentario
--ADD CONSTRAINT comentario_jogo_fk
--FOREIGN KEY (jogo_id_fk)
--REFERENCES jogo(id)
--ON DELETE SET NULL;
--
--ALTER TABLE comentario
--ADD CONSTRAINT comentario_usuario_fk
--FOREIGN KEY (usuario_id_fk)
--REFERENCES usuario(id)
--ON DELETE SET NULL;

INSERT INTO usuario
(nome_usuario, senha, nome_completo, email, tipo, data_nascimento, ultimo_acesso, criado_em)
VALUES
('admin', 'admin', 'Administrador', 'admin@gmail.com', 'ADMINISTRADOR', '1985-02-02 21:00:00-03', now(), now());

INSERT INTO usuario
(nome_usuario, senha, nome_completo, email, tipo, data_nascimento, ultimo_acesso, criado_em)
VALUES
('tibursinho', '123456', 'tibursinho tibursion', 'tibu@gmail.com', 'CLIENTE', '1986-04-04 21:00:00-03', now(), now());

INSERT INTO usuario
(nome_usuario, senha, nome_completo, email, tipo, data_nascimento, ultimo_acesso, criado_em)
VALUES
('Aroudo', '123456', 'Aroudin', 'aoudo@gmail.com', 'CLIENTE', '1989-02-02 21:00:00-03', now(), now());



INSERT INTO jogo
(genero, nome, descricao, desenvolvido_por, plataforma)
VALUES
('MMORPG', 'Final Fantasy', 'Joguin que o red nao quer jogar', 'Square', 'PSX');

INSERT INTO jogo
(genero, nome, descricao, desenvolvido_por, plataforma)
VALUES
('GACHA', 'Grand Summoners', 'Joguin que deixa geral frustrado', 'NEXT NINJA', 'MOBILE');

INSERT INTO jogo
(genero, nome, descricao, desenvolvido_por, plataforma)
VALUES
('SHOOTER', 'CS', 'Joguin que deixa geral redshotado', 'VALVE', 'PC');

INSERT INTO comentario
(jogo_id_fk, usuario_id_fk, enviado_em, texto_do_comentario)
VALUES
(1, 1, now(), 'comentario 01 do jogo 1 user 1');

INSERT INTO comentario
(jogo_id_fk, usuario_id_fk, enviado_em, texto_do_comentario)
VALUES
(1, 2, now(), 'comentario 02 jogo 1 user 2');

INSERT INTO comentario
(jogo_id_fk, usuario_id_fk, enviado_em, texto_do_comentario)
VALUES
(2, 3, now(), 'comentario 03 jogo 2 user 3');


