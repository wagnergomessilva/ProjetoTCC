# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 09
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 09
	E efetuar a entrada de produto acabado 
	E validar a movimentação de entrada do produto acabado

Cenário: Ordem de produção com regra 09
	Dado que Cadastro um configurador com a regra 09
	E cadastro uma Ordem de produção com esta regra
	E Insiro o Produto 8080 para Produzir 4 quantidades
	Quando finalizo a ordem de Produção
	E efetuo a entrada de produto desta Ordem de produção
	E valido o estoque do produto 8080 
	Entao o Sistema deve ter movimentado entrada de 4 quantidades deste produto