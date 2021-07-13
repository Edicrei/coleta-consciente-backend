# Location Service  
  
## Perfis  
  
O serviço conta com os perfis `dev` e `prod`.  
Para alterar o perfil atual, basta alterar o valor da chave `spring.profiles.active` no arquivo `src/main/resources/application.properties`.  
O perfil `dev` está configurado para rodar na porta 8083. Para alterar a porta deste perfil, é necessário alterar o valor da chave `server.port` no arquivo `src/main/resources/application-dev.properties`.  
  
## Json de um local de coleta  
  
A representação de um local de coleta, presente nas requisições e repostas deste serviço, segue o seguinte padrão:  
```json  
{
    "id": Number,
    "name": String,
    "address": {
        "street": String,
        "number": String,
        "complement": String,
        "coordinates": {
            "latitude": Number,
            "longitude": Number
        }
    },
    "material": String,
    "isValid": Boolean,
    "registrationDate": DateTime,
    "updateDate": DateTime,
    "userId": Number
}
```  
* `number` é do tipo 'String', para poder abranger localizações com endereços sem número, representados como `s/n`  
* `latitude` e `longitude` são decimais, que pode ser positivos ou negativos  
* `material` aceita apenas os valores `BATERIA`, `PILHA`, `AMBOS`  
* `id`, `registrationDate` e `updateDate` não devem ser informados no corpo das REQUISIÇÕES. Serão informados apenas nas repostas  
  
## Recursos disponíveis  
  
Tomando como exemplo o perfil `dev`, configurado para rodar localmente:  
  
* **GET:** `localhost:8083/location` - lista todos os locais de coleta salvos na base de dados  
  
* **GET:** `localhost:8083/location/{locationId}` - lista um único local de coleta salvo na base de dados, dado um número de "id"  
  
* **POST:**  `localhost:8083/location` - salva um local de coleta na base de dados. No corpo da requisição é necessário um Json de Location completo e válido   
  
* **PUT:** `localhost:8083/location/{locationId}` - atualiza um local de coleta salvo na base de dados, dado um número de "id". No corpo da requisição é necessário um Json de Location completo e válido  
  
* **PATCH:** `localhost:8083/location/{locationId}` - atualiza PARCIALMENTE um local salvo na base de dados, dado um número de "id". No corpo da requisição é necessário um Json contendo uma ou mais propriedades válidas de Location. Caso uma das propriedades seja `address`, é necessário que todos os atributos que pertençam a `address` estejam presentes  
  
* **DEL:** `localhost:8083/location/{locationId}` - deleta um local salvo na base de dados, dad um número de "id"  