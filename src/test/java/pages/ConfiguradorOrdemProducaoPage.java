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
	
	public void clicarCheckboxRegra02() {
		clicarCheckBoxId("intconfgercfg2");
	}
	
	public void clicarCheckboxRegra03() {
		clicarCheckBoxId("intconfgercfg3");
	}
	
	public void clicarCheckboxRegra04() {
		clicarCheckBoxId("intconfgercfg4");
	}
	
	public void clicarCheckboxRegra07() {
		clicarCheckBoxId("intconfgercfg7");
	}
	
	public void clicarCheckboxRegra09() {
		clicarCheckBoxId("intconfgercfg9");
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

	public void clicarCheckboxRegra12() {
		clicarCheckBoxId("intconfgercfg12");		
	}
	
	public void clicarCheckboxRegra13() {
		clicarCheckBoxId("intconfgercfg13");		
	}
	
	public void clicarCheckboxRegra14() {
		clicarCheckBoxId("intconfgercfg14");		
	}
		
	public void clicarCheckboxRegra10() {
		clicarCheckBoxId("intconfgercfg10");		
	}
	
	public void clicarCheckboxRegra11() {
		clicarCheckBoxId("intconfgercfg11");		
	}
	
	public void clicarCheckboxRegra15() {
		clicarCheckBoxId("intconfgercfg15");		
	}
	
	public void clicarCheckboxRegra16() {
		clicarCheckBoxId("intconfgercfg16");		
	}
	
	public void clicarCheckboxRegra17() {
		clicarCheckBoxId("intconfgercfg17");		
	}
	
	public void clicarCheckboxRegra19() {
		clicarCheckBoxId("intconfgercfg19");		
	}
	
	public void clicarCheckboxRegra18() {
		clicarCheckBoxId("intconfgercfg18");		
	}

	public void clicarAbaMovtoEstoque() {
		clicarAba("itemaba4");		
	}

	public void clicarCheckBoxInsumoSemSaldo() {
		clicarCheckBoxId("bolinssemsalcfg");		
	}

}
