import javafx.application.Application;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
* Classe de inicialização da interface e instanciamento das demais classes.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class Principal extends Application {

	// Classes com as estruturas iniciais	
	private Layout layout;
	private Pagina pagina;

	// Classe para operar as vendas
	private OperadorVendas operadorVendas;

	// Classe para operar o estoque
	private OperadorEstoque operadorEstoque;

	/**
	* Main function - Executa a interface.
	* @param args Argumentos da Main.
	*/

	public static void main(String[] args) {
		launch(args);
	}

	/**
	* Ao iniciar a janela, executa procedimentos iniciais.
	* @param stage Estado da página.
	*/

	@Override
	public void start(Stage stage) {
		
		// Instancia Layout
		layout = new Layout(stage);

		// Instancia operador de vendas
		operadorVendas = new OperadorVendas();

		// Instancia operador de produtos
		operadorEstoque = new OperadorEstoque();

		// Carrega Página Inicial
		pagina = new InterfaceInicial(layout, operadorVendas);

		// Navegação
		layout.btnInicial.setOnAction(e -> {
			pagina = new InterfaceInicial(layout, operadorVendas);
		});

		layout.btnCaixa.setOnAction(e -> {
			pagina = new InterfaceCaixa(layout, operadorVendas, operadorEstoque);
		});

		layout.btnEstoque.setOnAction(e -> {
			pagina = new InterfaceEstoque(layout, operadorEstoque);
		});

		layout.btnVendas.setOnAction(e -> {
			pagina = new InterfaceVendas(layout, operadorVendas);
		});

		layout.btnConfiguracoes.setOnAction(e -> {
			pagina = new InterfaceConfiguracoes(layout);
		});

	}

	/**
	* Ao fechar a página, salva dados para persistência.
	*/

	@Override
	public void stop(){
		operadorVendas.salvar();
		operadorEstoque.salvar();
	}
}
