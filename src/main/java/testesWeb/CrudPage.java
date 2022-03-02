package testesWeb;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrudPage {
	private WebDriver driver;
	private WebDriverWait espera;
	
	public CrudPage(WebDriver driver, WebDriverWait espera) {
		this.driver=driver;
		this.espera = espera;
	}
	
	public void abrirSite() {
		driver.get("https://camila.zeludigital.com/index.php");
		espera.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]/p/a")));
	}
	public void adicionarCliente() {
		WebElement botaoAdicionar = driver.findElement(By.xpath("/html/body/div/div[2]/p/a"));
		botaoAdicionar.click();
	}
	public void preencherCampoNome(String nome) {
		WebElement campoNome = driver.findElement(By.xpath("//html/body/div/div/div/div[2]/form/div[1]/div/input"));
		campoNome.clear();
		campoNome.sendKeys(nome);
	}
	public void preencherCampoEndereco(String nome) {
		WebElement campoEndereco = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[2]/div/input"));
		campoEndereco.sendKeys(nome);
	}
	public void preencherCampoTelefone(String numero) {
		WebElement campoTelefone = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[3]/div/input"));
		campoTelefone.sendKeys(numero);

	}
	
	public void preencherCampoEmail(String nome) {
		WebElement campoEmail = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[4]/div/input"));
		campoEmail.sendKeys(nome);
	}
	
	public void escolherSexoMasculino() {
		WebElement sexoMasculino = driver.findElement(By.xpath("//input[@type=\"radio\"][@value=\"M\"]"));
		sexoMasculino.click();
	}
	
	public void escolherSexoFeminino() {
		WebElement sexoFeminino = driver.findElement(By.xpath("//input[@type=\"radio\"][@value=\"F\"]"));
		sexoFeminino.click();
	}
	
	public void enviarFormulario () {
		WebElement botaoEnviar = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[6]/button"));
		botaoEnviar.click();
	}
	
	public void botaoVoltarParaLista () {
		WebElement botaoVoltar = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[6]/a"));
		botaoVoltar.click();
	}
	public void excluirCliente (String nome) {
		List<WebElement> linhasDaTabela = driver.findElements(By.xpath("/html/body/div/div[2]/table/tbody/tr"));
		WebElement botaoCorrespondenteAoNome;
		
		for( WebElement linha : linhasDaTabela ) {
			
			WebElement colunaNome = linha.findElement(By.xpath("./td[1]"));
			
			if( colunaNome.getText().equals(nome) ) {
				botaoCorrespondenteAoNome = linha.findElement(By.xpath("./td[6]/a[3]"));
				
				botaoCorrespondenteAoNome.click();
				
				esperarTexto("Deseja excluir o contato?");

				WebElement botaoSim = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/button"));
				botaoSim.click();
				
				return ;
			}	
		}	
	}
	
	public void visualizarInformacaoCliente(String nome) {
		List<WebElement> linhasDaTabela = driver.findElements(By.xpath("/html/body/div/div[2]/table/tbody/tr"));
		WebElement botaoCorrespondenteAoInfo;
		
		for(WebElement linha : linhasDaTabela) {
			
			WebElement colunaNome = linha.findElement(By.xpath("./td[1]"));
			
			if (colunaNome.getText().equals(nome)) {
				botaoCorrespondenteAoInfo = linha.findElement(By.xpath("./td[6]/a[1]"));
				
				botaoCorrespondenteAoInfo.click();
				
				esperarTexto("Informações do Contato");
				WebElement botaoVoltar = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[6]/a"));
				botaoVoltar.click();
				
				break;
			
			}
		}
	}

	
	public void atualizarDadosCliente(String id) {
		List<WebElement> linhasDaTabela =  driver.findElements(By.xpath("/html/body/div/div[2]/table/tbody/tr"));
		WebElement botaoCorrespondenteAoID;
		
		for(WebElement linha : linhasDaTabela) {
			
			WebElement colunaID = linha.findElement(By.xpath("./th[1]"));
			
			if (colunaID.getText().equals(id)){
				botaoCorrespondenteAoID = linha.findElement(By.xpath("./td[6]/a[2]"));
				botaoCorrespondenteAoID.click();
				
			esperarTexto("Atualizar Contato");
			
			
			break;
			}
		}
	}
	public void botaoAtualizarCadastro()  {
		WebElement botaoAtualizar = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[6]/button"));
		botaoAtualizar.click();
	
		}
		
	public String retornarNomePorId(String id) {
		String nome;
		List<WebElement> linhasDaTabela =  driver.findElements(By.xpath("/html/body/div/div[2]/table/tbody/tr"));
		WebElement nomeCorrespondetenAoId;
		
		for(WebElement linha : linhasDaTabela) {
			WebElement colunaID = linha.findElement(By.xpath("./th[1]"));
			
			if (colunaID.getText().equals(id)) {
				nomeCorrespondetenAoId = linha.findElement(By.xpath("./td[1]"));
				
				nome=nomeCorrespondetenAoId.getText();
				return nome;
				
			}
		}
		return null;
	}

	public void esperarTexto(String texto) {
		espera.until( dv -> dv.getPageSource().contains(texto) );
	}
}

