import javafx.scene.image.*;

/**
* Descrição da classe.
*/

public class InterfaceConfiguracoes extends Pagina {

	public static final String titulo = "Configurações";
	public static final Image icone = new Image("img/menu-configuracoes.png");

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public InterfaceConfiguracoes(Layout layout) {
		super(layout);
		super.alteraTitulo(titulo);
		super.selecionaBotao(layout.btnConfiguracoes);
	}
}