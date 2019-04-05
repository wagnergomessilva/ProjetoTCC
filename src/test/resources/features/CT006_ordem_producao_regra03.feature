# language: pt

Funcionalidade: Emissão de Ordem de Produção utilizando a regra 03 com apontamento de produção
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 03
	E efetuar o apontamento desta ordem de produção
	E garantir que o sistema está salvando corretamente o apontamento.

Cenário: Ordem de produção com regra 03 com apontamento de produção
	Dado que cadastro um Configurador de ordem de produção com a regra 03
	E cadastro uma Ordem de Produção utilizando esta regra, informando o cliente de código 1182
	E insiro o Produto 9090 com 3 quantidades
	Quando finalizo esta Ordem de Produção
	E realizo o apontamento desta Ordem
	E realizao a consulta do apontamento gerado
	Entao o Sistema deve deve exibir o apontamento gerado pela ordem de produção