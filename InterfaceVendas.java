import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.util.*;
import javafx.scene.control.ButtonBar.*;
import javafx.collections.FXCollections;

public class InterfaceVendas extends Pagina {

	public static final String titulo = "Vendas";
	public static final Image icone = new Image("img/menu-vendas.png");

	private OperadorVendas operadorVendas;
	private TableView<Venda> tabela;

	public InterfaceVendas(Layout layout, OperadorVendas operadorVendas) {
		super(layout);
		super.alteraTitulo(titulo);
		super.selecionaBotao(layout.btnVendas);

		this.operadorVendas = operadorVendas;

		// Barra de Opções

		// Barra de Ações
		HBox barraAcoes = new HBox();
		barraAcoes.getStyleClass().add("barraOpcoes");

		// Botões
		ChoiceBox cbIntervalo = new ChoiceBox(FXCollections.observableArrayList("Hoje", "Semana", "Mês"));
		cbIntervalo.getSelectionModel().selectFirst();
		Button btnDetalhes = new Button("Ver Detalhes");
		barraAcoes.getChildren().addAll(cbIntervalo, btnDetalhes);

		// Busca
		Text tTotal = new Text("R$ 0,00");
		tTotal.getStyleClass().add("total");

		BorderPane barraOpcoes = new BorderPane();
		barraOpcoes.setLeft(barraAcoes);
		barraOpcoes.setRight(tTotal);

		// Tabela
		tabela = new TableView<Venda>();

		// Define Colunas
		TableColumn<Venda, Integer> colunaId = new TableColumn<Venda, Integer>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("id"));
		tabela.getColumns().add(colunaId);

		TableColumn<Venda, Integer> colunaItens = new TableColumn<Venda, Integer>("Itens");
		colunaItens.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("itens"));
		tabela.getColumns().add(colunaItens);

		TableColumn<Venda, Double> colunaTotal = new TableColumn<Venda, Double>("Total");
		colunaTotal.setCellValueFactory(new PropertyValueFactory<Venda, Double>("total"));
		tabela.getColumns().add(colunaTotal);

		TableColumn<Venda, String> colunaData = new TableColumn<Venda, String>("Data e Hora");
		colunaData.setCellValueFactory(new PropertyValueFactory<Venda, String>("dataHora"));
		tabela.getColumns().add(colunaData);

		TableColumn<Venda, String> colunaMP = new TableColumn<Venda, String>("Pgto.");
		colunaMP.setCellValueFactory(new PropertyValueFactory<Venda, String>("meioPagamento"));
		tabela.getColumns().add(colunaMP);

		// Define largura e altura da tabela
		tabela.prefHeightProperty().bind(layout.conteudo.heightProperty());
		tabela.prefWidthProperty().bind(layout.conteudo.widthProperty());

		atualizarTabela();

		// Caso não tenham produtos inseridos
		tabela.setPlaceholder(new Label("Não foram encontradas vendas."));

		BorderPane conteudoVendas = new BorderPane();
		conteudoVendas.setTop(barraOpcoes);
		conteudoVendas.setCenter(tabela);

		super.layout.conteudo.getChildren().add(conteudoVendas);
	}

	private void atualizarTabela() {
		tabela.getItems().clear();
		for (Venda venda: operadorVendas.lista) {
			tabela.getItems().add(venda);
		}
	}

}