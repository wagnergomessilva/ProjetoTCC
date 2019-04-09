package steps.CT017;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class CT017_EmitirOrdemDeProducaoRegra19Steps {
	
	@Dado("^que Cadastro um configurador de Ordem de produção com a Regra (\\d+)$")
	public void queCadastroUmConfiguradorDeOrdemDeProduçãoComARegra(int arg1) throws Throwable {
	    
	}

	@E("^Cadastro a Ordem de Produção utilizando esta regra, informando o cliente de código (\\d+)$")
	public void cadastroAOrdemDeProduçãoUtilizandoEstaRegraInformandoOClienteDeCódigo(int arg1) throws Throwable {
	    
	}

	@E("^Adiciono o item de código (\\d+) com quantidade (\\d+)$")
	public void adicionoOItemDeCódigoComQuantidade(int arg1, int arg2) throws Throwable {
		
	}

	@Quando("^Finalizo esta a Ordem de Produção$")
	public void finalizoEstaAOrdemDeProdução() throws Throwable {
	    
	}

	@E("^Realizo as etpas do apontamento de produção$")
	public void realizoAsEtpasDoApontamentoDeProdução() throws Throwable {
	   
	}

	@Entao("^o sistema deve Movimentar o Estoque do produto (\\d+) com uma entrada de (\\d+) quantidades$")
	public void oSistemaDeveMovimentarOEstoqueDoProdutoComUmaEntradaDeQuantidades(int arg1, int arg2) throws Throwable {
	    
	}

	@E("^deve ter gerado o consumo dos insumos referente a primeira etapa do apontamento de produção\\.$")
	public void deveTerGeradoOConsumoDosInsumosReferenteAPrimeiraEtapaDoApontamentoDeProdução() throws Throwable {
	   
	}
}
