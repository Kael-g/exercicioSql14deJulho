# Aplicativo para armazenar, consultar e alterar informações sobre rebeldes em um banco de dados local
### Funcionalidades:
### - Adicionar Rebelde
Solicita o nome, idade, gênero e localização do novo rebelde.

Quando adicionado ao banco de dados, além das informações inseridas, o rebelde recebe um ID único, seu status é ATIVO e o seu número de denúncias é 0.

### - Atualizar localização de um rebelde
Solicita o ID único do rebelde e a sua nova localização.

No banco de dados, atualiza a coluna que indica a localização do rebelde indicado pelo ID.

### - Reportar rebelde como traidor
Solicida o ID do delator e o ID do suspeito.

- Não é possível que o mesmo rebelde faça mais de uma denúncia a outro rebelde.
- Traidores não podem fazer denúncias.

Caso cumpra as condições acima:
- a tabela de denúncias do banco de dados é atualizada com o ID do suspeito e o ID do delator.
- o número de denúncias do suspeito é atualizado (recebe +1)
- atualiza o status de todos os rebeldes que possuem 3 denúncias ou mais para "inativo", marcando-o como um traidor

### - Ver registros de rebeldes
O usuário escolhe se quer listar todos os rebeldes ou ver informações de um rebelde através de seu ID

### - Ver relatórios
Mostra a porcentagem de rebeldes e traidores registrados

### - Loja
Solicita o ID do comprador.
- Traidores não podem fazer compras

Caso o ID informado seja de um rebelde ativo, mostra a loja com os produtos, seus IDs e valor, e solicita que o usuário informe o ID do produto e a quantidade

Caso o produto ainda não exista no inventário do rebelde, é adicionado, junto com sua quantidade

Caso o produto já exista no inventário, a quantidade escolhida é acrescentada ao inventário

### - Sair
Encerra conexão com o banco de dados e finaliza o programa
