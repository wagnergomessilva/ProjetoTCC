# language: pt

Funcionalidade: Emissão de Ordem de Produção utilizando a regra 12 com entrada de produto acabado na últimaetapa do apontamento de produção.
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 12
	E efetuar o apontamento desta ordem de produção
	E efetuar a entrada de produto acabado na última etapa do apontamento
	E garantir que esta entrada de produto acabado gerada pelo apontamento movimentou entrada no estoque

Cenário: Ordem de produção com regra 12 com entrada de produto acabado na última etapa do apontamento de produção
	Dado que cadastro uma configuração de ordem de produção com a regra 12
	E realizo o cadastro uma ordem de produção utilizando esta regra, informando o cliente de código 1182
	E insiro o produto de código 9090 com quantidade 5
	Quando finalizo a ordem de produção 
	E cadastro o apontamento desta ordem de produção
	Entao o sistema deve gerar automaticamente a entrada de produto acabado do produto 9090 com 5 quantidades
	E deve movimentar o estoque do produto 9090 com uma entrada de 5 quantidades