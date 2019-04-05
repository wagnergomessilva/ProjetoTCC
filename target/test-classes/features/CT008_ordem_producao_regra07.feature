# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 07 com produto sem engenharia
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 07 utilizando produto sem engenharia
	E efetuar a entrada de produto acabado 
	E validar a movimentação de entrada do produto acabado

Cenário: Ordem de produção com regra 07
	Dado Que cadastro um configurador com a Regra 07
	E Cadastro uma Ordem de produção com esta regra
	E Insiro o Produto 9093 para produzir 2000 quantidades
	Quando Finalizo a Ordem de Produção
	E Realizo a Entrada de produto desta Ordem de produção
	E Consulto o estoque do Produto 9093 
	Entao o Sistema deve ter dado entrada de 2000 quantidades deste Produto