# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 06
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 06
	E efetuar a entrada de produto acabado 
	E validar a movimentação de entrada do produto acabado

Cenário: Ordem de produção com regra 06
	Dado que cadastro um configurador com a regra 06
	E cadastro uma ordem de produção com esta regra
	E insiro o produto 9090 para produzir 15 quantidades
	Quando finalizo a Ordem de produção
	E realizo a entrada de produto desta Ordem de produção
	E consulto o estoque do produto 9090 
	Entao o sistema deve ter dado entrada de 15 quantidades deste produto