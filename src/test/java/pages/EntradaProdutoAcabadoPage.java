package pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;

public class EntradaProdutoAcabadoPage extends BasePage {

	public void setTurno(String turnoCodigo) {
		escrever("epturno2", turnoCodigo);
	}

	public void validaAlertaSalvoComSucesso() {
		assertEquals("Salvo com sucesso.", alertaObterTextoEAceita());
	}

	public void clicarBotaoConfirmarEPA() {
		clicarBotaId("btnSave");
	}

	public void setCodigoOrdemProducao(String codigoOP) {
		escreverEDarEnter("objordemprodepi2", codigoOP);
	}

	public void validaProduto(String produto, String id_campo) {
		assertEquals(produto, getCodigo(By.id(id_campo)));
	}

	public void clicarBotaAdicionarEPA() {
		clicarBotaId("btnSave2");
	}

	public void clicarBotaoConfirmaInsumo() {
		clicarBotaId("btnConfirmInsumo");
	}

	public String obterCodigoEPA() {
		String codigo = getCodigo(By.id("epcod"));
		return codigo;
	}

	public void clicarBotaoPesquisarEPA() {
		clicarBotaId("btnPesquisar");		
	}

	public void validaEPAGerada(String codigoOP, String codApontamento) {
		String retorno = obterValorComId("epcoment");
		System.out.println(retorno);
		assertEquals("Entrada de produção gerada a partir da Ordem de Produção "+codigoOP+", Apontamento "+codApontamento+".", retorno);		
	}

	public void clicarBotaoEditarEPA() {
		clicarBotaXpath("//td[@class='itens']//img[@alt='Alterar']");
		
	}

}
