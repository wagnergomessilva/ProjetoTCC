package pages;

import static org.junit.Assert.assertEquals;

public class ConfiguradorOrdemProducaoPage extends BasePage {

	public void setDescricaoRegra(String descricao) {
		escrever("strdescrcfg", descricao);
	}

	public void setOrigemValue(String valor) {
		selecionarComboBox("intorigemcfg", valor);
	}

	public void setQuantidadeProduzirConfig(String valor) {
		selecionarComboBox("intqtproduzcfg", valor);
	}

	public void clicarBotaConfirmar() {
		clicarBotaId("btnSave");
	}

	public void validaAlertaSalvoComSucesso() {
		assertEquals("Salvo com sucesso.", alertaObterTextoEAceita());
	}

	public void clicarCheckboxRegra06() {
		clicarCheckBoxId("intconfgercfg6");
	}

	public void clicarAbaConfigIniciais() {
		clicarAba("itemaba2");

	}

	public void clicarAbaCadastro() {
		clicarAba("itemaba1");
	}

	public void clicarAbaCalculo() {
		clicarAba("itemaba6");
	}

	public void clicarAbaImpressao() {
		clicarAba("itemaba8");
	}

	public void validaAlertaPreencherCampos() {
		assertEquals("Preencha todos os campos requeridos.", alertaObterTextoEAceita());
	}

	public void setLayoutImpressaoOP(String valor) {
		selecionarComboBox("intordprodesp", valor);
	}

}
