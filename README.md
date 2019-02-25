# Bionexo Challenge: API



## Rotas
| Método HTTP | Rota | Descrição |
| ----------- | ---- | --------- |
| **POST** | /rest/ubs | Registra UBS |
| **GET** | /rest/ubs?latitude={latitude}&longitude={longitude} | Permite a listagem de UBS's registradas com base posição atual e filtrada por nome. |
| **GET** | /rest/ubs/<id> | Permite a exibição de informações de uma única UBS. |

## Documentação Swagger
http://localhost:8080/api/swagger-ui.html#/

## Configurações
Configurar no arquivo  application.properties distancia minima em (Metros). Com base nesse parâmetro a api filtrará

**parameter.distance.meters = 2500**

A Api vai calcular a distância com base latitude e longitude que usuário informar.O retorno deste calculo será em metros

Sendo assim as UBS's que estiverem dentro do range será retornada pela api


- Foi feito uma massa de dados com liquibase 

| Name             | Latitude   | Longitude   |
| -----------------|------------|------------ |
|UBS Jardim Europa | -23.617338 |	-46.674593  | 
|UBS Cidade Jardim | -23.616041	| -46.687210  |
|UBS Morumbi	     | -23.626343	| -46.691802  |
|UBS Itaim Bibi	   | -23.615516	| -46.630666  |



## RUN
mvn spring-boot:run

## DOCKER
docker-compose up --build

## Retorna uma lista de ubs com base na posição atual
http://localhost:8080/api/rest/ubs?latitude=-23.621542&longitude=-46.679222

## Retorna uma Ubs
http://localhost:8080/api/rest/ubs/1

