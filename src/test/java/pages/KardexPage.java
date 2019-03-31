package pages;

import static org.junit.Assert.assertEquals;

public class KardexPage extends BasePage{

	public void setProdutoConsulta(String codProduto) {
		escrever("ctlnprod2", codProduto);	
	}
	
	public void clicarBotaoPesquisarKardex() {
		clicarBotaId("btnPesquisar");
	}

	public void validaNumeroEPA(String numeroEPA) {
		String numeroEPARetornada = obterValorComXpath("//table//tbody//tr[@class='ENT_DE_PRODUCAO_"+numeroEPA+" trList cursor']//td//u");
	
		numeroEPARetornada = Integer.valueOf(numeroEPARetornada).toString(); //retira os zeros do início que vem do numero da EPA buscado pelo Xpath
		
		assertEquals(numeroEPA, numeroEPARetornada);
	}

	public void validaQtdeEPA(String numeroEPA, String qtde) {
		String qtdeRetornada = obterValorComXpath("//table//tbody//tr[@class='ENT_DE_PRODUCAO_"+numeroEPA+" trList cursor']//td[7]");
		qtdeRetornada = qtdeRetornada.trim();
	
		assertEquals(qtde, qtdeRetornada);
		
	}

	public void clicarBotaoNovo() {
		clicarBotaId("btnLimpar");		
	}
	
}
