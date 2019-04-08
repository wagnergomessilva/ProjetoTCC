package steps.CT010;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

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

public class CT010_EmitirOrdemDeProducaoRegra10Steps {

	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	private ApontamentoDeProducaoPage apontamentoProd = new ApontamentoDeProducaoPage();
	private EntradaProdutoAcabadoPage entProAcabado = new EntradaProdutoAcabadoPage();
	private KardexPage kardex = new KardexPage();

	// variaveis globais
	String casoTeste = "CT010 - Ordem Producao Regra 10 com apontamento de produção e entrada de produto acabado";

	String codigoOP = "";
	String codProduto = "";
	String codApontamento = "";
	String codEPA = "";
	String dataOP = "";
	String qtdeProd = "";
	String dataApontamento = "";

	@Dado("^que efetuo o cadastro um configurador com a Regra (\\d+)$")
	public void queEfetuoOCadastroUmConfiguradorComARegra(String numeroRegra) throws Throwable {
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
		confiOP.clicarCheckboxRegra10();
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

	@E("^realizo o cadastro de uma ordem de produção com esta regra$")
	public void realizoOCadastroDeUmaOrdemDeProduçãoComEstaRegra() throws Throwable {
		menuPage.acessaTelaOrdemProducaoPCP045();
		ordemProd.esperaFixa(1000);
		ordemProd.alternarFocoJanela(2);
		ordemProd.setCliente("1182");
		ordemProd.setOrigemOP("0");
		ordemProd.clicarBotaoConfirmar();
		ordemProd.clicarAbaCadastro();
		ordemProd.esperaFixa(700);
		codigoOP = ordemProd.obterCodigoOP("intnumop");
		dataOP = ordemProd.obterDataEmissaoOP("datemisop");
	}

	@E("^adiciono o produto de código (\\d+) para produzir (\\d+) quantidades$")
	public void adicionoOProdutoDeCódigoParaProduzirQuantidades(String codigoProduto, String qtdeProduzir) throws Throwable {
		codProduto = codigoProduto;
		qtdeProd = qtdeProduzir; 									
		ordemProd.clicarAbaItemProduzir();
		ordemProd.setProduto(codigoProduto);
		ordemProd.setQuantidadeProduzir(qtdeProduzir);
		ordemProd.clicarBotaoAdicionar();
		ordemProd.esperaFixa(300);
		ordemProd.validaQtdeProduzir(qtdeProduzir);
		
	}

	@Quando("^finalizo esta ordem de produção$")
	public void finzalizoEstaOrdemDeProdução() throws Throwable {
		ordemProd.clicarBotaoFinalizarOPAbaItem();
		ordemProd.validaAlertaOPFinalizadaSucesso();
	}

	@E("^efetuo as etapas do apontamento de produção$")
	public void efetuoAsEtapasDoApontamentoDeProdução() throws Throwable {
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
		apontamentoProd.validaAlertaApontamentoFinalizado();
	}

	@E("^efetuo a entrada de produto acabado desta ordem de produção$")
	public void efetuoAEntradaDeProdutoAcabadoDestaOrdemDeProdução() throws Throwable {
		menuPage.acessaTelaEntradaProdutoAcabado();
		entProAcabado.esperaFixa(1000);
		entProAcabado.alternarFocoJanela(4);
		entProAcabado.setTurno("1");
		entProAcabado.clicarBotaoConfirmarEPA();
		entProAcabado.validaAlertaSalvoComSucesso();

		codEPA = entProAcabado.obterCodigoEPA();

		entProAcabado.setCodigoOrdemProducao(codigoOP);
		entProAcabado.validaProduto(codProduto, "epiproduto2");
		entProAcabado.clicarBotaAdicionarEPA();
		entProAcabado.esperaFixa(700);
		entProAcabado.clicarBotaoConfirmaInsumo();
		entProAcabado.esperaFixa(2000);
	}

	@Entao("^o Sistema deve movimentar entrada de (\\d+) quantidades do produto (\\d+)$")
	public void oSistemaDeveMovimentarEntradaDeQuantidadesDesteProduto(String qtde, String produto) throws Throwable {
		menuPage.acessaTelaKardex();
		kardex.esperaFixa(700);
		kardex.alternarFocoJanela(5);
		kardex.setProdutoConsulta(codProduto);
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
