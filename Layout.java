import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

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
	public Button btnVender;
	public Button btnEstoque;

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

	private void barraLateral() {
		barraLateral = new VBox();
		barraLateral.getStyleClass().add("barraLateral");
	}

	private void logo() {
		ImageView logo = new ImageView();
		logo.setImage(new Image("img/logo.png"));
		logo.getStyleClass().add("logo");
		logo.setPreserveRatio(true);
		barraLateral.getChildren().add(logo);
	}

	private void menu() {
		menu = new VBox();
		menu.getStyleClass().add("menu");

		btnInicial = new Button(InterfaceInicial.titulo, new ImageView(InterfaceInicial.icone));
		btnInicial.getStyleClass().add("menuBotao");
		menu.getChildren().add(btnInicial);

		btnVender = new Button(InterfaceVender.titulo, new ImageView(InterfaceVender.icone));
		btnVender.getStyleClass().add("menuBotao");
		menu.getChildren().add(btnVender);

		btnEstoque = new Button(InterfaceEstoque.titulo, new ImageView(InterfaceEstoque.icone));
		btnEstoque.getStyleClass().add("menuBotao");
		menu.getChildren().add(btnEstoque);

		barraLateral.getChildren().add(menu);
	}

	private void cabecalho() {
		cabecalho = new HBox();
		cabecalho.getStyleClass().add("cabecalho");
	}

	private void titulo() {
		titulo = new Text("Título");
		titulo.getStyleClass().add("titulo");
		cabecalho.getChildren().add(titulo);
	}

	private void conteudo() {
		conteudo = new VBox();
		conteudo.getStyleClass().add("conteudo");
	}

	private void corpo() {
		corpo = new BorderPane();
		corpo.setTop(cabecalho);
		corpo.setCenter(conteudo);
	}

	private void principal() {
		principal = new BorderPane();
		principal.setLeft(barraLateral);
		principal.setCenter(corpo);
	}

	// Cena do aplicativo
	private void cena() {
		cena = new Scene(principal, 900, 600);
		cena.getStylesheets().add("styles.css");
		stage.setTitle("SysBanca");
		stage.setScene(cena);
		stage.getIcons().add(new Image("file:img/icon.png"));
		stage.show();
	}

}