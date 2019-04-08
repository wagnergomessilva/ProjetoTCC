# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 17 com apontamento e entrada de produto acabado utilizando produto sem engenharia 
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 17
	E utilizar produto que não contém engenharia
	E gerar o apontamento de produção da Ordem de produção
	E gerar a entrada de produto acabado informando utilizando esta ordem de produção.

Cenário: Ordem de produção com regra 17 com apontamento e entrada de produto acabado utilizando produto sem engenharia
	Dado que cadastro um configurador de Ordem de produção com a Regra 17
	E Cadastro a Ordem de produção utilizando esta regra, informando o cliente de código 1187
	E adiciono o item de código 3535 com quantidade 1598
	Quando Finalizo esta a ordem de Produção 
	E realizo as etpas do apontamento de produção
	E cadastro a entrada de produto acabado utilizando esta ordem de produção
	Entao o sistema deve Movimentar o estoque do produto 3535 com uma entrada de 1598 quantidades