# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 15 utilizando 2 produtos diferentes na mesma Ordem. 
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 15
	E utilizar 2 produtos diferentes na mesma Ordem	
	E garantir o sistema está salvando corretamente a ordem de produção

Cenário: Ordem de produção com regra 15 utilizando 2 produtos na mesma Ordem.
	Dado que efetuo o cadastro um configurador de Ordem de produção com a regra 15
	E cadastro uma Ordem de produção utilizando esta regra, informando o cliente de código 1182
	E insiro o item de código 3535 com quantidade 15
	E insiro o item de código 4045 com quantidade 10
	Quando finalizo esta a ordem de produção 
	Entao ao acessar a tela de consulta de ordem de produção o sistema deve ter salvo a ordem de produção