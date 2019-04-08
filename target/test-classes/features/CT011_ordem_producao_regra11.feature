# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 11, consumo dos insumos na primeira etapa do apontamento e entrada de produto acabado
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 11 utilizando produto com engenharia
	E efetuar as etapas de apontamento de produção desta Ordem de produção 
	E cadastrar a entrada do produto acabado 
	E validar a movimentação de entrada do produto acabado

Cenário: Ordem de produção com regra 11 com entrada de produto acabado e consumo dos insumos na primeira etapa do apontamento 
	Dado que Efetuo o cadastro um configurador com a Regra 11
	E realizo o cadastro de uma ordem de produção com esta regra informando o cliente 1182
	E adiciono o produto com código 8080 para produzir 13 quantidades
	Quando Finalizo esta ordem de produção
	E Efetuo as etapas do apontamento de produção
	E Efetuo a entrada de produto acabado desta ordem de produção 
	Entao o Sistema deve movimentar entrada de 13 quantidades do produto 8080
	E o sistema deve ter gerado o consumo dos insumos pela primeira etapa do apontamento de produção