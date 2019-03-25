# language: pt

Funcionalidade: Faturamento de nota fiscal normal
	Como um usuário
	Eu quero emitir uma nota fiscal normal pela tela de Outras notas fiscais
	E validar que a nota fiscal é autorizada no SEFAZ com sucesso
	
Cenário: Nota fiscal normal
	Dado que desejo emitir a nota fiscal
	Quando informo todos os dados da aba Cadastro
	E adiciono o produto de código 8080
	E informo as parcelas
	E finalizo a nota fiscal
	Então quando realizo a consulta desta nota, ela está autorizada