# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 16 utilizando 2 produtos diferentes na mesma Ordem. 
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 16
	E utilizar 2 produtos diferentes na mesma Ordem	
	E gerar a entrada de produto acabado informando os dois produtos da Ordem de produção.

Cenário: Ordem de produção com regra 16 utilizando 2 produtos na mesma Ordem com entrada de produto acabado.
	Dado que efetuo o cadastro um configurador de Ordem de produção com a Regra 16
	E Cadastro uma Ordem de produção utilizando esta regra, informando o cliente de código 1187
	E Insiro o item de código 9090 com quantidade 9
	E Insiro o item de código 8080 com quantidade 7
	Quando Finalizo esta a ordem de produção 
	E cadastro a entrada de produto acabado desta Ordem de Produção
	Entao o sistema deve Movimentar o estoque do produto 9090 com uma entrada de 9 quantidades
	E Movimentar o estoque do produto 8080 com uma entrada de 7 quantidades