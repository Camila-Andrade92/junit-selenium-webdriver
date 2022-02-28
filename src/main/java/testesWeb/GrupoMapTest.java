package testesWeb;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GrupoMapTest {
	private WebDriver driver;
	private WebDriverWait espera;
	private GrupoMapPage paginaGrupoMap;
	
	@Before
	public void funcaoQueVaiExecutarAntesDoTest(){

		System.setProperty("webdriver.chrome.driver","C:\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		espera = new WebDriverWait(driver, Duration.ofSeconds(10));
		paginaGrupoMap = new GrupoMapPage(driver,espera);
	
	}
	
	@Test
	public void deveApresentarMsgSucesso() {
		paginaGrupoMap.abrirSite();
		paginaGrupoMap.escolherOpcaoOrcamento();
		paginaGrupoMap.preencherCampoNome("Teste");
		paginaGrupoMap.preencherCampoSobrenome("Sobrenome Teste");
		paginaGrupoMap.preencherCampoEmail("teste@gmail.com");
		paginaGrupoMap.preencherCampoTelefone("11988526654");
		paginaGrupoMap.preencherCampoMensagem("Olá!");
		paginaGrupoMap.clicarNoBotaoEnviar();
		
		paginaGrupoMap.esperarTexto("A mensagem foi enviada com sucesso");
		
		assertTrue(driver.getPageSource().contains("A mensagem foi enviada com sucesso"));
	}
	
	@After
	public void funcaoQueVaiExecutarDepoisdoTest() {
		driver.close();
	}
}

