package steps.CT015;

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

public class CT015_EmitirOrdemDeProducaoRegra16Steps {

	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	private EntradaProdutoAcabadoPage entProAcabado = new EntradaProdutoAcabadoPage();
	private KardexPage kardex = new KardexPage();

	// variaveis globais
	String casoTeste = "CT015 - Ordem Producao Regra 16 com 2 produtos";

	String codigoOP = "";
	String codProduto = "";
	String codProduto2 = "";
	String qtdeProd = "";
	String qtdeProd2 = "";
	String codApontamento = "";
	String codEPA = "";
	String dataOP = "";
	String dataApontamento = "";
	
	@Dado("^que efetuo o cadastro um configurador de Ordem de produção com a Regra (\\d+)$")
	public void queEfetuoOCadastroUmConfiguradorDeOrdemDeProduçãoComARegra(int numeroRegra) throws Throwable {
		loginPage.acessarTelaSistema();
		loginPage.setUsuario("robohom");
		loginPage.setSenha("robo123");
		loginPage.entrarNoSistema();

		menuPage.acessaTelaConfiguradorDeOrdemProducao();

		confiOP.alternarFocoJanela(1);
		confiOP.esperaFixa(700);
		confiOP.setDescricaoRegra("REGRA " + numeroRegra + "");
		confiOP.setOrigemValue("0");
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaSalvoComSucesso();
		confiOP.clicarAbaCadastro();
		confiOP.clicarAbaConfigIniciais();
		confiOP.clicarCheckboxRegra16();
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaPreencherCampos();
		confiOP.clicarAbaConfigIniciais();
		confiOP.clicarAbaCalculo();
		confiOP.setQuantidadeProduzirConfig("0");
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaPreencherCampos();
		confiOP.clicarAbaImpressao();
		confiOP.setLayoutImpressaoOP("1");
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaSalvoComSucesso();
	}

	@E("^Cadastro uma Ordem de produção utilizando esta regra, informando o cliente de código (\\d+)$")
	public void cadastroUmaOrdemDeProduçãoUtilizandoEstaRegraInformandoOClienteDeCódigo(String codigoCliente) throws Throwable {
		menuPage.acessaTelaOrdemProducaoPCP045();
		ordemProd.esperaExplicita("btnSave");
		ordemProd.alternarFocoJanela(2);
		ordemProd.setCliente(codigoCliente);
		ordemProd.setOrigemOP("0");
		ordemProd.clicarBotaoConfirmar();
		ordemProd.clicarAbaCadastro();
		ordemProd.esperaFixa(700);
		codigoOP = ordemProd.obterCodigoOP("intnumop");
	}

	@E("^Insiro o item de código (\\d+) com quantidade (\\d+)$")
	public void insiroOItemDeCódigoComQuantidade(String codigoProduto, String qtdeProduzir) throws Throwable {
		if (codProduto == "") {
			codProduto = codigoProduto;
			qtdeProd = qtdeProduzir;
		} else {
			codProduto2 = codigoProduto;
			qtdeProd2 = qtdeProduzir;
		}
		ordemProd.clicarAbaItemProduzir();
		ordemProd.setProduto(codigoProduto);
		ordemProd.setQuantidadeProduzir(qtdeProduzir);
		ordemProd.clicarBotaoAdicionar();
		ordemProd.esperaFixa(300);
		ordemProd.validaQtdeProduzir(qtdeProduzir);
	}

	@Quando("^Finalizo esta a ordem de produção$")
	public void finalizoEstaAOrdemDeProdução() throws Throwable {
		ordemProd.clicarBotaoFinalizarOPAbaItem();
		ordemProd.validaAlertaOPFinalizadaSucesso();
	}

	@E("^cadastro a entrada de produto acabado desta Ordem de Produção$")
	public void cadastroAEntradaDeProdutoAcabadoDestaOrdemDeProdução() throws Throwable {
		menuPage.acessaTelaEntradaProdutoAcabado();
		entProAcabado.esperaFixa(700);
		entProAcabado.alternarFocoJanela(3);
		entProAcabado.setTurno("1");
		entProAcabado.clicarBotaoConfirmarEPA();
		entProAcabado.validaAlertaSalvoComSucesso();

		codEPA = entProAcabado.obterCodigoEPA(); // Obtem o código da EPA para posteriormente validar na tela de kardex

		entProAcabado.setCodigoOrdemProducao(codigoOP);
		entProAcabado.setProdutoEPA(codProduto);
		entProAcabado.clicarBotaAdicionarEPA();
		entProAcabado.esperaFixa(700);
		entProAcabado.clicarBotaoConfirmaInsumo();
		entProAcabado.esperaFixa(2000);

		// inserindo o segundo produto na entrada de produto acabado
		entProAcabado.setCodigoOrdemProducao(codigoOP);
		entProAcabado.setProdutoEPA(codProduto2);
		entProAcabado.clicarBotaAdicionarEPA();
		entProAcabado.esperaFixa(700);
		entProAcabado.clicarBotaoConfirmaInsumo();
		entProAcabado.esperaFixa(2000);
	}

	@Entao("^o sistema deve Movimentar o estoque do produto (\\d+) com uma entrada de (\\d+) quantidades$")
	public void oSistemaDeveMovimentarOEstoqueDoProdutoComUmaEntradaDeQuantidades(String produto, String qtde) throws Throwable {
		menuPage.acessaTelaKardex();
		kardex.esperaFixa(700);
		kardex.alternarFocoJanela(4);
		kardex.setProdutoConsulta(produto);
		kardex.clicarBotaoPesquisarKardex();
		kardex.esperaFixa(1000);
		kardex.validaNumeroEPA(codEPA);
		kardex.validaQtdeEPA(codEPA, qtde);
		kardex.clicarBotaoNovo();
	}

	@E("^Movimentar o estoque do produto (\\d+) com uma entrada de (\\d+) quantidades$")
	public void movimentarOEstoqueDoProdutoComUmaEntradaDeQuantidades(String produto, String qtde) throws Throwable {
		kardex.setProdutoConsulta(produto);
		kardex.clicarBotaoPesquisarKardex();
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
