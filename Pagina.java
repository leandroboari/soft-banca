import javafx.scene.control.*;

public class Pagina {
	
	protected Layout layout;

	public Pagina(Layout layout) {
		this.layout = layout;
	}

	// Método para alterar título
	protected void alteraTitulo(String titulo) {
		layout.titulo.setText(titulo);
	}

	// Método ao clicar nos botões
	protected void selecionaBotao(Button btn) {
		layout.btnInicial.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnCaixa.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnEstoque.getStyleClass().remove("menuBotaoSelecionado");
		layout.btnVendas.getStyleClass().remove("menuBotaoSelecionado");
		btn.getStyleClass().add("menuBotaoSelecionado");
		limpaConteudo();
	}

	// Limpa conteúdo da página
	protected void limpaConteudo() {
		layout.conteudo.getChildren().clear();
	}

}