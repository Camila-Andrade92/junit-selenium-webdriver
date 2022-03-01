package testesWeb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrudTest {
	private WebDriver driver;
	private WebDriverWait espera;
	private CrudPage paginaCrud;
	
	@Before
	public void funcaoQueVaiExecutarAntesDoTest(){

		System.setProperty("webdriver.chrome.driver","C:\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();
		espera = new WebDriverWait(driver, Duration.ofSeconds(10));
		paginaCrud = new CrudPage(driver,espera);
	}
	
	@Test
	public void deveRealizarCadastroDeCliente() {
		
		String nome=("Joaquim Andrade Gomes");
		paginaCrud.abrirSite();
		paginaCrud.adicionarCliente();
		paginaCrud.preencherCampoNome(nome);
		paginaCrud.preencherCampoEndereco("Avenida Bela Vista, n 20.");
		paginaCrud.preencherCampoEmail("teste@teste.com");
		paginaCrud.preencherCampoTelefone("71988635587");
		paginaCrud.escolherSexoFeminino();
		paginaCrud.enviarFormulario();
		paginaCrud.botaoVoltarParaLista();
	
		paginaCrud.esperarTexto(nome);
		assertTrue(driver.getPageSource().contains(nome));
	}
	
	@Test
	public void naoDeveRealizarCadastroCamposNaoPreenchidos() {
		paginaCrud.abrirSite();
		paginaCrud.adicionarCliente();
		paginaCrud.enviarFormulario();
		
		paginaCrud.esperarTexto("Por favor digite");
		assertTrue(driver.getPageSource().contains("Por favor digite"));
	}
@Test
	public void naoDeveRealizarCadastroNomeComCaracterNumerico(){
	paginaCrud.abrirSite();
	paginaCrud.adicionarCliente();
	paginaCrud.preencherCampoNome("1551165");
	paginaCrud.preencherCampoEndereco("Avenida Bela Vista, n 20.");
	paginaCrud.preencherCampoEmail("teste@teste.com");
	paginaCrud.preencherCampoTelefone("71988635587");
	paginaCrud.escolherSexoFeminino();
	paginaCrud.enviarFormulario();
	paginaCrud.botaoVoltarParaLista();
	
	paginaCrud.esperarTexto("Campo nome com caracteres inválidos");
	assertTrue(driver.getPageSource().contains("Campo nome com caracteres inválidos"));

	
	}

	@Test
	public void deveExcluirCadastro() {
		String nome="Joaquim Andrade Gomes";
		paginaCrud.abrirSite();
		paginaCrud.excluirCliente(nome);
		
		paginaCrud.esperarTexto("CRUD de Pessoas");
		assertFalse(driver.getPageSource().contains(nome));
		
	}
		
	@Test
	public void atualizarCadastro() {
		String id="45";
		String nome= "Camila Andrade Gomes";
		paginaCrud.abrirSite();
		paginaCrud.atualizarDadosCliente(id);
		paginaCrud.preencherCampoNome(nome);
		paginaCrud.botaoAtualizarCadastro();
		
		
		paginaCrud.esperarTexto("CRUD de Pessoas");
		
		String nomeCorrespondenteId = paginaCrud.retornarNomePorId(id);
		assertEquals( nomeCorrespondenteId ,nome );
	}
		
	@After
	public void funcaoQueVaiExecutarDepoisdoTest() {
		driver.quit();
	}
}
