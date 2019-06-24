import javafx.scene.image.*;

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

	public InterfaceConfiguracoes(Layout layout) {
		super(layout);
		super.alteraTitulo(titulo);
		super.selecionaBotao(layout.btnConfiguracoes);
	}
}