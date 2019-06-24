import javafx.scene.image.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;

/**
* Classe com os elementos de interface da Página Inicial.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class InterfaceInicial extends Pagina {

	public static final String titulo = "Início";
	public static final Image icone = new Image("img/menu-inicial.png");
	private OperadorVendas operadorVendas;
	private Text qtdHoje;
	private Text valHoje;
	private Text qtdMes;
	private Text valMes;

	/**
	* Construtor principal da classe.
	* @param layout
	* @param operadorVendas
	*/

	public InterfaceInicial(Layout layout, OperadorVendas operadorVendas) {
		super(layout);
		super.alteraTitulo(titulo);
		super.selecionaBotao(layout.btnInicial);

		this.operadorVendas = operadorVendas;

		VBox vendas = new VBox();
		vendas.getStyleClass().add("vendas");

		HBox vendasLogo = new HBox();

		ImageView vendasIcon = new ImageView();
		vendasIcon.setImage(new Image("img/vendas.png"));
		vendasIcon.getStyleClass().add("logo");
		vendasIcon.setPreserveRatio(true);

		Text vendasLogoText = new Text("Vendas");
		vendasLogoText.setId("tituloVendas");

		vendasLogo.getChildren().addAll(vendasIcon, vendasLogoText);
		vendasLogo.getStyleClass().add("vendas-logo");

		VBox hoje = new VBox();
		hoje.getStyleClass().add("painelVendas");
		Text titHoje = new Text("Hoje");
		titHoje.setId("tituloVendas");
		qtdHoje = new Text("Não foram realizadas");
		qtdHoje.setId("conteudoVendas");
		valHoje = new Text("R$ 0,00");
		valHoje.setId("conteudoVendas");
		hoje.getChildren().addAll(titHoje, qtdHoje, valHoje);

		VBox mes = new VBox();
		mes.getStyleClass().add("painelVendas");
		Text titMes = new Text("Mês");
		titMes.setId("tituloVendas");
		qtdMes = new Text("Não foram realizadas");
		qtdMes.setId("conteudoVendas");
		valMes = new Text("R$ 0,00");
		valMes.setId("conteudoVendas");
		mes.getChildren().addAll(titMes, qtdMes, valMes);

		GridPane numerosVenda = new GridPane();
		numerosVenda.setHgap(50);
		numerosVenda.getStyleClass().add("numerosVenda");
		numerosVenda.add(hoje, 0, 0);
		numerosVenda.add(mes, 1, 0);

		BorderPane conteudoVendas = new BorderPane();
		conteudoVendas.setLeft(vendasLogo);
		conteudoVendas.setCenter(numerosVenda);
		conteudoVendas.getStyleClass().add("conteudoVendas");

		vendas.getChildren().add(conteudoVendas);

		atualizaEstatisticas();

		super.layout.conteudo.getChildren().add(vendas);
	}


	/**
	* Método responsável por atualizar os dados estatísticos de  venda.
	*/
	private void atualizaEstatisticas() {
		operadorVendas.atualizaEstatisticas();
		qtdHoje.setText(String.valueOf(operadorVendas.getQtdVendasHoje()) + " itens");
		valHoje.setText(Conversor.DoubleParaPreco(operadorVendas.getTotVendasHoje(), true));
		qtdMes.setText(String.valueOf(operadorVendas.getQtdVendasMes()) + " itens");
		valMes.setText(Conversor.DoubleParaPreco(operadorVendas.getTotVendasMes(), true));
	}
}