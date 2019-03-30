package steps.CT002;

import static core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import core.DriverFactory;
import core.Propriedades;
import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.ApontamentoDeProducaoPage;
import pages.ConfiguradorOrdemProducaoPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.OrdemDeProducaoPCP045Page;

public class CT002_EmitirOrdemProducaoRegra02Steps {

	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	private ApontamentoDeProducaoPage apontamentoProd = new ApontamentoDeProducaoPage();

	// variaveis globais
	String casoTeste = "CT002 - Ordem Producao Regra 02 com apontamento"; // varivael que será utilizada para nomear o Screenshot

	String codigoOP;
	String codProduto;
	String codApontamento;
	String dataOP;
	String qtdeProd;
	String dataApontamento;

	@Dado("^que cadastro um configurador de ordem de produção com a regra (\\d+)$")
	public void queCadastroUmConfiguradorDeOrdemDeProduçãoComARegra(int numeroRegra) throws Throwable {
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
		confiOP.clicarCheckboxRegra02();
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
	public void cadastroUmaOrdemDeProduçãoUtilizandoEstaRegraInformandoOClienteDeCódigo(String coigoCliente)
			throws Throwable {
		menuPage.acessaTelaOrdemProducaoPCP045();
		ordemProd.esperaFixa(1000);
		ordemProd.alternarFocoJanela(2);
		ordemProd.setCliente(coigoCliente);
		ordemProd.setOrigemOP("0");
		ordemProd.clicarBotaoConfirmar();
		ordemProd.clicarAbaCadastro();
		ordemProd.esperaFixa(700);
		codigoOP = ordemProd.obterCodigoOP("intnumop");
		dataOP = ordemProd.obterDataEmissaoOP("datemisop");
	}

	@E("^insiro o produto (\\d+) com (\\d+) quantidades$")
	public void insiroOProdutoComQuantidades(String codigoProduto, String qtdeProduzir) throws Throwable {
		codProduto = codigoProduto;
		qtdeProd = qtdeProduzir; // guarda a quantidade a produzir na variavel global "qtdeProd" para
									// posteriormente validar o saldo da OP na tela de apontamento
		ordemProd.clicarAbaItemProduzir();
		ordemProd.setProduto(codigoProduto);
		ordemProd.setQuantidadeProduzir(qtdeProduzir);
		ordemProd.clicarBotaoAdicionar();
		ordemProd.validaQtdeProduzir(qtdeProduzir);

	}

	@Quando("^finalizo esta Ordem de produção$")
	public void finalizoEstaOrdemDeProdução() throws Throwable {
		ordemProd.clicarBotaoFinalizarOPAbaItem();
		ordemProd.validaAlertaOPFinalizadaSucesso();
	}

	@E("^realizo o apontamento desta Ordem de Produção$")
	public void realizoOApontamentoDestaOrdemDeProdução() throws Throwable {
		menuPage.acessaTelaApontamentoProducao();
		apontamentoProd.esperaFixa(1000);
		apontamentoProd.alternarFocoJanela(3);
		apontamentoProd.setCodigoOrdemProducao(codigoOP);
		apontamentoProd.validaProduto(codProduto, "objprodutap2");
		apontamentoProd.setEtapa("8");
		apontamentoProd.clicarBotaoConfirmarApontamento();
		codApontamento = apontamentoProd.obterCodigoApontamento();
		dataApontamento = apontamentoProd.obterDataApontamento();
		apontamentoProd.validaDataProducao(dataOP, "datdataprodinf");
		apontamentoProd.validaQtdeSaldoOP(qtdeProd);
		apontamentoProd.clicarBotaoAdicionarItemApontamento();
		apontamentoProd.validaDataApontamento(dataApontamento);
		apontamentoProd.validaQtdeProduzida(qtdeProd);
		apontamentoProd.clicarBotaoFinalizarApontamento();
		apontamentoProd.validaAlertaApontamentoFinalizado();

		// cadastrando a segunda etapa do apontamento
		apontamentoProd.setCodigoOrdemProducao(codigoOP);
		apontamentoProd.setEtapa("11");
		apontamentoProd.clicarBotaoConfirmarApontamento();
		apontamentoProd.validaDataProducao(dataOP, "datdataprodinf");
		apontamentoProd.validaQtdeSaldoOP(qtdeProd);
		apontamentoProd.clicarBotaoAdicionarItemApontamento();
		apontamentoProd.validaDataApontamento(dataApontamento);
		apontamentoProd.validaQtdeProduzida(qtdeProd);
		apontamentoProd.clicarBotaoFinalizarApontamento();
		apontamentoProd.validaAlertaApontamentoFinalizado();

	}

	@E("^consulto o apontamento gerado$")
	public void consultoOApontamentoGerado() throws Throwable {
		apontamentoProd.clicarBotaoPesquisar();
		apontamentoProd.esperaFixa(700);

		// valida as informações retornada na pesquisa
		apontamentoProd.validaNumeroOP(codigoOP);
	}

	@Entao("^o sistema deve deve exibir o apontamento gerado pela ordem de produção$")
	public void oSistemaDeveDeveExibirOApontamentoGeradoPelaOrdemDeProdução() throws Throwable {
		apontamentoProd.setCodigoOrdemProducao(codigoOP);
		apontamentoProd.clicarBotaoPesquisar();
	}

	@After(order = 1)
	public void screenshot() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo,
				new File("target" + File.separator + "screenshot" + File.separator + casoTeste + ".jpg"));
	}

	@After(order = 0)
	public void finaliza() throws IOException {
		if (Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}

}
