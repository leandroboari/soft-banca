import javafx.application.Application;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
* Descrição da classe.
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	// Classe principal
	public static void main(String[] args) {
		launch(args);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	@Override
	public void start(Stage stage) {
		
		// Instancia Layout
		layout = new Layout(stage);

		// Carrega Página Inicial
		pagina = new InterfaceInicial(layout, operadorVendas);

		// Instancia operador de vendas
		operadorVendas = new OperadorVendas();

		// Instancia operador de produtos
		operadorEstoque = new OperadorEstoque();

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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	@Override
	public void stop(){
		operadorVendas.salvar();
		operadorEstoque.salvar();
	}
}
