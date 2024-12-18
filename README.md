# Projeto API - Engenharia de Software: Análise e Projeto de Software

## Descrição
Este projeto foi desenvolvido como requisito parcial para aprovação na disciplina **EGS19502 - Engenharia de Software: Análise e Projeto de Software**. O objetivo foi criar uma API RESTful utilizando boas práticas de arquitetura de software.

### Tecnologias Utilizadas
- **Linguagem**: Kotlin
- **Framework**: Spring Boot
- **Gerenciador de Dependências**: Gradle
- **Padrão Arquitetural**: MVC
- **Banco de Dados**: MySQL (Dockerizado) e H2 (para testes)
- **Ferramenta de Migrations**: Flyway
- **Cliente HTTP**: Retrofit2
- **Gerenciamento de Containers**: Docker e Docker Compose

### Funcionalidades
- Consulta de dados na API pública do [The Movie Database (TMDB)](https://www.themoviedb.org/).
- Tratamento e armazenamento das informações consultadas em um banco de dados local (MySQL).
- Testes unitários com cobertura completa, utilizando H2 como banco de dados em memória para maior eficiência durante os testes.

## Requisitos para Execução
- Docker e Docker Compose instalados.
- JDK 17 ou superior (opcional para desenvolvimento local sem Docker).

## Passo a Passo para Iniciar o Projeto com Docker Compose

1. **Clone o Repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_REPOSITORIO>
   ```

2. **Configure as Variáveis de Ambiente**:
   Atualize o arquivo `application.yml` na raiz do projeto e configure as seguintes variáveis:
   ```env
   MYSQL_ROOT_PASSWORD=senha_root
   MYSQL_DATABASE=nome_do_banco
   MYSQL_USER=usuario
   MYSQL_PASSWORD=senha_usuario

   TMDB_API_KEY=sua_chave_de_api
   ```
   Substitua `sua_chave_de_api` pela sua chave da API do TMDB. Caso não possua, registre-se [aqui](https://www.themoviedb.org/signup) para obter uma.

3. **Inicie os Containers com Docker Compose**:
   ```bash
   docker-compose up --build
   ```

4. **Acesse a API**:
   A API estará disponível em: `http://localhost:8080`

5. **Documentação da API**:
   Acesse a documentação (se configurada) em: `http://localhost:8080/swagger-ui.html` ou `http://localhost:8080/api-docs`.

## Estrutura do Projeto
- **Controller**: Camada responsável por gerenciar as requisições HTTP e enviar as respostas adequadas.
- **Service**: Contém a lógica de negócios da aplicação.
- **Repository**: Responsável pela interação com o banco de dados.
- **Model**: Classes que representam as entidades do sistema.
- **Testes**: Testes unitários que garantem a qualidade do código e cobertura completa.

## Banco de Dados
### MySQL (Produção)
- Configurado no Docker Compose.
- Migrations gerenciadas pelo Flyway.

### H2 (Testes)
- Banco em memória utilizado durante os testes para maior eficiência.

## Contribuições
Contribuições são bem-vindas! Siga os passos abaixo para contribuir:
1. Faça um fork do repositório.
2. Crie uma branch para sua feature ou correção: `git checkout -b minha-feature`.
3. Commit suas alterações: `git commit -m 'Minha nova feature'`.
4. Envie sua branch: `git push origin minha-feature`.
5. Abra um Pull Request.

## Autor
Desenvolvido por [Seu Nome].

## Licença
Este projeto está licenciado sob a [Licença MIT](LICENSE).
