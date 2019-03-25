package pages;

import static core.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public void escrever(String id_campo, String texto) {
		getDriver().findElement(By.id(id_campo)).sendKeys(texto);
	}
	
	public void escreverEDarEnter(String id_campo, String texto) {
		getDriver().findElement(By.id(id_campo)).sendKeys(texto, Keys.ENTER);
	}

	public void clicarBotaId(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public void clicarBotaXpath(String xpath) {
		getDriver().findElement(By.xpath(xpath)).click();
	}

	public void clicarCheckBoxId(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public void clicarAba(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public void inserirCodigo(String id_campo, String texto) {
		getDriver().findElement(By.id(id_campo)).sendKeys(texto);
	}

	public void inserirQuantidade(String id_campo, String texto) {
		getDriver().findElement(By.id(id_campo)).sendKeys(texto);
	}

	public void inserirValor(String id_campo, String texto) {
		getDriver().findElement(By.id(id_campo)).sendKeys(texto);
	}

	public void buscaTelasSistemaClicar(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public void clicarNoResultadoDaPesquisa(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public void alternarFocoJanela(int numeroJanela) {
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[numeroJanela]);
	}

	public void selecionarComboBox(String id, String valor) {
		WebElement elemento = getDriver().findElement(By.id(id));
		Select combobox = new Select(elemento);
		combobox.selectByValue(valor);
	}

	public String alertaObterTextoEAceita() {
		Alert alerta = getDriver().switchTo().alert();
		String valor = alerta.getText();
		alerta.accept();
		return valor;
	}

	public String alertaObterTextoECancela() {
		Alert alerta = getDriver().switchTo().alert();
		String valor = alerta.getText();
		alerta.dismiss();
		return valor;
	}

	public void esperaFixa(int tempoMilesegundos) throws InterruptedException {
		Thread.sleep(tempoMilesegundos);
	}

	public void esperaImplicita(int segundos) {
		getDriver().manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
	}

	public void esperaExplicita(String id) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
	}

	public String getCodigo(By by) {
		return getDriver().findElement(by).getAttribute("value");
	}
}
