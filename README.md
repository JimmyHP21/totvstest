
## Etiquetas

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)


# Teste TOTVS

Criar um Frontend para inserção de dados e um backend com funcionalidades descritas abaixo e teste unitarios


## Stack utilizada

**Front-end:** Angular

**Back-end:** Java, Spring Boot


## Funcionalidades

- Validar se o nome do cliente é maior que 10 caracteres e não permitir dados redundantes.
- Validar se um telefone cadastro a um cliente X não está vinculado a outro. 
- Validar se o CPF é valido.
- Validar se o telefone é nulo
- Validar se os caracteres do telefone são diferentes (ex: Não deve ser cadastrado um telefone 99999-9999)
- Validar se o CPF é único e válido
- Testes Unitarios


## Rodando localmente front

Clone o projeto

```bash
  git clone https://link-para-o-projeto
```

Entre no diretório do projeto

```bash
  cd my-project
```

Instale as dependências

```bash
  npm install
```

Inicie o servidor

```bash
  npm run start
```


## Autores

- [@Renan](https://www.github.com/JimmyHP21)


## Documentação da API

#### Swagger

```http
  http://localhost:8080/swagger-ui/index.html#/
```

#### Retorna todos os itens

```http
  GET /v1/api/client/list
```

#### Retorna um item

```http
  GET /v1/api/client/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` |   ID do client |


