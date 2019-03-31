package pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;

public class ApontamentoDeProducaoPage extends BasePage {

	public void setCodigoOrdemProducao(String codigoOP) {
		escreverEDarEnter("objordprodap2", codigoOP);
	}

	public void validaProduto(String produto, String id_campo) {
		assertEquals(produto, getCodigo(By.id(id_campo)));
	}

	public void setEtapa(String etapa) {
		escrever("objetadestap2", etapa);
	}

	public String obterCodigoApontamento() {
		String codigo = getCodigo(By.id("intcodigoap"));
		return codigo;
	}

	public void clicarBotaoConfirmarApontamento() {
		clicarBotaId("btnSave");
	}

	public void validaDataProducao(String dataProducao, String id_campo) {
		assertEquals(dataProducao, getData(By.id(id_campo)));
	}

	public void validaQtdeSaldoOP(String qtdeProd) {
		assertEquals(qtdeProd, obterValorComId("saldoaponta"));
	}

	public void clicarBotaoAdicionarItemApontamento() {
		clicarBotaId("btnAdicItem");

	}

	public void validaDataApontamento(String dataApontamento) {
		String dataRetornada = obterValorComXpath("//table[@class='cTable']//tr[@class='trList cursor']//td");
		assertEquals(dataApontamento, dataRetornada);
	}

	public String obterDataApontamento() {
		String data = getCodigo(By.id("datdataprodinf"));
		return data;
	}

	public void validaQtdeProduzida(String qtdeProd) {
		String qtdeRetornada = obterValorComXpath("//table[@class='cTable']//tr[@class='trList cursor']//td[7]");
		assertEquals(qtdeProd, qtdeRetornada);
	}

	public void clicarBotaoFinalizarApontamento() {
		clicarBotaId("btnFinaliz");
	}

	public void validaAlertaApontamentoFinalizado() {
		assertEquals("Apontamento finalizado com sucesso.", alertaObterTextoEAceita());
	}

	public void clicarBotaoPesquisar() {
		clicarBotaId("btnPesq");
	}

	public void validaNumeroOP(String codigoOP) {
		String numeroOPRetornada = obterValorComXpath(" //table//tr[@class='trList cursor']//td[3]");

		numeroOPRetornada = Integer.valueOf(numeroOPRetornada).toString(); // retira os zeros do início que vem do numero da OP buscado pelo Xpath

		assertEquals(codigoOP, numeroOPRetornada);

	}

	public void setTurno(String turnoCodigo) {
		escrever("epturno2", turnoCodigo);		
	}

	public void clicarBotaoFinalizarApontamentoEPA() {
		clicarBotaId("btnFinalizCons");	
	}

	public void clicarBotaoConfirmarInsumos() {
		clicarBotaId("btnConfirmInsumo");
	}

	public void setProdutoApontamento(String produto) {
		escrever("objprodutap2", produto);
	}

}
