# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 18 utilizando 2 produtos diferentes na mesma Ordem. 
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 18
	E utilizar 2 produtos diferentes na mesma Ordem
	E efetuar o apontamento de produção
	E efetuar a entrada de produto acabado
	E garantir que esta entrada de produto acabado gerada pelo apontamento movimentou entrada no estoque dos 2 produtos

Cenário: Ordem de produção com regra 18 utilizando 2 produtos na mesma Ordem.
	Dado que cadastro um configurador de Ordem de produção com a regra 18
	E realizo o cadastro uma Ordem de produção utilizando esta regra, informando o cliente de código 1182
	E insiro o Produto de código 9090 com quantidade 15
	E insiro o Produto de código 8080 com quantidade 10
	Quando finalizo a Ordem de Produção 
	E cadastro o Apontamento desta Ordem de Produção  
	E cadastro a Entrada de produto acabado desta Ordem de Produção
	Entao o sistema deve movimentar o estoque do produto 9090 com uma entrada de 15 quantidades
	E movimentar o estoque do produto 8080 com uma entrada de 10 quantidades