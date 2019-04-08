# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 13, apontamento de produção e entrada de produto acabado
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 13 utilizando produto com engenharia
	E efetuar as etapas de apontamento de produção desta Ordem de produção 
	E cadastrar a entrada do produto acabado 
	E validar a movimentação de entrada do produto acabado

Cenário: Ordem de produção com regra 13 com apontamento de produção e entrada de produto acabado 
	Dado que realizo o cadastro um configurador com a Regra 13
	E efetuo o cadastro de uma ordem de produção com esta regra informando o cliente 1182
	E insiro o produto com código 8080 para produzir 6 quantidades
	Quando finalizo a ordem de produção
	E realizo as etapas do apontamento de produção
	E realizo a entrada de produto acabado desta ordem de produção 
	Entao o Sistema deve movimentar entrada de 6 quantidades do produto 8080