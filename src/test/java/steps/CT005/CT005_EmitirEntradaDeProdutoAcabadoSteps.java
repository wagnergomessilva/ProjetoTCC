package steps.CT005;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.EntradaProdutoAcabadoPage;
import pages.KardexPage;
import pages.LoginPage;
import pages.MenuPage;

public class CT005_EmitirEntradaDeProdutoAcabadoSteps {

	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage = new MenuPage();
	private EntradaProdutoAcabadoPage entProAcabado = new EntradaProdutoAcabadoPage();
	private KardexPage kardex = new KardexPage();

	// variaveis globais
	String casoTeste = "CT005 - Entrada de produto acabado sem ordem de produção";

	String codEPA = "";

	@Dado("^que acesso a tela de Entrada de Produto acabado$")
	public void queAcessoATelaDeEntradaDeProdutoAcabado() throws Throwable {
		loginPage.acessarTelaSistema();
		loginPage.setUsuario("robohom");
		loginPage.setSenha("robo123");
		loginPage.entrarNoSistema();
		menuPage.acessaTelaEntradaProdutoAcabado();
		entProAcabado.alternarFocoJanela(1);
		entProAcabado.esperaFixa(1000);
	}

	@E("^realizao o cadastro de uma Entrada de produto acabado$")
	public void realizaoOCadastroDeUmaEntradaDeProdutoAcabado() throws Throwable {
		entProAcabado.setTurno("1");
		entProAcabado.setSolicitante("Solicitante Robô teste automação");
		entProAcabado.setComentario("Comentário dp Robô teste automação");
		entProAcabado.clicarBotaoConfirmarEPA();
		entProAcabado.validaAlertaSalvoComSucesso();
		codEPA = entProAcabado.obterCodigoEPA(); // Obtem o código da EPA para posteriormente validar na tela de kardex

	}

	@E("^insiro o Produto de código (\\d+) com quantidade (\\d+) quantidades$")
	public void insiroOProdutoDeCódigoComQuantidadeQuantidades(String codProduto, String qtde) throws Throwable {
		entProAcabado.setProdutoEPA(codProduto);
		entProAcabado.setQuantidadeProduzir(qtde);
		entProAcabado.clicarBotaAdicionarEPA();
		entProAcabado.esperaFixa(700);
	}

	@Quando("^finalizo a Entrada de priduto acabado$")
	public void finalizoAEntradaDePridutoAcabado() throws Throwable {
		entProAcabado.clicarBotaoConfirmaInsumoRequisicao();
		entProAcabado.esperaFixa(2000);
	}

	@Entao("^o sistema deve movimentar o estoque do produto (\\d+) com (\\d+) quantidades$")
	public void oSistemaDeveMovimentarOEstoqueDoProdutoComQuantidades(String produto, String qtde) throws Throwable {
		menuPage.acessaTelaKardex();
		kardex.esperaFixa(700);
		kardex.alternarFocoJanela(2);
		kardex.setProdutoConsulta(produto);
		kardex.clicarBotaoPesquisarKardex();
		kardex.esperaFixa(1000);
		kardex.validaNumeroEPA(codEPA);
		kardex.validaQtdeEPA(codEPA, qtde);
	}

	@After(order = 1)
	public void screenshot() throws IOException, HeadlessException, AWTException {
		entProAcabado.screenshotTela(casoTeste);
	}

	@After(order = 0)
	public void finaliza() throws IOException {
		entProAcabado.finalizarNavegador();
	}
}
