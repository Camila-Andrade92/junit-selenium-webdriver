package testesWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GrupoMapPage {
		private WebDriver driver;
		private WebDriverWait espera;
		
		public GrupoMapPage(WebDriver driver, WebDriverWait espera) {
			this.driver=driver;
			this.espera = espera;
		}
		
		public void abrirSite() {
			driver.get("https://grupomap.com.br/fale-conosco/");
			//esperar até mostrar logo inicial a esquerda
			espera.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"logo\"]/img[3]")));
		}
		public void clicarNoBotaoSolicitarOrcamento() {
			WebElement botaoOrcamento = driver.findElement(By.xpath("//*[@id=\"Top_bar\"]/div/div/div[2]/div/a"));
			botaoOrcamento.click();
			espera.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wpcf7-f393-p15-o1\"]/form/div[2]/p")));
		}
		
		public void escolherOpcaoOrcamento() {
			WebElement select = driver.findElement(By.xpath("//*[@id=\"wpcf7-f393-p15-o1\"]/form/div[2]/p/span/select"));
			Select selecionarOPorcamento = new Select(select);
			selecionarOPorcamento.selectByVisibleText("Setor Comercial - Orçamento / Outros assuntos");	

		}
		public void preencherCampoNome(String nome) {
			WebElement campoNome = driver.findElement(By.xpath("//*[@id=\"wpcf7-f393-p15-o1\"]/form/div[3]/span/input"));
			campoNome.sendKeys(nome);
		}
		public void preencherCampoSobrenome(String nome) {
			WebElement campoSobrenome = driver.findElement(By.xpath("//*[@id=\"wpcf7-f393-p15-o1\"]/form/div[4]/span/input"));
			campoSobrenome.sendKeys(nome);
		}
		public void preencherCampoEmail(String email) {
			WebElement campoEmail = driver.findElement(By.xpath("//*[@id=\"wpcf7-f393-p15-o1\"]/form/div[5]/span/input"));
			campoEmail.sendKeys(email);
		}
		public void preencherCampoTelefone(String numero) {
			WebElement campoTelefone = driver.findElement(By.xpath("//*[@id=\"wpcf7-f393-p15-o1\"]/form/div[6]/span/input"));
			campoTelefone.sendKeys(numero);
		}
		public void preencherCampoMensagem(String nome) {
			WebElement campoMensagem = driver.findElement(By.xpath("//*[@id=\"wpcf7-f393-p15-o1\"]/form/div[7]/span/textarea"));
			campoMensagem.sendKeys(nome);
		}
		public void clicarNoBotaoEnviar() {
			WebElement botaoEnviar = driver.findElement(By.xpath("//*[@id=\"wpcf7-f393-p15-o1\"]/form/div[8]/input"));
			botaoEnviar.click();
			
			
			
		}
		
		public void esperarTexto(String texto) {
			espera.until( dv -> dv.getPageSource().contains(texto) );
		}
}

