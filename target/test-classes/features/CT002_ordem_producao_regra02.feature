# language: pt

Funcionalidade: Emissão de Ordem de Produção utilizando a regra 02 com apontamento de produção
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 02
	E efetuar o apontamento desta ordem de produção
	E garantir que o sistema está salvando corretamente o apontamento.

Cenário: Ordem de produção com regra 02 com apontamento de produção
	Dado que cadastro um configurador de ordem de produção com a regra 02
	E cadastro uma ordem de produção utilizando esta regra, informando o cliente de código 1182
	E insiro o produto 9090 com 15 quantidades
	Quando finalizo esta Ordem de produção
	E realizo o apontamento desta Ordem de Produção
	E consulto o apontamento gerado
	Entao o sistema deve deve exibir o apontamento gerado pela ordem de produção