package pages;

public class KardexPage extends BasePage{

	public void setProdutoConsulta(String codProduto) {
		escrever("ctlnprod2", codProduto);		
	}
	
	public void clicarBotaoPesquisarKardex() {
		clicarBotaId("btnPesquisar");
	}
	
}
