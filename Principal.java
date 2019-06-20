import javafx.application.Application;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Principal extends Application {

	// Classes com as estruturas iniciais	
	private Layout layout;
	private Pagina pagina;

	// Classe para operar a classe Produto
	OperadorProduto operadorProduto;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		// Instancia Layout
		layout = new Layout(stage);

		// Carrega Página Inicial
		pagina = new InterfaceInicial(layout);

		// Instancia operador de Produtos
		operadorProduto = new OperadorProduto();

		// Navegação
		layout.btnInicial.setOnAction(e -> {
			pagina = new InterfaceInicial(layout);
		});

		layout.btnVender.setOnAction(e -> {
			pagina = new InterfaceVender(layout, operadorProduto);
		});

		layout.btnEstoque.setOnAction(e -> {
			pagina = new InterfaceEstoque(layout, operadorProduto);
		});

	}
}
