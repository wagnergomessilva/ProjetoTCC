package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.ConfiguradorOrdemProducaoPage;
import pages.KardexPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.OrdemDeProducaoPCP045Page;

public class CT002_EmitirOrdemProducaoRegra02Steps {

	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	KardexPage kardex = new KardexPage();

	// variaveis globais

	String casoTeste = "CT002 - Ordem Producao Regra 02 com apontamento"; // varivael que será utilizada para nomear o Screenshot

	String codigoOP;
	String codProduto;
	String codApontamento;

	@Dado("^que cadastro um configurador de ordem de produção com a regra (\\d+)$")
	public void queCadastroUmConfiguradorDeOrdemDeProduçãoComARegra(int numeroRegra) throws Throwable {
		loginPage.acessarTelaSistema();
		loginPage.setUsuario("robohom");
		loginPage.setSenha("robo123");
		loginPage.entrarNoSistema();

		menuPage.acessaTelaConfiguradorDeOrdemProducao();

		confiOP.alternarFocoJanela(1);
		confiOP.setDescricaoRegra("REGRA "+numeroRegra+"");
		confiOP.setOrigemValue("0");
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaSalvoComSucesso();
		confiOP.clicarAbaCadastro();
		confiOP.clicarAbaConfigIniciais();
		confiOP.clicarCheckboxRegra06();
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaPreencherCampos();
		confiOP.clicarAbaConfigIniciais();
		confiOP.clicarAbaCalculo();
		confiOP.setQuantidadeProduzirConfig("0");
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaPreencherCampos();
		confiOP.clicarAbaImpressao();
		confiOP.setLayoutImpressaoOP("0");
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaSalvoComSucesso();
	}

	@E("^cadastro uma ordem de produção utilizando esta regra, informando o cliente de código (\\d+)$")
	public void cadastroUmaOrdemDeProduçãoUtilizandoEstaRegraInformandoOClienteDeCódigo(int arg1) throws Throwable {

	}

	@E("^insiro o produto (\\d+) com (\\d+) quantidades$")
	public void insiroOProdutoComQuantidades(int arg1, int arg2) throws Throwable {

	}

	@Quando("^finalizo esta Ordem de produção$")
	public void finalizoEstaOrdemDeProdução() throws Throwable {

	}

	@E("^realizo o apontamento desta Ordem de Produção$")
	public void realizoOApontamentoDestaOrdemDeProdução() throws Throwable {

	}

	@E("^consulto o apontamento gerado$")
	public void consultoOApontamentoGerado() throws Throwable {

	}

	@Entao("^o sistema deve deve exibir o apontamento gerado pela ordem de produção$")
	public void oSistemaDeveDeveExibirOApontamentoGeradoPelaOrdemDeProdução() throws Throwable {

	}

}
