package pages;

import static core.DriverFactory.killDriver;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;

import core.Propriedades;

public class BaseTest {

	private LoginPage page = new LoginPage();

	@Before
	public void loginSistema() {

		page.acessarTelaSistema();
		page.setUsuario("robohom");
		page.setSenha("robo123");
		page.entrarNoSistema();

	}
	
	@After
	public void finaliza() throws IOException {
		if (Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
}
