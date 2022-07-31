# Projeto Solicitação Ordem de Serviço (O.S.)


- Java 8;
- Web;
- MVC;


* Cadastro + Consulta: 
 	-Computador;
	- Laborátio;
	- Funcionário;
	- Computador;
	- Tipo de Serviço;
	- Mensagem de status.
	

- Popular banco:
```
psql -U root -d web2n2 < /zoom-dev.sql
``

### Docker

Acessar container
```
docker exec -ti postgresql bash

```

* Rodar no terminal apenas uma vez:
```
docker-compose exec -T postgresql psql -U postgres -d web2n2 < Solicitacao.sql
```

acessar o banco
```
docker exec -it postgresql psql -U postgres
``` 
