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
		String endereco=("Avenida Bela Vista, n 20.");
		String email=("testejo@teste.com");
		String telefone=("79988635587");
		paginaCrud.abrirSite();
		paginaCrud.adicionarCliente();
		paginaCrud.preencherCampoNome(nome);
		paginaCrud.preencherCampoEndereco(endereco);
		paginaCrud.preencherCampoEmail(email);
		paginaCrud.preencherCampoTelefone(telefone);
		paginaCrud.escolherSexoFeminino();
		paginaCrud.enviarFormulario();
		paginaCrud.botaoVoltarParaLista();
	
		paginaCrud.esperarTexto(nome);
		assertTrue(driver.getPageSource().contains(nome));
		assertTrue(driver.getPageSource().contains(endereco));
		assertTrue(driver.getPageSource().contains(email));
		assertTrue(driver.getPageSource().contains(telefone));
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
	public void deveExcluirCadastro() {
		String nome=("Teste de exclusão");
		String endereco=("Avenida exclusão");
		String email=("testeexclusao@teste.com");
		String telefone=("79988635587");
		paginaCrud.abrirSite();
		paginaCrud.adicionarCliente();
		paginaCrud.preencherCampoNome(nome);
		paginaCrud.preencherCampoEndereco(endereco);
		paginaCrud.preencherCampoEmail(email);
		paginaCrud.preencherCampoTelefone(telefone);
		paginaCrud.escolherSexoFeminino();
		paginaCrud.enviarFormulario();
		paginaCrud.botaoVoltarParaLista();
	
		paginaCrud.esperarTexto(nome);
		assertTrue(driver.getPageSource().contains(nome));
		assertTrue(driver.getPageSource().contains(endereco));
		assertTrue(driver.getPageSource().contains(email));
		assertTrue(driver.getPageSource().contains(telefone));
		
		paginaCrud.excluirCliente(nome);

		paginaCrud.esperarTexto("CRUD de Pessoas");
		assertFalse(driver.getPageSource().contains(nome));
	
	}
		
	@Test
	public void atualizarCadastro() {
		String id="69";
		String nome= "Camila Andrade Gomes";
		paginaCrud.abrirSite();
		paginaCrud.atualizarDadosCliente(id);
		paginaCrud.preencherCampoNome(nome);
		paginaCrud.botaoAtualizarCadastro();
		
		
		paginaCrud.esperarTexto("CRUD de Pessoas");
		
		String nomeCorrespondenteId = paginaCrud.retornarNomePorId(id);
		assertEquals( nomeCorrespondenteId ,nome );
	}
		
	@Test
	public void deveMostrarInformacoesDoCadastro() {
		String nome= "Joaquim Andrade Gomes";
		paginaCrud.abrirSite();
		paginaCrud.visualizarInformacaoCliente(nome);
	
		
		paginaCrud.esperarTexto("CRUD de Pessoas");
		assertTrue(driver.getPageSource().contains(nome));	
		
	}
	
	@Test
	public void naoDeveRealizarCadastroNomeComCaracterNumerico(){
		String nome= ("1551165");
		paginaCrud.abrirSite();
		paginaCrud.adicionarCliente();
		paginaCrud.preencherCampoNome(nome);
		paginaCrud.preencherCampoEndereco("Avenida Bela Vista, n 20.");
		paginaCrud.preencherCampoEmail("teste@teste.com");
		paginaCrud.preencherCampoTelefone("71988635587");
		paginaCrud.escolherSexoFeminino();
		paginaCrud.enviarFormulario();
		paginaCrud.botaoVoltarParaLista();
		
		paginaCrud.esperarTexto("Campo nome com caracteres inválidos");
		assertTrue(driver.getPageSource().contains("Campo nome com caracteres inválidos"));
		assertFalse(driver.getPageSource().contains(nome));
	}
	
	@Test
	public void naoDeveRealizarCadastroEmailInvalido() {
		String email="teste_152_teste.com";
		paginaCrud.abrirSite();
		paginaCrud.adicionarCliente();
		paginaCrud.preencherCampoNome("Testando Endereço Numérico");
		paginaCrud.preencherCampoEndereco("Avenida Bela Vista, n 20.");
		paginaCrud.preencherCampoEmail(email);
		paginaCrud.preencherCampoTelefone("71988635587");
		paginaCrud.escolherSexoFeminino();
		paginaCrud.enviarFormulario();
		paginaCrud.botaoVoltarParaLista();	
		
		paginaCrud.esperarTexto("Por favor digite um endereço de email válido!");
		assertTrue(driver.getPageSource().contains("Por favor digite um endereço de email válido!"));
		assertFalse(driver.getPageSource().contains(email));
	}
	@Test
	public void naoDeveRealizarCadastroTelefoneCaracteresAlfabeticos() {
		String telefone="tele-fonee";
		paginaCrud.abrirSite();
		paginaCrud.adicionarCliente();
		paginaCrud.preencherCampoNome("Testando Telefone Alfabético");
		paginaCrud.preencherCampoEndereco("Avenida Bela Vista, n 20.");
		paginaCrud.preencherCampoEmail("telefone@alfabetico.com");
		paginaCrud.preencherCampoTelefone(telefone);
		paginaCrud.escolherSexoFeminino();
		paginaCrud.enviarFormulario();
		paginaCrud.botaoVoltarParaLista();	
		
		paginaCrud.esperarTexto("Campo nome com caracteres inválidos");
		assertTrue(driver.getPageSource().contains("Campo nome com caracteres inválidos"));
		assertFalse(driver.getPageSource().contains(telefone));
	}
	
	
	@After
	public void funcaoQueVaiExecutarDepoisdoTest() {
		driver.quit();
	}
}
