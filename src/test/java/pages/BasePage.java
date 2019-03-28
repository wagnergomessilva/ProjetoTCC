package pages;

import static core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DriverFactory;

public class BasePage {
	
	@Rule
	public TestName testnome = new TestName(); 

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
	
	public String obterValorComXpath(String xpath) {
		return getDriver().findElement(By.xpath(xpath)).getText();
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
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
		//wait.until(ExpectedConditions.textToBe(By.id(id), texto));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[1]/div/img")));
		
	}

	public String getCodigo(By by) {
		return getDriver().findElement(by).getAttribute("value");
	}
	
	public void capturaScreenchot(String nomeCT) throws IOException { 
		
		TakesScreenshot screenshot = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("target"+File.separator+"screenshot"+File.separator + nomeCT  + ".jpg"));
	}
}
