package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

public class OrdemDeProducaoPCP045Page extends BasePage {

	public void setOrigemOP(String valor) {
		selecionarComboBox("intorigop", valor);
	}

	public void clicarBotaoConfirmar() {
		clicarBotaId("btnSave");
	}

	public void clicarAbaCadastro() {
		clicarAba("itemaba1");
	}

	public void clicarAbaItemProduzir() {
		clicarAba("itemaba4");
	}

	public void setCliente(String cliente) {
		escrever("objclientop2", cliente);
	}

	public void setProduto(String codigoProduto) {
		escrever("objprodutopi2", codigoProduto);
	}

	public void setQuantidadeProduzir(String qtdeProduzir) {
		escrever("fltqtdprodopi", qtdeProduzir);
	}

	public void clicarBotaoAdicionar() {
		clicarBotaId("btnAdicItem");
	}

	public void clicarBotaoConfirmarProduto() {
		clicarBotaId("btnConfirmProd");
	}

	public void clicarAbaReservaInsumos() {
		clicarAba("itemaba5");
	}

	public void clicarBotaoFinalizarOP() {
		clicarBotaId("btnConfirmInsumo");
	}

	public void clicarBotaoFinalizarOPAbaItem() {
		clicarBotaId("btnFinalizProd");
	}

	public void validaAlertaOPFinalizadaSucesso() {
		assertTrue(alertaObterTextoECancela().startsWith("Ordem de produção finalizada com sucesso."));
	}

	public String obterCodigoOP(String id_campo) {
		String codigo = getCodigo(By.id(id_campo));
		return codigo;
	}

	public String obterDataEmissaoOP(String id_campo) {
		String data = getCodigo(By.id(id_campo));
		return data;
	}

	public void validaQtdeProduzir(String qtdeProduzir) {
		String qtdeProduzirRetornada = obterValorComXpath("//table[@class='cTable']//tr[@class='trList cursor']//td[6]");
		assertEquals(qtdeProduzir, qtdeProduzirRetornada);
	}
}
