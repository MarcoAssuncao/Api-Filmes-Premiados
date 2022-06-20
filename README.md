# Api-Filmes-Premiados
Api Rest com Spring Batch

API Rest com desenvolvimento com Spring Data e Spring Batch. A api consiste em duas funcionalidades o endpoint http://localhost:8080/filmes/load que deve ser executado primeiro 
para processar os dados do arquivo.csv que será gravado na base de dados Tabela FILME no Banco H2. após execução com sucesso deve acessar ao endpoint http://localhost:8080/filmes/filmes-premiados
que retorna a estrutura dos filmes premiados da categoria. Na pasta TEST contém os testes de integração deste projeto
