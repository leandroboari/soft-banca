import javafx.scene.control.*;

/**
* Superclasse com os elementos que compõem uma página.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class Pagina {
	
	protected Layout layout;

	/**
	* Construtor principal da classe.
	* @param layout
	*/

	public Pagina(Layout layout) {
		this.layout = layout;
	}

	/**
	* Altera título da página.
	* @param titulo
	*/

	protected void alteraTitulo(String titulo) {
		layout.titulo.setText(titulo);
	}

	/**
	* Seleciona botão que corresponde a página atual.
	* @param btn
	*/

	protected void selecionaBotao(Button btn) {
		layout.btnInicial.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnCaixa.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnEstoque.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnVendas.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnConfiguracoes.getStyleClass().remove("menuBotaoSelecionado");
		btn.getStyleClass().add("menuBotaoSelecionado");
		limpaConteudo();
	}

	/**
	* Limpa conteúdo ao transitar páginas.
	*/

	// Limpa conteúdo da página
	protected void limpaConteudo() {
		layout.conteudo.getChildren().clear();
	}

}