package steps;

import static core.DriverFactory.killDriver;

import java.io.IOException;

import core.Propriedades;
import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pages.LoginPage;
import pages.MenuPage;
import pages.OutrasNotasPage;

public class EmitirNFeOutrasNotasSteps{
	
	private LoginPage loginPage = new LoginPage();
	private MenuPage menuPage	= new MenuPage();
	private OutrasNotasPage ONPage = new OutrasNotasPage();
	
	@Dado("^que desejo emitir a nota fiscal$")
	public void que_desejo_emitir_a_nota_fiscal() throws Throwable {
		loginPage.acessarTelaSistema();
		loginPage.setUsuario("robohom");
		loginPage.setSenha("robo123");
		loginPage.entrarNoSistema();
		
		menuPage.acessaTelaOutrasNotasFiscais();
	}

	@Quando("^informo todos os dados da aba Cadastro$")
	public void informo_todos_os_dados_da_aba_Cadastro() throws Throwable {
		
		ONPage.alternarFocoJanela(1);
		ONPage.setCodigoCliente("1182");
		ONPage.setCodigoResponsavel("1");
		ONPage.setTipoOperacaoValue("1");
		
		ONPage.clicarBotaoConfirmarCadastro();
		ONPage.validaAlertaSalvoComSucesso();		
	}

	@Quando("^adiciono o produto de código (\\d+)$")
	public void adiciono_o_produto_de_código(String codigoProduto) throws Throwable {
		
		ONPage.clicarAbaItensDeVenda();
		ONPage.setCodigoProduto(codigoProduto);
		ONPage.setCFOP("5.101-A");
		ONPage.setQuantidadeItem("10,00");
		ONPage.setValorUnitarioItem("10,00000");
		
		ONPage.clicarBotaoConfirmarItem();
	}

	@Quando("^informo as parcelas$")
	public void informo_as_parcelas() throws Throwable {
		ONPage.clicarAbaTransportadora();
		ONPage.clicarBotaoConfirmarTransportadora();
		ONPage.validaAlertaSalvoComSucesso();
		
		ONPage.clicarAbaResumo();
		ONPage.clicarSubAbaCondPagto();
		ONPage.setTipoParcela("2");
		
		ONPage.clicarBotaoConfirmarResumo();
	}

	@Quando("^finalizo a nota fiscal$")
	public void finalizo_a_nota_fiscal() throws Throwable {		
		
		ONPage.clicarBotaoFinalizarNota();
		ONPage.validaAlertaNotaFinalizada();
		ONPage.validaAlertaCancelaImpressaoEspelho();
	}

	@Então("^quando realizo a consulta desta nota, ela está autorizada$")
	public void quando_realizo_a_consulta_desta_nota_ela_está_autorizada() throws Throwable {
		ONPage.clicarBotaoPesquisar();
	}
	
	/*@After
	public void finaliza() throws IOException {
		if (Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}*/
}
