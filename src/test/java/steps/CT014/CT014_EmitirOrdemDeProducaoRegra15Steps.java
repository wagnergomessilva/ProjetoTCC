package steps.CT014;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.ConfiguradorOrdemProducaoPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.OrdemDeProducaoPCP045Page;

public class CT014_EmitirOrdemDeProducaoRegra15Steps {

	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();

	// variaveis globais
	String casoTeste = "CT014 - Ordem Producao Regra 15 com 2 produtos";

	String codigoOP = "";
	String codProduto = "";
	String codProduto2 = "";
	String qtdeProd = "";
	String qtdeProd2 = "";
	String codApontamento = "";
	String codEPA = "";
	String dataOP = "";
	String dataApontamento = "";

	@Dado("^que efetuo o cadastro um configurador de Ordem de produção com a regra (\\d+)$")
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
		confiOP.clicarCheckboxRegra15();
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

	@E("^cadastro uma Ordem de produção utilizando esta regra, informando o cliente de código (\\d+)$")
	public void cadastroUmaOrdemDeProduçãoUtilizandoEstaRegraInformandoOClienteDeCódigo(String codigoCliente) throws Throwable {
		menuPage.acessaTelaOrdemProducaoPCP045();
		ordemProd.esperaFixa(2000);
		ordemProd.alternarFocoJanela(2);
		ordemProd.setCliente(codigoCliente);
		ordemProd.setOrigemOP("0");
		ordemProd.clicarBotaoConfirmar();
		ordemProd.clicarAbaCadastro();
		ordemProd.esperaFixa(700);
		codigoOP = ordemProd.obterCodigoOP("intnumop");
	}

	@E("^insiro o item de código (\\d+) com quantidade (\\d+)$")
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

	@Quando("^finalizo esta a ordem de produção$")
	public void finalizoEstaAOrdemDeProdução() throws Throwable {
		ordemProd.clicarBotaoFinalizarOPAbaItem();
		ordemProd.validaAlertaOPFinalizadaSucesso();
	}

	@Entao("^ao acessar a tela de consulta de ordem de produção o sistema deve ter salvo a ordem de produção$")
	public void aoAcessarATelaDeConsultaDeOrdemDeProduçãoOSistemaDeveTerSalvoAOrdemDeProdução() throws Throwable {
		ordemProd.esperaFixa(700);
		ordemProd.clicarBotaoPesquisarOP();
		ordemProd.esperaFixa(1000);
		ordemProd.setCodigo4OP(codigoOP);
		ordemProd.setProdutoPesquisa(codProduto);
		ordemProd.clicarBotaoPesquisarOP();
		ordemProd.esperaFixa(300);
		ordemProd.clicarBotaoEditarOP();
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
