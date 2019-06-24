import javafx.scene.control.*;

public class Pagina {
	
	protected Layout layout;

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public Pagina(Layout layout) {
		this.layout = layout;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	// Método para alterar título
	protected void alteraTitulo(String titulo) {
		layout.titulo.setText(titulo);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	// Método ao clicar nos botões
	protected void selecionaBotao(Button btn) {
		layout.btnInicial.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnCaixa.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnEstoque.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnVendas.getStyleClass().remove("menuBotaoSelecionado");
		btn.getStyleClass().add("menuBotaoSelecionado");
		limpaConteudo();
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	// Limpa conteúdo da página
	protected void limpaConteudo() {
		layout.conteudo.getChildren().clear();
	}

}