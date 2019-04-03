package steps.CT003;

import static core.DriverFactory.killDriver;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
import pages.EntradaProdutoAcabadoPage;
import pages.KardexPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.OrdemDeProducaoPCP045Page;

public class CT003_EmitirOrdemProducaoRegra12Steps {

	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	private EntradaProdutoAcabadoPage entProAcabado = new EntradaProdutoAcabadoPage();
	private ApontamentoDeProducaoPage apontamentoProd = new ApontamentoDeProducaoPage();
	private KardexPage kardex = new KardexPage();

	// variaveis globais
	String casoTeste = "CT003 - Ordem Producao Regra 12 com EPA na ultima etapa do apontamento";

	String codigoOP;
	String codProduto;
	String codApontamento;
	String codEPA;
	String dataOP;
	String qtdeProd;
	String dataApontamento;

	@Dado("^que cadastro uma configuração de ordem de produção com a regra (\\d+)$")
	public void queCadastroUmaConfiguraçãoDeOrdemDeProduçãoComARegra(int numeroRegra) throws Throwable {
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
		confiOP.clicarCheckboxRegra12();
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaPreencherCampos();
		confiOP.clicarAbaConfigIniciais();
		confiOP.clicarAbaCalculo();
		confiOP.setQuantidadeProduzirConfig("0");
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaPreencherCampos();
		confiOP.clicarAbaImpressao();
		confiOP.setLayoutImpressaoOP("0");
		confiOP.clicarAbaMovtoEstoque();
		confiOP.clicarCheckBoxInsumoSemSaldo();
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaSalvoComSucesso();
	}

	@E("^realizo o cadastro uma ordem de produção utilizando esta regra, informando o cliente de código (\\d+)$")
	public void realizoOCadastroUmaOrdemDeProduçãoUtilizandoEstaRegraInformandoOClienteDeCódigo(String codigoCliente)
			throws Throwable {
		menuPage.acessaTelaOrdemProducaoPCP045();
		ordemProd.esperaFixa(1000);
		ordemProd.alternarFocoJanela(2);
		ordemProd.setCliente(codigoCliente);
		ordemProd.setOrigemOP("0");
		ordemProd.clicarBotaoConfirmar();
		ordemProd.clicarAbaCadastro();
		ordemProd.esperaFixa(700);
		codigoOP = ordemProd.obterCodigoOP("intnumop");
		dataOP = ordemProd.obterDataEmissaoOP("datemisop");
	}

	@E("^insiro o produto de código (\\d+) com quantidade (\\d+)$")
	public void insiroOProdutoDeCódigoComQuantidade(String codigoProduto, String qtdeProduzir) throws Throwable {
		codProduto = codigoProduto;
		qtdeProd = qtdeProduzir; // guarda a quantidade a produzir na variavel global "qtdeProd" para posteriormente validar o saldo da OP na tela de apontamento
		ordemProd.clicarAbaItemProduzir();
		ordemProd.setProduto(codigoProduto);
		ordemProd.setQuantidadeProduzir(qtdeProduzir);
		ordemProd.clicarBotaoAdicionar();
		ordemProd.esperaFixa(300);
		ordemProd.validaQtdeProduzir(qtdeProduzir);
		ordemProd.clicarBotaoConfirmarProduto();
		ordemProd.clicarAbaItemProduzir();
	}

	@Quando("^finalizo a ordem de produção$")
	public void finalizoAOrdemDeProdução() throws Throwable {
		ordemProd.clicarAbaReservaInsumos();
		ordemProd.esperaFixa(300);
		ordemProd.clicarBotaoFinalizarOP();
		ordemProd.validaAlertaOPFinalizadaSucesso();		
	}

	@E("^cadastro o apontamento desta ordem de produção$")
	public void cadastroOApontamentoDestaOrdemDeProdução() throws Throwable {
		menuPage.acessaTelaApontamentoProducao();
		apontamentoProd.esperaFixa(1000);
		apontamentoProd.alternarFocoJanela(3);
		apontamentoProd.setCodigoOrdemProducao(codigoOP);
		apontamentoProd.validaProduto(codProduto, "objprodutap2");
		apontamentoProd.setEtapa("8");
		apontamentoProd.clicarBotaoConfirmarApontamento();		
		dataApontamento = apontamentoProd.obterDataApontamento();
		apontamentoProd.validaDataProducao(dataOP, "datdataprodinf");
		apontamentoProd.validaQtdeSaldoOP(qtdeProd);
		apontamentoProd.clicarBotaoAdicionarItemApontamento();
		apontamentoProd.esperaFixa(300);
		apontamentoProd.validaDataApontamento(dataApontamento);
		apontamentoProd.validaQtdeProduzida(qtdeProd);
		apontamentoProd.clicarBotaoFinalizarApontamento();
		apontamentoProd.validaAlertaApontamentoFinalizado();

		// cadastrando a segunda etapa do apontamento
		apontamentoProd.setCodigoOrdemProducao(codigoOP);
		apontamentoProd.setEtapa("11");
		apontamentoProd.clicarBotaoConfirmarApontamento();
		codApontamento = apontamentoProd.obterCodigoApontamento();
		apontamentoProd.validaDataProducao(dataOP, "datdataprodinf");
		apontamentoProd.validaQtdeSaldoOP(qtdeProd);
		apontamentoProd.clicarBotaoAdicionarItemApontamento();
		apontamentoProd.esperaFixa(300);
		apontamentoProd.validaDataApontamento(dataApontamento);
		apontamentoProd.validaQtdeProduzida(qtdeProd);
		apontamentoProd.clicarBotaoFinalizarApontamento();
		apontamentoProd.validaAlertaPreencherCampos();
		apontamentoProd.esperaFixa(300);
		apontamentoProd.setTurno("1");
		apontamentoProd.clicarBotaoFinalizarApontamentoEPA();
		apontamentoProd.esperaFixa(1000);
		apontamentoProd.clicarBotaoConfirmarInsumos();		
		apontamentoProd.validaAlertaApontamentoFinalizado();
	}

	@Entao("^o sistema deve gerar automaticamente a entrada de produto acabado do produto (\\d+) com (\\d+) quantidades$")
	public void oSistemaDeveGerarAutomaticamenteAEntradaDeProdutoAcabadoDoProdutoComQuantidades(int arg1, int arg2) throws Throwable {
		menuPage.acessaTelaEntradaProdutoAcabado();
		entProAcabado.esperaFixa(1000);
		entProAcabado.alternarFocoJanela(4);		
		entProAcabado.clicarBotaoPesquisarEPA();
		entProAcabado.setCodigoOrdemProducao(codigoOP);
		entProAcabado.clicarBotaoPesquisarEPA();
		entProAcabado.esperaFixa(300);
		entProAcabado.clicarBotaoEditarEPA();
		entProAcabado.esperaFixa(1000);
		codEPA = entProAcabado.obterCodigoEPA();
		System.out.println(codEPA);
		entProAcabado.validaEPAGerada(codigoOP, codApontamento);
	}

	@E("^deve movimentar o estoque do produto (\\d+) com uma entrada de (\\d+) quantidades$")
	public void deveMovimentarOEstoqueDoProdutoComUmaEntradaDeQuantidades(String produto, String qtde) throws Throwable {
		menuPage.acessaTelaKardex();
		kardex.esperaFixa(700);
		kardex.alternarFocoJanela(5);
		kardex.setProdutoConsulta(produto);
		kardex.clicarBotaoPesquisarKardex();
		kardex.esperaFixa(1000);
		kardex.validaNumeroEPA(codEPA);
		kardex.validaQtdeEPA(codEPA, qtde); 
	}

	@After(order = 1)
	public void screenshot() throws IOException, HeadlessException, AWTException {
		
		/*TakesScreenshot screenshot = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo,
				new File("target" + File.separator + "screenshot" + File.separator + casoTeste + ".jpg"));*/
		BufferedImage screenchot = new Robot().createScreenCapture(new Rectangle(java.awt.Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(screenchot, "jpg", new File("target/screenshot/"+casoTeste+".jpg"));
	}

	@After(order = 0)
	public void finaliza() throws IOException {
		if (Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
}
