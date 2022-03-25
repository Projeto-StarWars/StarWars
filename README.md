# Star Wars Resistence Social Network

Descrição do problema

O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e eliminar os rebeldes. 

Você, como um soldado da resistência, foi designado para desenvolver um sistema para compartilhar recursos entre os rebeldes.

Requisitos funcionais

Você irá desenvolver uma API REST (sim, nós levamos a arquitetura da aplicação a sério mesmo no meio de uma guerra), ao qual irá armazenar informação sobre os rebeldes, bem como os recursos que eles possuem.
Adicionar rebeldes

Um rebelde deve ter um nome, idade, gênero, localização (latitude, longitude e nome, na galáxia, da base ao qual faz parte).
Um rebelde também possui um inventário que deverá ser passado na requisição com os recursos em sua posse.

Atualizar localização do rebelde

Um rebelde deve possuir a capacidade de reportar sua última localização, armazenando a nova latitude/longitude/nome (não é necessário rastrear as localizações, apenas sobrescrever a última é o suficiente).

Reportar o rebelde como um traidor

Eventualmente algum rebelde irá trair a resistência e se aliar ao império. Quando isso acontecer, nós precisamos informar que o rebelde é um traidor.

Um traidor não pode negociar os recursos com os demais rebeldes, não pode manipular seu inventário, nem ser exibido em relatórios.

Um rebelde é marcado como traidor quando, ao menos, três outros rebeldes reportarem a traição.

Uma vez marcado como traidor, os itens do inventário se tornam inacessíveis (eles não podem ser negociados com os demais).

Rebeldes não podem Adicionar/Remover itens do seu inventário

Seus pertences devem ser declarados quando eles são registrados no sistema. Após isso eles só poderão mudar seu inventário através de negociação com os outros rebeldes.

Negociar itens

Os rebeldes poderão negociar itens entre eles.

Para isso, eles devem respeitar a tabela de preços abaixo, onde o valor do item é descrito em termo de pontos.

Ambos os lados deverão oferecer a mesma quantidade de pontos. Por exemplo, 1 arma e 1 água (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3).

A negociação em si não será armazenada, mas os itens deverão ser transferidos de um rebelde a outro.

ITEM
PONTOS
1 Arma
4
1 Munição
3
1 Água
2
1 Comida
1


Relatórios

A API deve oferecer os seguintes relatórios:
Porcentagem de traidores.
Porcentagem de rebeldes.
Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde).
Pontos perdidos devido a traidores.

## Documentação da API

#### Retorna todos os rebeldes

```http
  GET /v1/rebeldes

```


#### Cria um rebelde

```http
  POST /v1/rebeldes

  exemplo

  {
    "nome": "Lapalui",
    "idade": 12,
    "genero": 1,
    "localization": {
        "latitude": 200,
        "longitude": 300,
        "nomeDaBase": "Mizeravon"
    },
    "inventoryList": [
        {"item": 3},
        {"item": 0},
        {"item": 2},
        {"item": 1},
        {"item": 1},
        {"item": 1}
        
    ]

}
```
#### Alterar Localização de um rebelde

```http
  PATCH /v1/rebeldes/{id}

  exemplo

{
    "latitude": 2000,
    "longitude": 3000,
    "nomeDaBase": "Mizeravonnn"
}
```


#### Reportar Traidor

```http
  GET localhost:8080/v1/rebeldes/reportar/{id}
  
  Aviso: Enviar 3 vezes para colocar status de traidor
  
```

#### Retorna o relatorio completo

```http
  GET /v1/rebeldes/relatorios

  Aviso: Favor adicionar pelo menos 1 item de cada tanto em um rebelde como em um traidor, pois no cauculo da média, a divisão por 0 não esta tratada.
```

