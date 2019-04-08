package steps.CT007;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.ConfiguradorOrdemProducaoPage;
import pages.EntradaProdutoAcabadoPage;
import pages.KardexPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.OrdemDeProducaoPCP045Page;

public class CT007_EmitirOrdemDeProducaoRegra04Steps {
	
	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	private EntradaProdutoAcabadoPage entProAcabado = new EntradaProdutoAcabadoPage();
	private KardexPage kardex = new KardexPage();

	// variaveis globais

	String casoTeste = "CT007 - Ordem Producao Regra 04";

	String codigoOP;
	String codProduto;
	String codEPA;
	
	@Dado("^que cadastro um configurador com a Regra (\\d+)$")
	public void queCadastroUmConfiguradorComARegra(int numeroRegra) throws Throwable {
		loginPage.acessarTelaSistema();
		loginPage.setUsuario("robohom");
		loginPage.setSenha("robo123");
		loginPage.entrarNoSistema();

		menuPage.acessaTelaConfiguradorDeOrdemProducao();
		
		confiOP.esperaFixa(1000);
		confiOP.alternarFocoJanela(1);
		confiOP.setDescricaoRegra("REGRA " + numeroRegra + "");
		confiOP.setOrigemValue("0");
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaSalvoComSucesso();
		confiOP.clicarAbaCadastro();
		confiOP.clicarAbaConfigIniciais();
		confiOP.clicarCheckboxRegra04();
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

	@E("^Cadastro uma ordem de produção com esta regra$")
	public void cadastroUmaOrdemDeProduçãoComEstaRegra() throws Throwable {
		menuPage.acessaTelaOrdemProducaoPCP045();
		ordemProd.esperaFixa(1000);
		ordemProd.alternarFocoJanela(2);
		ordemProd.setCliente("1186");
		ordemProd.setOrigemOP("0");
		ordemProd.clicarBotaoConfirmar();
		ordemProd.clicarAbaCadastro();
		ordemProd.esperaFixa(700);
		codigoOP = ordemProd.obterCodigoOP("intnumop");
	}

	@E("^Insiro o produto (\\d+) para produzir (\\d+) quantidades$")
	public void insiroOProdutoParaProduzirQuantidades(String codigoProduto, String qtdeProduzir) throws Throwable {
		codProduto = codigoProduto;
		ordemProd.clicarAbaItemProduzir();
		ordemProd.setProduto(codigoProduto);
		ordemProd.setQuantidadeProduzir(qtdeProduzir);
		ordemProd.clicarBotaoAdicionar();
	}

	@Quando("^Finalizo a Ordem de produção$")
	public void finalizoAOrdemDeProdução() throws Throwable {
		ordemProd.clicarBotaoFinalizarOPAbaItem();
		ordemProd.validaAlertaOPFinalizadaSucesso();
	}

	@E("^Realizo a entrada de produto desta Ordem de produção$")
	public void realizoAEntradaDeProdutoDestaOrdemDeProdução() throws Throwable {
		menuPage.acessaTelaEntradaProdutoAcabado();
		entProAcabado.esperaFixa(1000);
		entProAcabado.alternarFocoJanela(3);
		entProAcabado.setTurno("1");
		entProAcabado.clicarBotaoConfirmarEPA();
		entProAcabado.validaAlertaSalvoComSucesso();

		codEPA = entProAcabado.obterCodigoEPA(); // Obtem o código da EPA para posteriormente validar na tela de kardex

		entProAcabado.setCodigoOrdemProducao(codigoOP);
		entProAcabado.validaProduto(codProduto, "epiproduto2");
		entProAcabado.clicarBotaAdicionarEPA();		
		entProAcabado.esperaFixa(2000);
	}

	@E("^Consulto o estoque do produto (\\d+)$")
	public void consultoOEstoqueDoProduto(String produto) throws Throwable {
		menuPage.acessaTelaKardex();
		kardex.esperaFixa(700);
		kardex.alternarFocoJanela(4);
		kardex.setProdutoConsulta(produto);
		kardex.clicarBotaoPesquisarKardex();
	}

	@Entao("^o Sistema deve ter dado entrada de (\\d+) quantidades deste produto$")
	public void oSistemaDeveTerDadoEntradaDeQuantidadesDesteProduto(String qtde) throws Throwable {
		kardex.esperaFixa(1000);
		kardex.validaNumeroEPA(codEPA);
		kardex.validaQtdeEPA(codEPA, qtde);
	}
	
	@After(order = 1)
	public void screenshot() throws IOException, HeadlessException, AWTException {
		ordemProd.screenshotTela(casoTeste);
	}

	@After(order = 0)
	public void finaliza() throws IOException {
		ordemProd.finalizarNavegador();
	}
}
