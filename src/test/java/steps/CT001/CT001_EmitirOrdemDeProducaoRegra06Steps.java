package steps.CT001;

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
import pages.ConfiguradorOrdemProducaoPage;
import pages.EntradaProdutoAcabadoPage;
import pages.KardexPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.OrdemDeProducaoPCP045Page;

public class CT001_EmitirOrdemDeProducaoRegra06Steps {

	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	private EntradaProdutoAcabadoPage entProAcabado = new EntradaProdutoAcabadoPage();
	private KardexPage kardex = new KardexPage();
	
	//variaveis globais
	
	String casoTeste = "CT001 - Ordem Producao Regra 06"; //varivael que será utilizada para nomear o Screenshot
	
	String codigoOP;
	String codProduto;
	String codEPA;

	@Dado("^que cadastro um configurador com a regra (\\d+)$")
	public void que_cadastro_um_configurador_com_a_regra(int numeroRegra) throws Throwable {
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
		confiOP.clicarAbaMovtoEstoque();
		confiOP.clicarCheckBoxInsumoSemSaldo();
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaSalvoComSucesso();
	}

	@E("^cadastro uma ordem de produção com esta regra$")
	public void cadastro_uma_ordem_de_produção_com_esta_regra() throws Throwable {
		
		menuPage.acessaTelaOrdemProducaoPCP045();
		ordemProd.esperaFixa(700);
		ordemProd.alternarFocoJanela(2);
		ordemProd.setCliente("1182");
		ordemProd.setOrigemOP("0");
		ordemProd.clicarBotaoConfirmar();
		ordemProd.clicarAbaCadastro();
		ordemProd.esperaFixa(700);
		codigoOP = ordemProd.obterCodigoOP("intnumop");
	}

	@E("^insiro o produto (\\d+) para produzir (\\d+) quantidades$")
	public void insiro_o_produto_para_produzir_quantidades(String codigoProduto, String qtdeProduzir) throws Throwable {
		codProduto = codigoProduto;
		ordemProd.clicarAbaItemProduzir();
		ordemProd.setProduto(codigoProduto);
		ordemProd.setQuantidadeProduzir(qtdeProduzir);
		ordemProd.clicarBotaoAdicionar();
		ordemProd.clicarBotaoConfirmarProduto();
		ordemProd.clicarAbaItemProduzir(); 
	}

	@Quando("^finalizo a Ordem de produção$")
	public void finalizo_a_Odem_de_produção() throws Throwable {
		ordemProd.clicarAbaReservaInsumos();
		ordemProd.esperaFixa(300);
		ordemProd.clicarBotaoFinalizarOP();
		ordemProd.esperaFixa(300);
		ordemProd.validaAlertaOPFinalizadaSucesso();
	}

	@E("^realizo a entrada de produto desta Ordem de produção$")
	public void realizo_a_entrada_de_produto_desta_Ordem_de_produção() throws Throwable {
		menuPage.acessaTelaEntradaProdutoAcabado();
		entProAcabado.esperaFixa(700);
		entProAcabado.alternarFocoJanela(3);
		entProAcabado.setTurno("1");
		entProAcabado.clicarBotaoConfirmarEPA();
		entProAcabado.validaAlertaSalvoComSucesso();
		
		codEPA = entProAcabado.obterCodigoEPA(); //Obtem o código da EPA para posteriormente validar na tela de kardex
		
		entProAcabado.setCodigoOrdemProducao(codigoOP);
		entProAcabado.validaProduto(codProduto, "epiproduto2");
		entProAcabado.clicarBotaAdicionarEPA();
		entProAcabado.esperaFixa(700);
		entProAcabado.clicarBotaoConfirmaInsumo();
		entProAcabado.esperaFixa(2000);		
	}
 
	@E("^consulto o estoque do produto (\\d+)$")
	public void consulto_o_estoque_do_produto(String produto) throws Throwable {
		menuPage.acessaTelaKardex();
		kardex.esperaFixa(700);
		kardex.alternarFocoJanela(4);
		kardex.setProdutoConsulta(produto);
		kardex.clicarBotaoPesquisarKardex();
	}

	@Entao("^o sistema deve ter dado entrada de (\\d+) quantidades deste produto$")
	public void o_sistema_deve_ter_dado_entrada_de_quantidades_deste_produto(int arg1) throws Throwable {
		kardex.esperaFixa(1000);
		kardex.validaNumeroEPA(codEPA);
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
