package testesWeb;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestandoBlogTest {
	private WebDriver driver;
	private WebDriverWait espera;
	private TestandoBlog paginaBlog;
	
	@Before
	public void funcaoQueVaiExecutarAntesDoTest(){

		System.setProperty("webdriver.chrome.driver","C:\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		espera = new WebDriverWait(driver, Duration.ofSeconds(10));
		paginaBlog = new TestandoBlog(driver,espera);
	}
	
	@Test
	public void deveApresentarComentario() {
		paginaBlog.abrirSite();
		paginaBlog.encontrarOpcaoComentario();
		paginaBlog.preencherCampoComentario("Olá, testando.");
		paginaBlog.preencherCampoNome("Testando");
		paginaBlog.preencherCampoEmail("teste@teste.com.br");
		paginaBlog.publicarComentario();
		paginaBlog.esperarTexto("0 Comments");
		
		assertTrue(driver.getPageSource().contains("0 Comments"));
		
	}
	@Test
	public void deveSolicitarPreenchimentoDosCampos() {
		paginaBlog.abrirSite();
		paginaBlog.encontrarOpcaoComentario();
		paginaBlog.publicarComentario();
		paginaBlog.esperarTexto("Preencha este campo");
		
		assertTrue(driver.getPageSource().contains("Preencha este campo"));
	}
	
	@Test
	public void deveApresentarMensagemDeErroComentarioNumerico() {
		paginaBlog.abrirSite();
		paginaBlog.encontrarOpcaoComentario();
		paginaBlog.preencherCampoComentario("O215115151");
		paginaBlog.preencherCampoNome("Testando");
		paginaBlog.preencherCampoEmail("teste@teste.com.br");
		paginaBlog.publicarComentario();
		
		paginaBlog.esperarTexto("Preenchimento inválido");
		
		assertTrue(driver.getPageSource().contains("Preenchimento inválido"));
	
	}
	
	@Test
	public void deveApresentarMensagemDeErroNomeNumerico() {
		paginaBlog.abrirSite();
		paginaBlog.encontrarOpcaoComentario();
		paginaBlog.preencherCampoComentario("Ola.");
		paginaBlog.preencherCampoNome("551515151");
		paginaBlog.preencherCampoEmail("teste@teste.com.br");
		paginaBlog.publicarComentario();
		paginaBlog.esperarTexto("Preenchimento inválido");
		
		assertTrue(driver.getPageSource().contains("Preenchimento inválido"));
	}
	
	@Test
	public void deveApresentarMensagemDeErroEmailInvalido() {
		paginaBlog.abrirSite();
		paginaBlog.encontrarOpcaoComentario();
		paginaBlog.preencherCampoComentario("Ola.");
		paginaBlog.preencherCampoNome("Testando");
		paginaBlog.preencherCampoEmail("teste_teste");
		paginaBlog.publicarComentario();
		
		paginaBlog.esperarTexto("Erro: Digite um endereço de e-mail válido.");
		
		assertTrue(driver.getPageSource().contains("Erro: Digite um endereço de e-mail válido."));
	}
	
	@After
	public void funcaoQueVaiExecutarDepoisdoTest() {
		driver.quit();
	}
}
