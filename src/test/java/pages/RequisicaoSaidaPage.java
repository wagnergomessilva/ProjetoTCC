package pages;

public class RequisicaoSaidaPage extends BasePage{

	public void clicarBotaPesquisarRequisicao() {
		clicarBotaId("btnPesquisar");
	}

	public void setComentario(String comentario) {
		escrever("rdnarrativa", comentario);		
	}

	public void clicarBotaEditarRequisicao() {
		clicarBotaXpath("//table//tbody//td[8]/img");		
	}

	public void setDataPeriodo01(String data) {
		escrever("de", data);		
	}

	public void setDataPeriodo02(String data) {
		escrever("ate", data);		
	}
	
}
