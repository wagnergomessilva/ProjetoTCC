# language: pt

Funcionalidade: Emissão deentrada de produto acabado sem Ordem de produção. 
	Como um usuário
	Eu quero lançar uma entrada de produto acabado
	E utilizar um produto com engenharia
	E garantir que esta entrada de produto acabado movimentou entrada no estoque

Cenário: Entrada de produto acabado sem Ordem de Produção.
	Dado que acesso a tela de Entrada de Produto acabado
	E realizao o cadastro de uma Entrada de produto acabado
	E insiro o Produto de código 9090 com quantidade 15 quantidades	
	Quando finalizo a Entrada de priduto acabado	
	Entao o sistema deve movimentar o estoque do produto 9090 com 15 quantidades