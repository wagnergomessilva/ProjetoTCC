# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 04 com produto sem engenharia
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 04 utilizando produto sem engenharia
	E efetuar a entrada de produto acabado 
	E validar a movimentação de entrada do produto acabado

Cenário: Ordem de produção com regra 04
	Dado que cadastro um configurador com a Regra 04
	E Cadastro uma ordem de produção com esta regra
	E Insiro o produto 9093 para produzir 5000 quantidades
	Quando Finalizo a Ordem de produção
	E Realizo a entrada de produto desta Ordem de produção
	E Consulto o estoque do produto 9093 
	Entao o Sistema deve ter dado entrada de 5000 quantidades deste produto