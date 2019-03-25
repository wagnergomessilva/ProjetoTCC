package steps;

import static core.DriverFactory.killDriver;

import java.io.IOException;

import core.Propriedades;
import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.ConfiguradorOrdemProducaoPage;
import pages.EntradaProdutoAcabadoPage;
import pages.KardexPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.OrdemDeProducaoPCP045Page;

public class EmitirOrdemDeProducaoRegra06Steps {

	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	private EntradaProdutoAcabadoPage entProAcabado = new EntradaProdutoAcabadoPage();
	KardexPage kardex = new KardexPage();
	
	//variaveis globais
	String codigoOP;
	String codProduto;

	@Dado("^que cadastro um configurador com a regra (\\d+)$")
	public void que_cadastro_um_configurador_com_a_regra(int arg1) throws Throwable {
		loginPage.acessarTelaSistema();
		loginPage.setUsuario("robohom");
		loginPage.setSenha("robo123");
		loginPage.entrarNoSistema();

		// menuPage.esperaFixa(2000);
		menuPage.acessaTelaConfiguradorDeOrdemProducao();

		// confiOP.esperaFixa(2000);
		confiOP.alternarFocoJanela(1);
		confiOP.setDescricaoRegra("REGRA 06");
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

	@Dado("^cadastro uma ordem de produção com esta regra$")
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

	@Dado("^insiro o produto (\\d+) para produzir (\\d+) quantidades$")
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
		ordemProd.validaAlertaOPFinalizadaSucesso();
	}

	@Quando("^realizo a entrada de produto desta Ordem de produção$")
	public void realizo_a_entrada_de_produto_desta_Ordem_de_produção() throws Throwable {
		menuPage.acessaTelaEntradaProdutoAcabado();
		entProAcabado.esperaFixa(700);
		entProAcabado.alternarFocoJanela(3);
		entProAcabado.setTurno("1");
		entProAcabado.clicarBotaoConfirmarEPA();
		entProAcabado.validaAlertaSalvoComSucesso();
		entProAcabado.setCodigoOrdemProducao(codigoOP);
		entProAcabado.validaProduto(codProduto, "epiproduto2");
		entProAcabado.clicarBotaAdicionarEPA();
		entProAcabado.esperaFixa(700);
		entProAcabado.clicarBotaoConfirmaInsumo();
		entProAcabado.esperaFixa(2000);
	}
 
	@Quando("^consulto o estoque do produto (\\d+)$")
	public void consulto_o_estoque_do_produto(int arg1) throws Throwable {
		menuPage.acessaTelaKardex();
		kardex.esperaFixa(700);
		kardex.alternarFocoJanela(4);
		kardex.setProdutoConsulta(codProduto);
		kardex.clicarBotaoPesquisarKardex();
	}

	@Entao("^o sistema deve ter dado entrada de (\\d+) quantidades deste produto$")
	public void o_sistema_deve_ter_dado_entrada_de_quantidades_deste_produto(int arg1) throws Throwable {

	}

	@After
	public void finaliza() throws IOException {
		if (Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
}
