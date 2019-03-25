package pages;

import static org.junit.Assert.assertEquals;

public class OutrasNotasPage extends BasePage {

	public void alternarFocoTelaOutrasNotas() {
		alternarFocoJanela(1);
	}

	public void setCodigoCliente(String codigoCliente) {
		inserirCodigo("fatcliente2", codigoCliente);
	}

	public void setCodigoResponsavel(String codigoResponsavel) {
		inserirCodigo("fatResponsavel2", codigoResponsavel);
	}

	public void setTipoOperacaoValue(String tipoOperacao) {
		selecionarComboBox("fatoperacao", tipoOperacao);
	}

	public void setTipoParcela(String tipoParcela) {
		selecionarComboBox("fatTipoParcela", tipoParcela);
	}

	public void clicarBotaoConfirmarCadastro() {
		clicarBotaId("btnSaveCadastro");
	}

	public void clicarBotaoConfirmarItem() {
		clicarBotaId("btnSaveItemVenda");
	}
	
	public void clicarBotaoConfirmarResumo() {
		clicarBotaId("btnSaveResumo");
	}
	
	public void clicarBotaoFinalizarNota() {
		clicarBotaId("btnFinalizar");
	}
	
	public void clicarBotaoPesquisar() {
		clicarBotaId("btnPesqCadastro");
	}
	
	public void clicarBotaoConfirmarTransportadora() {
		clicarBotaId("btnSaveTransp");
	}

	public void validaAlertaSalvoComSucesso() {
		assertEquals("Salvo com sucesso.", alertaObterTextoEAceita());
	}
	
	public void validaAlertaNotaFinalizada() {
		assertEquals("Operação efetuada com sucesso.", alertaObterTextoEAceita());
	}
	
	public void validaAlertaCancelaImpressaoEspelho() {
		assertEquals("Deseja imprimir espelho?", alertaObterTextoECancela());
	}

	public void clicarAbaItensDeVenda() {
		clicarAba("itemaba4");
	}
	
	public void clicarAbaTransportadora() {
		clicarAba("itemaba9");
	}

	public void clicarAbaResumo() {
		clicarAba("itemaba6");
	}

	public void clicarSubAbaCondPagto() {
		clicarAba("abaresumo3");
	}

	public void setCodigoProduto(String codigoProduto) {
		inserirCodigo("fatiproduto2", codigoProduto);
	}

	public void setCFOP(String codigoCFOP) {
		inserirCodigo("faticfop2", codigoCFOP);
	}

	public void setQuantidadeItem(String quantidade) {
		inserirQuantidade("fatiqtde", quantidade);
	}

	public void setValorUnitarioItem(String valorUnitario) {
		inserirValor("fatiunitario", valorUnitario);
	}
	
}
