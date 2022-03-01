package testesWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestandoBlog {
	private WebDriver driver;
	private WebDriverWait espera;
	
	public TestandoBlog(WebDriver driver,WebDriverWait espera) {
		this.driver=driver;
		this.espera = espera;
	}
	
	public void abrirSite() {
		driver.get("https://blog.morrodesaopaulo.com.br/reveillon-amare-no-morro-de-sao-paulo/");
	}
	
	public void encontrarOpcaoComentario() {
		WebElement comentario = driver.findElement(By.xpath("//*[@id=\"theChampTabs-1-li\"]"));
		comentario.click();
	}
	
	public void preencherCampoComentario(String comentario) {
		WebElement campoComentario = driver.findElement(By.xpath("//*[@id=\"comment\"]"));
		campoComentario.sendKeys(comentario);
	}
	public void preencherCampoNome(String nome) {
		WebElement campoNome = driver.findElement(By.xpath("//*[@id=\"author\"]"));
		campoNome.sendKeys(nome);
	}
	
	public void preencherCampoEmail(String email) {
		WebElement campoEmail = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		campoEmail.sendKeys(email);
	}
	
	public void publicarComentario() {
		WebElement botaoComentario = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
		botaoComentario.click();
	}
	public void esperarTexto(String texto) {
		espera.until( dv -> dv.getPageSource().contains(texto) );
	}

}

