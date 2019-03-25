package pages;

public class MenuPage extends BasePage {

	public void acessaTelaOutrasNotasFiscais() {

		buscaTelasSistemaClicar("iconeNavFerramentaPesquisa");
		escrever("inputCaixaPesquisa", "Outras Notas Fiscais");
		clicarNoResultadoDaPesquisa("preresultadoPesquisa");
	}

	public void acessaTelaConfiguradorDeOrdemProducao() {
		buscaTelasSistemaClicar("iconeNavFerramentaPesquisa");
		escrever("inputCaixaPesquisa", "configurador de O.P. 2015");
		clicarNoResultadoDaPesquisa("preresultadoPesquisa");
	}

	public void acessaTelaOrdemProducaoPCP045() {
		buscaTelasSistemaClicar("iconeNavFerramentaPesquisa");
		escrever("inputCaixaPesquisa", "PCP045");
		clicarNoResultadoDaPesquisa("preresultadoPesquisa");
	}

	public void acessaTelaEntradaProdutoAcabado() {
		buscaTelasSistemaClicar("iconeNavFerramentaPesquisa");
		escrever("inputCaixaPesquisa", "Entrada de produto acabado");
		clicarNoResultadoDaPesquisa("preresultadoPesquisa");
	}

	public void acessaTelaKardex() {
		buscaTelasSistemaClicar("iconeNavFerramentaPesquisa");
		escrever("inputCaixaPesquisa", "Kardex - consulta de estoque");
		clicarNoResultadoDaPesquisa("preresultadoPesquisa");
	}

}
