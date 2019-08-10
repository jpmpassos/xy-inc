## Projeto XyInc

Para poder rodar este projeto é necessário ter o Postgresql instalado e criar um banco de dados com o nome db_xy_inc.

```markdown
CREATE DATABASE db_xy_inc
  WITH ENCODING = 'UTF8';
```

Depois deve configurar o arquivo `application.properties` com as informações de usuário senha e host do banco de dados.

```markdown
spring.datasource.url=jdbc:postgresql://localhost:5432/db_xy_inc
spring.datasource.username=postgres
spring.datasource.password=admin123@
```

### Markdown

Foram criados 6 serviços para manipulação e listagem dos pontos.

Abaixo segue um exemplo do objeto json que representa a classe Ponto.

```markdown
  {
        "pontoid": 2,
        "nome": "Posto",
        "coordenadax": 31,
        "coordenaday": 18
  }
```
Através do endpoint http://localhost:8080/ponto é possível consumir diversos serviços alternando apenas o verbo http.

Para gravar um novo ponto, deve realizar uma chamada POST para o endpoint acima, passando no Body o objeto json Ponto sem a propriedade pontoid.

Para recuperar todos os registros basta realizar uma chamada GET para o endpoint acima sem nenhum parâmetro.

Para listar os pontos por proximidade, é necessário realizar uma chamada do tipo GET para o endpoint com os respectivos parâmetros:
http://localhost:8080/ponto/porproximidade?x=20&y=10&distancia=10

Foi adicionado o arquivo `Aplicação XYInc - Teste Zup.postman_collection.json` que pode ser importado pelo seu Postman, onde terá um exemplo de chamada de todos os serviços criado.

### Observações

Ao analisar o código, notará que foi desenvolvido uma função `caucularDistanciaDeCoodenadas` que calcula a distancia entre duas coordenadas, que é usada na função `listarPorProximidadeJava` dentro da classe `PontoSevice`. Já a função `listarPorProximidadeJava` recupera uma lista de todos os pontos existente no banco de dados e realiza um filtro através da API stream, onde separa apenas os pontos com distancia inferior distância e ponto passados como parâmetro.

Mas essa função não é utilizada na chamada do serviço `ponto/porproximidade`, por achar que o filtro sendo realizado no próprio select terá uma melhor performance para uma grande quantidade de informações.

Sendo assim a função utilizado é a `listarPorProximidadeBanco` que realiza uma busca no banco de dados pelos registros tenha distância inferior ou igual a distância e ponto passados como parâmetro.
