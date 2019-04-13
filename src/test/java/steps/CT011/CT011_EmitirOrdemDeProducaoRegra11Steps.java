package steps.CT011;

import static utils.DataUtils.obterDataFormatada;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Date;

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
import pages.RequisicaoSaidaPage;

public class CT011_EmitirOrdemDeProducaoRegra11Steps {
	
	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	private ApontamentoDeProducaoPage apontamentoProd = new ApontamentoDeProducaoPage();
	private EntradaProdutoAcabadoPage entProAcabado = new EntradaProdutoAcabadoPage();
	private KardexPage kardex = new KardexPage();
	private RequisicaoSaidaPage requisicaoSaida = new RequisicaoSaidaPage();

	// variaveis globais
	String casoTeste = "CT011 - Ordem Producao Regra 11";

	String codigoOP = "";
	String codProduto = "";
	String codApontamento = "";
	String codEPA = "";
	String dataOP = "";
	String qtdeProd = "";
	String dataApontamento = "";
	
	@Dado("^que Efetuo o cadastro um configurador com a Regra (\\d+)$")
	public void queEfetuoOCadastroUmConfiguradorComARegra(int numeroRegra) throws Throwable {
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
		confiOP.clicarCheckboxRegra11();
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

	@E("^realizo o cadastro de uma ordem de produção com esta regra informando o cliente (\\d+)$")
	public void realizoOCadastroDeUmaOrdemDeProduçãoComEstaRegraInformandoOCliente(String cliente) throws Throwable {
		menuPage.acessaTelaOrdemProducaoPCP045();
		ordemProd.esperaExplicita("btnSave");
		ordemProd.alternarFocoJanela(2);
		ordemProd.setCliente(cliente);
		ordemProd.setOrigemOP("0");
		ordemProd.clicarBotaoConfirmar();
		ordemProd.clicarAbaCadastro();
		ordemProd.esperaFixa(700);
		codigoOP = ordemProd.obterCodigoOP("intnumop");
		dataOP = ordemProd.obterDataEmissaoOP("datemisop");
	}

	@E("^adiciono o produto com código (\\d+) para produzir (\\d+) quantidades$")
	public void adicionoOProdutoComCódigoParaProduzirQuantidades(String codigoProduto, String qtdeProduzir) throws Throwable {
		codProduto = codigoProduto;
		qtdeProd = qtdeProduzir; 									
		ordemProd.clicarAbaItemProduzir();
		ordemProd.setProduto(codigoProduto);
		ordemProd.setQuantidadeProduzir(qtdeProduzir);
		ordemProd.clicarBotaoAdicionar();
		ordemProd.esperaFixa(300);
		ordemProd.validaQtdeProduzir(qtdeProduzir);
	}

	@Quando("^Finalizo esta ordem de produção$")
	public void finalizoEstaOrdemDeProdução() throws Throwable {
		ordemProd.clicarBotaoFinalizarOPAbaItem();
		ordemProd.validaAlertaOPFinalizadaSucesso();
	}

	@E("^Efetuo as etapas do apontamento de produção$")
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

	@E("^Efetuo a entrada de produto acabado desta ordem de produção$")
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
		kardex.setProdutoConsulta(produto);
		kardex.clicarBotaoPesquisarKardex();
		kardex.esperaFixa(1000);
		kardex.validaNumeroEPA(codEPA);
		kardex.validaQtdeEPA(codEPA, qtde);
	}

	@E("^o sistema deve ter gerado o consumo dos insumos pela primeira etapa do apontamento de produção$")
	public void oSistemaDeveTerGeradoOConsumoDosInsumosPelaPrimeiraEtapaDoApontamentoDeProdução() throws Throwable {
		menuPage.acessaTelaRequisicaoSaida();
		requisicaoSaida.esperaFixa(1000);
		kardex.alternarFocoJanela(6);
		requisicaoSaida.clicarBotaPesquisarRequisicao();
		requisicaoSaida.esperaFixa(1000);
		requisicaoSaida.setDataPeriodo01(obterDataFormatada(new Date()));
		requisicaoSaida.setDataPeriodo02(obterDataFormatada(new Date()));
		requisicaoSaida.setComentario("REQUISIÇÃO DE MATERIAL GERADA A PARTIR DA ORDEM DE PRODUCAO " + codigoOP);
		requisicaoSaida.clicarBotaPesquisarRequisicao();
		requisicaoSaida.esperaFixa(300);
		requisicaoSaida.clicarBotaEditarRequisicao();				
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
