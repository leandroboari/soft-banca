import javafx.application.Application;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Principal extends Application {

	// Classes com as estruturas iniciais	
	private Layout layout;
	private Pagina pagina;

	// Classe para operar as vendas
	private OperadorVenda operadorVenda;

	// Classe para operar o estoque
	private OperadorProduto operadorProduto;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		// Instancia Layout
		layout = new Layout(stage);

		// Carrega Página Inicial
		pagina = new InterfaceInicial(layout);

		// Instancia operador de vendas
		operadorVenda = new OperadorVenda();

		// Instancia operador de produtos
		operadorProduto = new OperadorProduto();

		// Navegação
		layout.btnInicial.setOnAction(e -> {
			pagina = new InterfaceInicial(layout);
		});

		layout.btnVender.setOnAction(e -> {
			pagina = new InterfaceVender(layout, operadorVenda, operadorProduto);
		});

		layout.btnEstoque.setOnAction(e -> {
			pagina = new InterfaceEstoque(layout, operadorProduto);
		});

	}
}
