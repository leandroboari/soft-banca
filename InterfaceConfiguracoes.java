import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;

/**
* Classe com os elementos de interface da Página de Configurações.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class InterfaceConfiguracoes extends Pagina {

	public static final String titulo = "Configurações";
	public static final Image icone = new Image("img/menu-configuracoes.png");

	/**
	* Construtor principal da classe.
	* @param layout Layout do sistema.
	*/

	private Autenticacao autenticacao;

	public InterfaceConfiguracoes(Layout layout, Autenticacao autenticacao) {
		super(layout);
		super.alteraTitulo(titulo);
		super.selecionaBotao(layout.btnConfiguracoes);
		this.autenticacao = autenticacao;

		VBox lAlterarSenha = new VBox();
		lAlterarSenha.setSpacing(10);

		Text titulo = new Text("Alterar senha");
		titulo.getStyleClass().add("titulo");

		TextField senhaAtual = new TextField();
		senhaAtual.setPromptText("Senha atual");
		senhaAtual.setMaxWidth(200);

		TextField senhaNova = new TextField();
		senhaNova.setPromptText("Nova senha");
		senhaNova.setMaxWidth(200);

		TextField senhaNovaDenovo = new TextField();
		senhaNovaDenovo.setPromptText("Nova senha Novamente");
		senhaNovaDenovo.setMaxWidth(200);

		Button alterarSenha = new Button("Alterar");

		lAlterarSenha.getChildren().addAll(titulo, senhaAtual, senhaNova, senhaNovaDenovo, alterarSenha);

		super.layout.conteudo.getChildren().add(lAlterarSenha);

		alterarSenha.setOnAction(e -> {
			if(autenticacao.autentica(senhaAtual.getText())) {
				if(senhaNova.getText().equals(senhaNovaDenovo.getText()))
					autenticacao.alterarSenha(senhaNova.getText());
					Alerta.sucesso("Senha modificada com sucesso");
			} else {
				Alerta.erro("Senha incorreta. Tente novamente!");
			}
		});
	}
}