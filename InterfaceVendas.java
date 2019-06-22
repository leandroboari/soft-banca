import javafx.scene.control.*;
import javafx.scene.image.*;

public class InterfaceVendas extends Pagina {

	public static final String titulo = "Vendas";
	public static final Image icone = new Image("img/menu-vendas.png");

	public InterfaceVendas(Layout layout) {
		super(layout);
		super.alteraTitulo(titulo);
		super.selecionaBotao(layout.btnVendas);
	}

}