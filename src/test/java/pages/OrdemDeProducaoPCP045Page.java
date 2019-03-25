package pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

public class OrdemDeProducaoPCP045Page extends BasePage {

	public void setOrigemOP(String valor) {
		selecionarComboBox("intorigop", valor);
	}

	public void clicarBotaoConfirmar() {
		clicarBotaXpath("//span[@class='botoes']//input[@id='btnSave']");
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

	public void validaAlertaOPFinalizadaSucesso() {
		assertTrue(alertaObterTextoECancela().startsWith("Ordem de produção finalizada com sucesso."));		
	}

	public String obterCodigoOP(String id_campo) {
		String teste = getCodigo(By.id(id_campo));
		System.out.println(teste);
		return teste;
	}
}
