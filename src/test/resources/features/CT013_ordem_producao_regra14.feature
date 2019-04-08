# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 14, consumo dos insumos na primeira etapa do apontamento e entrada de produto acabado na última etapa do apontamento de produção
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 14 utilizando produto com engenharia
	E efetuar as etapas de apontamento de produção desta Ordem de produção 
	E validar a movimentação de entrada do produto acabado
	E o consumo dos insumos gerado na primeira etapa do apontamento de produção

Cenário: Ordem de produção com regra 14 com entrada de produto acabado na última etapa do apontamento e consumo dos insumos na primeira etapa do apontamento 
	Dado que Cadastro um configurador com a Regra 14
	E cadastro uma ordem de produção com esta regra informando o cliente 1182
	E adiciono o produto código 8080 para produzir 8 quantidades
	Quando Finalizo a ordem de produção
	E cadastro a primeira etapa do apontamento de produção
	E cadastro a última etapa do apontamento de produção
	Entao o sistema deve gerar automaticamente a entrada de produto acabado do produto 8080 com 8 quantidades
	E o Sistema deve efetuar entrada de 8 quantidades do produto 8080
	E deve ter gerado o consumo dos insumos pela primeira etapa do apontamento de produção