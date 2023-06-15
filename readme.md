# Projeto Vida Loka

## Docker

### Comandos

-docker ps
    -lista os containers que estão ativos no momentos

-docker-compose up
    -inicializa todos os containers que estão listados dentro do docker-compose.yml

-docker-compose down
    -mata os containers

-docker image ls
    -lista as imagens

---
Comandos SQL:

- para selecionar todos os dados de uma tabela
> select * from nome_tabela

- Para selecionar uma determinada informação com base em um critério
> select * from nome_tabela where parametro = valor
> 
> exemplo:
> select * from usuario where id = 2
> 
> select * from usuario where nome = 'xxxx'
> 
> select * from loja where id = 2 and numero_funcionarios >= 5
> 
> select * from loja where id = 3 and (numero_funcionarios > 5 or nome = 'lalala')
> 
> select nome, logo from loja
> 
> Para inserir dados em uma tabela preexistente
> 
> insert into loja(id, nome, logo, numero_funcionarios)
values (4, 'pei', 'pou', 15)
> 
> Para editar
> 
> update loja set nome = 'chupin' where id = 4

---

Para criar uma tabela:
> CREATE TABLE nome_tabela (

);

>CREATE TABLE usuario (
id BIGSERIAL,
nome_usuario VARCHAR(50) NOT NULL,
nome_completo VARCHAR(100),
PRIMARY KEY(id)

);

Para adicionar elementos:

INSERT INTO usuario(nome_usuario, nome_completo)
VALUES ('red', 'marcelo pipo red bull');


INSERT INTO usuario(nome_usuario, nome_completo)
VALUES ('totonho', 'luis antonio');

Criando uma tabela para associar duas outras:

CREATE TABLE user_jogo (
usuario_id_fk BIGINT NOT NULL,
jogo_id_fk BIGINT NOT NULL,
nota BIGINT DEFAULT 0,
PRIMARY KEY(usuario_id_fk, jogo_id_fk)
);

Acrescentando uma regra para nao colocar informação equivocada (exemplo colocar um id que nao existe)

ALTER TABLE user_jogo
ADD CONSTRAINT usuario_jogo_usuario_fk
FOREIGN KEY (usuario_id_fk)
REFERENCES usuario(id)
ON DELETE CASCADE;

ALTER TABLE user_jogo
ADD CONSTRAINT usuario_jogo_jogo_fk
FOREIGN KEY (jogo_id_fk)
REFERENCES jogo(id)
ON DELETE CASCADE;

Povoando a tabela:

INSERT INTO user_jogo(usuario_id_fk, jogo_id_fk) VALUES(1,1);


INSERT INTO user_jogo(usuario_id_fk, jogo_id_fk) VALUES(2,1);
INSERT INTO user_jogo(usuario_id_fk, jogo_id_fk) VALUES(2,2);

Consultando
SELECT * FROM  usuario
INNER JOIN user_jogo ON id = usuario_id_fk
WHERE id = 2

Usando apelidos para espicificar os ids
SELECT * FROM  usuario u
INNER JOIN user_jogo uj ON u.id = uj.usuario_id_fk
INNER JOIN jogo j ON j.id = uj.jogo_id_fk
WHERE u.id = 2

SELECT  j.nome, j.plataforma, j.valor, uj.nota FROM  usuario u
INNER JOIN user_jogo uj ON u.id = uj.usuario_id_fk
INNER JOIN jogo j ON j.id = uj.jogo_id_fk
WHERE u.id = 2

