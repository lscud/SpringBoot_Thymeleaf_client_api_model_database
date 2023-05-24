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