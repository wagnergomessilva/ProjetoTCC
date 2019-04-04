package pages;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.Propriedades;

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

	public String obterValorComXpath(String xpath) {
		return getDriver().findElement(By.xpath(xpath)).getText();
	}

	public String obterValorComId(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
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

	public void esperaExplicita(String id) { // método que espera até que determinado elemento seja clicável na tela
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.id(id))));
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));

	}

	public void esperaTelaCarregar() {
		WebDriver driver = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		wait.until(pageLoadCondition);
	}

	public String getCodigo(By by) {
		return getDriver().findElement(by).getAttribute("value");
	}

	public String getData(By by) {
		return getDriver().findElement(by).getAttribute("value");
	}

	public void validaAlertaPreencherCampos() {
		assertEquals("Preencha todos os campos requeridos.", alertaObterTextoEAceita());
	}

	public void finalizarNavegador() {
		if (Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
	
	public void screenshotTela(String casoTeste) throws IOException, HeadlessException, AWTException {
		/*TakesScreenshot screenshot = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo,
				new File("target" + File.separator + "screenshot" + File.separator + casoTeste + ".jpg"));*/
		BufferedImage screenchot = new Robot().createScreenCapture(new Rectangle(java.awt.Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(screenchot, "jpg", new File("target/screenshot/"+casoTeste+".jpg"));
	}

}
