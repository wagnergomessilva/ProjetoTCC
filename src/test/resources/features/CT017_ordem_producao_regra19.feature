# language: pt

Funcionalidade: Emissão de Ordem de Produção com a regra 19 com apontamento de produção, consumo dos insumos na primeira etapa do apontamento e entrada de produto acabado na última etapa do apontamento de produção.
	Como um usuário
	Eu quero emitir uma ordem de produção utilizando a regra 19
	E gerar o apontamento de produção da Ordem de produção
	E Validar que o sistema gera Entrada de produo acabado na última etapa do apontamento
	E também que gera o consumo dos insumos pela primeira etapa do apontamento.

Cenário: Emissão de Ordem de Produção com a regra 19 com apontamento de produção, consumo dos insumos na primeira etapa do apontamento e entrada de produto acabado na última etapa do apontamento de produção.
	Como um usuário
	Dado que Cadastro um configurador de Ordem de produção com a Regra 19
	E Cadastro a Ordem de Produção utilizando esta regra, informando o cliente de código 1182
	E Adiciono o item de código 9090 com quantidade 8
	Quando Finalizo esta a Ordem de Produção 
	E Realizo as etpas do apontamento de produção
	Entao o sistema deve Movimentar o Estoque do produto 9090 com uma entrada de 8 quantidades
	E deve ter gerado o consumo dos insumos referente a primeira etapa do apontamento de produção.