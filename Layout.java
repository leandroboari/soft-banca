import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

/**
* Classe com os elementos que compõem o layout base do sistema.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class Layout {

	// Janela
	private Scene cena;
	private Stage stage;

	// Layout Base
	public Text titulo;
	private VBox barraLateral;
	private VBox menu;
	private HBox cabecalho;
	public VBox conteudo;
	private BorderPane corpo;
	private BorderPane principal;

	// Botões Menu
	public Button btnInicial;
	public Button btnCaixa;
	public Button btnEstoque;
	public Button btnVendas;
	public Button btnConfiguracoes;

	/**
	* Construtor principal da classe.
	* @param stage stage da interface.
	*/

	public Layout(Stage stage) {
		this.stage = stage;
		barraLateral();
		logo();
		menu();
		cabecalho();
		titulo();
		conteudo();
		corpo();
		principal();
		cena();
	}

	/**
	* Barra lateral esquerda contendo o menu.
	*/

	private void barraLateral() {
		barraLateral = new VBox();
		barraLateral.getStyleClass().add("barraLateral");
	}

	/**
	* Logomarca do sistema.
	*/

	private void logo() {
		ImageView logo = new ImageView();
		logo.setImage(new Image("img/logo.png"));
		logo.getStyleClass().add("logo");
		logo.setPreserveRatio(true);
		barraLateral.getChildren().add(logo);
	}

	/**
	* Listagem de botões de navegação.
	*/

	private void menu() {
		menu = new VBox();
		menu.getStyleClass().add("menu");

		btnInicial = new Button(InterfaceInicial.titulo, new ImageView(InterfaceInicial.icone));
		btnInicial.getStyleClass().add("menuBotao");
		menu.getChildren().add(btnInicial);

		btnCaixa = new Button(InterfaceCaixa.titulo, new ImageView(InterfaceCaixa.icone));
		btnCaixa.getStyleClass().add("menuBotao");
		menu.getChildren().add(btnCaixa);

		btnEstoque = new Button(InterfaceEstoque.titulo, new ImageView(InterfaceEstoque.icone));
		btnEstoque.getStyleClass().add("menuBotao");
		menu.getChildren().add(btnEstoque);

		btnVendas = new Button(InterfaceVendas.titulo, new ImageView(InterfaceVendas.icone));
		btnVendas.getStyleClass().add("menuBotao");
		menu.getChildren().add(btnVendas);

		btnConfiguracoes = new Button(InterfaceConfiguracoes.titulo, new ImageView(InterfaceConfiguracoes.icone));
		btnConfiguracoes.getStyleClass().add("menuBotao");
		menu.getChildren().add(btnConfiguracoes);

		barraLateral.getChildren().add(menu);
	}

	/**
	* Cabeçalho do conteúdo da interface.
	*/

	private void cabecalho() {
		cabecalho = new HBox();
		cabecalho.getStyleClass().add("cabecalho");
	}

	/**
	* Título da página.
	*/

	private void titulo() {
		titulo = new Text("Título");
		titulo.getStyleClass().add("titulo");
		cabecalho.getChildren().add(titulo);
	}

	/**
	* Conteúdo onde serão exibidas as páginas.
	*/

	private void conteudo() {
		conteudo = new VBox();
		conteudo.getStyleClass().add("conteudo");
	}

	/**
	* Constrói corpo do conteudo.
	*/

	private void corpo() {
		corpo = new BorderPane();
		corpo.setTop(cabecalho);
		corpo.setCenter(conteudo);
	}

	/**
	* Agrupagem de barra lateral e conteúdo.
	*/

	private void principal() {
		principal = new BorderPane();
		principal.setLeft(barraLateral);
		principal.setCenter(corpo);
	}

	/**
	* Inicia a janela.
	*/

	private void cena() {
		cena = new Scene(principal, 900, 600);
		cena.getStylesheets().add("styles.css");
		stage.setTitle("SysBanca");
		stage.setScene(cena);
		stage.getIcons().add(new Image("file:img/icon.png"));
		stage.show();
	}

}