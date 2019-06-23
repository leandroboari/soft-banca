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
	private Text tTotal;

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
		ChoiceBox<String> cbIntervalo = new ChoiceBox<String>(FXCollections.observableArrayList("Hoje", "Semana", "Mês"));
		cbIntervalo.getSelectionModel().selectFirst();
		Button btnDetalhes = new Button("Ver Detalhes");
		barraAcoes.getChildren().addAll(cbIntervalo, btnDetalhes);

		// Total
		tTotal = new Text();
		tTotal.getStyleClass().add("total");
		atualizaTotal();

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

		TableColumn<Venda, String> colunaTotal = new TableColumn<Venda, String>("Total");
		colunaTotal.setCellValueFactory(new PropertyValueFactory<Venda, String>("totalArredondado"));
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

		operadorVendas.preencherTabela(tabela);

		// Caso não tenham produtos inseridos
		tabela.setPlaceholder(new Label("Não foram encontradas vendas."));

		BorderPane conteudoVendas = new BorderPane();
		conteudoVendas.setTop(barraOpcoes);
		conteudoVendas.setCenter(tabela);

		// Botão Finalizar Venda
		btnDetalhes.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Venda vendaSelecionada = tabela.getSelectionModel().getSelectedItem();
				if(vendaSelecionada == null) {
					Alerta.erro("Por favor, é necessário selecionar uma venda.");
				} else {
					verDetalhes(vendaSelecionada);
				}
			}
		});

		super.layout.conteudo.getChildren().add(conteudoVendas);
	}

	private void verDetalhes(Venda venda) {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Detalhes da Venda");
		dialog.setHeaderText("Verifique as informações a seguir:");
		
		// Botões
		ButtonType btnFinalizar = new ButtonType("Finalizar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(btnFinalizar);
		
		VBox bDetalhes = new VBox();

		bDetalhes.getChildren().add(new Text("ID da venda: "+String.valueOf(venda.getId())));
		bDetalhes.getChildren().add(new Text("Qtd. de itens: "+String.valueOf(venda.getItens())));

		Pane espaco = new Pane();
		espaco.setPrefSize(10,10);
		bDetalhes.getChildren().add(espaco);

		int contaProdutos = 1;
		for(ProdutoVenda pv: venda.getLista()) {

			String nome = pv.getTitulo();
			bDetalhes.getChildren().add(new Text("Produto "+String.valueOf(contaProdutos)+": "+nome));

			int qtde = pv.getQuantidade();
			double preco = pv.getPreco();
			String valores = String.valueOf(qtde)+" x "+Conversor.DoubleParaPreco(preco, true)+" = "+Conversor.DoubleParaPreco(qtde * preco, true);
			bDetalhes.getChildren().add(new Text(valores));

			Pane espaco2 = new Pane();
			espaco2.setPrefSize(10,10);
			bDetalhes.getChildren().add(espaco2);

			contaProdutos++;
		}

		bDetalhes.getChildren().add(new Text("Total: "+Conversor.DoubleParaPreco(venda.getTotal(), true)));
		bDetalhes.getChildren().add(new Text("Meio de Pagamento: "+venda.getMeioPagamento()));

		dialog.getDialogPane().setContent(bDetalhes);
		Button verBtnFinalizar = (Button) dialog.getDialogPane().lookupButton(btnFinalizar);
		
		Optional<ButtonType> resultado = dialog.showAndWait();
	}

	private void atualizaTotal() {
		operadorVendas.atualizaTotal();
		tTotal.setText(operadorVendas.getTotalArredondado());
	}

}