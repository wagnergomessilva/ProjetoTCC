package steps.CT017;

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

public class CT017_EmitirOrdemDeProducaoRegra19Steps {
	
	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private ConfiguradorOrdemProducaoPage confiOP = new ConfiguradorOrdemProducaoPage();
	private OrdemDeProducaoPCP045Page ordemProd = new OrdemDeProducaoPCP045Page();
	private ApontamentoDeProducaoPage apontamentoProd = new ApontamentoDeProducaoPage();
	private EntradaProdutoAcabadoPage entProAcabado = new EntradaProdutoAcabadoPage();
	private KardexPage kardex = new KardexPage();
	RequisicaoSaidaPage requisicaoSaida = new RequisicaoSaidaPage();

	// variaveis globais
	String casoTeste = "CT017 - Ordem Producao Regra 19";

	String codigoOP = "";
	String codProduto = "";
	String codApontamento = "";
	String codEPA = "";
	String dataOP = "";
	String qtdeProd = "";
	String dataApontamento = "";
	
	@Dado("^que Cadastro um configurador de Ordem de produção com a Regra (\\d+)$")
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
		confiOP.clicarCheckboxRegra19();
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaPreencherCampos();
		confiOP.clicarAbaConfigIniciais();
		confiOP.clicarAbaCalculo();
		confiOP.setQuantidadeProduzirConfig("0");
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaPreencherCampos();
		confiOP.clicarAbaImpressao();
		confiOP.setLayoutImpressaoOP("1");
		confiOP.clicarAbaMovtoEstoque();
		confiOP.clicarCheckBoxInsumoSemSaldo();
		confiOP.clicarBotaConfirmar();
		confiOP.validaAlertaSalvoComSucesso();
	}

	@E("^Cadastro a Ordem de Produção utilizando esta regra, informando o cliente de código (\\d+)$")
	public void cadastroAOrdemDeProduçãoUtilizandoEstaRegraInformandoOClienteDeCódigo(String cliente) throws Throwable {
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

	@E("^Adiciono o item de código (\\d+) com quantidade (\\d+)$")
	public void adicionoOItemDeCódigoComQuantidade(String codigoProduto, String qtdeProduzir) throws Throwable {
		codProduto = codigoProduto;
		qtdeProd = qtdeProduzir;
		ordemProd.clicarAbaItemProduzir();
		ordemProd.setProduto(codigoProduto);
		ordemProd.setQuantidadeProduzir(qtdeProduzir);
		ordemProd.clicarBotaoAdicionar();
		ordemProd.esperaFixa(300);
		ordemProd.validaQtdeProduzir(qtdeProduzir);
	}

	@Quando("^Finalizo esta a Ordem de Produção$")
	public void finalizoEstaAOrdemDeProdução() throws Throwable {
		ordemProd.clicarBotaoConfirmarProduto();
		ordemProd.clicarAbaReservaInsumos();
		ordemProd.clicarBotaoFinalizarOP();
		ordemProd.esperaFixa(300);
		ordemProd.validaAlertaOPFinalizadaSucesso();
	}

	@E("^Realizo as etpas do apontamento de produção$")
	public void realizoAsEtpasDoApontamentoDeProdução() throws Throwable {
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
		apontamentoProd.clicarAbaItensDeConsumo();
		apontamentoProd.clicarBotaoFinalizarApontamentoInsumo();
		apontamentoProd.validaAlertaApontamentoFinalizado();
		
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
		apontamentoProd.setTurno("1");
		apontamentoProd.clicarBotaoFinalizarApontamentoEPA();
		apontamentoProd.esperaFixa(1000);
		apontamentoProd.validaAlertaApontamentoFinalizado();
	}

	@Entao("^o sistema deve Movimentar o Estoque do produto (\\d+) com uma entrada de (\\d+) quantidades$")
	public void oSistemaDeveMovimentarOEstoqueDoProdutoComUmaEntradaDeQuantidades(String produto, String qtde) throws Throwable {
		menuPage.acessaTelaEntradaProdutoAcabado();
		entProAcabado.alternarFocoJanela(4);
		entProAcabado.esperaFixa(1000);		
		entProAcabado.clicarBotaoPesquisarEPA();
		entProAcabado.setCodigoOrdemProducao(codigoOP);
		entProAcabado.clicarBotaoPesquisarEPA();
		entProAcabado.esperaFixa(500);
		entProAcabado.clicarBotaoEditarEPA();
		entProAcabado.esperaFixa(1000);
		codEPA = entProAcabado.obterCodigoEPA();
		
		menuPage.acessaTelaKardex();
		kardex.esperaFixa(700);
		kardex.alternarFocoJanela(5);
		kardex.setProdutoConsulta(produto);
		kardex.clicarBotaoPesquisarKardex();
		kardex.esperaFixa(1000);
		kardex.validaNumeroEPA(codEPA);
		kardex.validaQtdeEPA(codEPA, qtde);
	}

	@E("^deve ter gerado o consumo dos insumos referente a primeira etapa do apontamento de produção\\.$")
	public void deveTerGeradoOConsumoDosInsumosReferenteAPrimeiraEtapaDoApontamentoDeProdução() throws Throwable {
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
		requisicaoSaida.esperaExplicita("btnSave");
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
