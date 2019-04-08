# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 10 com apontamento e entrada de produto acabado
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 10 utilizando produto com engenharia
	E efetuar as etapas de apontamento de produção desta Ordem de produção 
	E cadastrar a entrada do produto acabado 
	E validar a movimentação de entrada do produto acabado

Cenário: Ordem de produção com regra 10 com apontamento e entrada de produto acabado
	Dado que efetuo o cadastro um configurador com a Regra 10
	E realizo o cadastro de uma ordem de produção com esta regra
	E adiciono o produto de código 9090 para produzir 5 quantidades
	Quando finalizo esta ordem de produção
	E efetuo as etapas do apontamento de produção
	E efetuo a entrada de produto acabado desta ordem de produção 
	Entao o Sistema deve movimentar entrada de 5 quantidades do produto 9090