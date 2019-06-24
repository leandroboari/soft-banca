import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class Login {

	// Janela
	private Autenticacao autenticacao;
	private Scene cena;
	private Stage stage;
	private Layout layout;
	private ImageView logo;
	private VBox principal;

	public Login(Stage stage, Layout layout, Autenticacao autenticacao) {
		this.stage = stage;
		this.layout = layout;
		this.autenticacao = autenticacao;
		principal();
		cena();
	}

	/**
	* Layout de login.
	*/

	private void principal() {

		logo = new ImageView();
		logo.setImage(new Image("img/logo.png"));
		logo.getStyleClass().add("logo");
		logo.setPreserveRatio(true);

		principal = new VBox();
		principal.getStyleClass().add("login");

		TextField senha = new TextField();
		senha.setPromptText("Senha de autenticação");
		senha.setMaxWidth(200);

		Button logar = new Button("Entrar");

		principal.getChildren().addAll(logo, senha, logar);

		logar.setOnAction(e -> {
			if(autenticacao.autentica(senha.getText())) {
				layout.iniciar();
			} else {
				Alerta.erro("Senha incorreta. Tente novamente!");
			}
		});
	}

	/**
	* Inicia a janela.
	*/

	private void cena() {
		cena = new Scene(principal, 400, 300);
		cena.getStylesheets().add("styles.css");
		stage.setTitle("SysBanca - Login");
		stage.setScene(cena);
		stage.getIcons().add(new Image("file:img/icon.png"));
		stage.show();
	}
}