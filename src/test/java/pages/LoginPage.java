package pages;

import static core.DriverFactory.getDriver;

public class LoginPage extends BasePage{
	
	public void acessarTelaSistema() {
		getDriver().get("http://localhost/csp/erpwebmais/system/www/index.csp?lf=1"); 
	}
	
	public void setUsuario(String usuario) {
		escrever("UserName", usuario);
	}
	
	public void setSenha(String senha) {
		escrever("Password", senha);
	}
	
	public void entrarNoSistema() {
		clicarBotaId("botaoLogin");
	}
	
}
