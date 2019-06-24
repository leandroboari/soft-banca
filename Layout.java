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
	public Button btnCaixa;
	public Button btnEstoque;
	public Button btnVendas;

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	private void barraLateral() {
		barraLateral = new VBox();
		barraLateral.getStyleClass().add("barraLateral");
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	private void logo() {
		ImageView logo = new ImageView();
		logo.setImage(new Image("img/logo.png"));
		logo.getStyleClass().add("logo");
		logo.setPreserveRatio(true);
		barraLateral.getChildren().add(logo);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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

		barraLateral.getChildren().add(menu);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	private void cabecalho() {
		cabecalho = new HBox();
		cabecalho.getStyleClass().add("cabecalho");
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	private void titulo() {
		titulo = new Text("Título");
		titulo.getStyleClass().add("titulo");
		cabecalho.getChildren().add(titulo);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	private void conteudo() {
		conteudo = new VBox();
		conteudo.getStyleClass().add("conteudo");
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	private void corpo() {
		corpo = new BorderPane();
		corpo.setTop(cabecalho);
		corpo.setCenter(conteudo);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	private void principal() {
		principal = new BorderPane();
		principal.setLeft(barraLateral);
		principal.setCenter(corpo);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

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