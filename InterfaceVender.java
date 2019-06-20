import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.util.*;
import javafx.scene.control.ButtonBar.*;
import javafx.application.Platform;

public class InterfaceVender extends Pagina {

	public static final String titulo = "Vender";
	public static final Image icone = new Image("img/menu-vender.png");

	private OperadorProduto operadorProduto;

	private TableView<Produto> tblListaProdutos;
	private TableView<ProdutoVenda> tblCaixa;
	private Venda venda;

	private TextField tfBusca;

	public InterfaceVender(Layout layout, OperadorProduto operadorProduto) {
		super(layout);
		super.alteraTitulo(titulo);
		super.selecionaBotao(layout.btnVender);

		this.operadorProduto = operadorProduto;

		// Instanciar nova venda
		venda = new Venda();

		SplitPane splitPane = new SplitPane();
		splitPane.prefHeightProperty().bind(layout.conteudo.heightProperty());

		super.layout.conteudo.getChildren().add(splitPane);

		// Lado esquerdo

		// Barra de opções
		tfBusca = new TextField();
		tfBusca.setPromptText("Insira a ID ou o Título");
		Button btnBuscar = new Button("Buscar");
		HBox.setHgrow(tfBusca, Priority.ALWAYS);
		HBox barraListaProdutos = new HBox();
		barraListaProdutos.getStyleClass().add("barraOpcoes");
		barraListaProdutos.getChildren().addAll(tfBusca, btnBuscar);

		// Botão Buscar
		btnBuscar.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				realizarBusca(tfBusca.getText());
			}
		});

		// Tabela
		tblListaProdutos = new TableView<Produto>();
		tblListaProdutos.setPlaceholder(new Label("É necessário inserir uma ID ou título."));

		// Define Colunas
		TableColumn<Produto, Integer> colunaId1 = new TableColumn<Produto, Integer>("ID");
		colunaId1.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("id"));
		tblListaProdutos.getColumns().add(colunaId1);

		TableColumn<Produto, String> colunaTitulo1 = new TableColumn<Produto, String>("Título");
		colunaTitulo1.setCellValueFactory(new PropertyValueFactory<Produto, String>("titulo"));
		tblListaProdutos.getColumns().add(colunaTitulo1);

		TableColumn<Produto, Double> colunaPreco1 = new TableColumn<Produto, Double>("Preço");
		colunaPreco1.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));
		tblListaProdutos.getColumns().add(colunaPreco1);

		BorderPane conteudoListaProdutos = new BorderPane();
		conteudoListaProdutos.setStyle("-fx-padding: 0 5 0 0");
		conteudoListaProdutos.setTop(barraListaProdutos);
		conteudoListaProdutos.setCenter(tblListaProdutos);

		tblListaProdutos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				Produto produtoSelecionado = tblListaProdutos.getSelectionModel().getSelectedItem();
				solicitaQuantidade(produtoSelecionado);
			}
		});

		// Lado direito

		// Barra de opções
		Button btnFinalizar = new Button("Finalizar caixa");
		
		BorderPane barraCaixa = new BorderPane();
		barraCaixa.getStyleClass().add("barraOpcoes");
		barraCaixa.setRight(btnFinalizar);

		// Tabela
		tblCaixa = new TableView<ProdutoVenda>();
		tblCaixa.setPlaceholder(new Label("Aguardando a seleção de um produto."));

		TableColumn<ProdutoVenda, Integer> colunaQtd2 = new TableColumn<ProdutoVenda, Integer>("Qtd.");
		colunaQtd2.setCellValueFactory(new PropertyValueFactory<ProdutoVenda, Integer>("quantidade"));
		tblCaixa.getColumns().add(colunaQtd2);

		TableColumn<ProdutoVenda, Integer> colunaId2 = new TableColumn<ProdutoVenda, Integer>("ID");
		colunaId2.setCellValueFactory(new PropertyValueFactory<ProdutoVenda, Integer>("id"));
		tblCaixa.getColumns().add(colunaId2);

		TableColumn<ProdutoVenda, String> colunaTitulo2 = new TableColumn<ProdutoVenda, String>("Título");
		colunaTitulo2.setCellValueFactory(new PropertyValueFactory<ProdutoVenda, String>("titulo"));
		tblCaixa.getColumns().add(colunaTitulo2);

		TableColumn<ProdutoVenda, Double> colunaPreco2 = new TableColumn<ProdutoVenda, Double>("Preço");
		colunaPreco2.setCellValueFactory(new PropertyValueFactory<ProdutoVenda, Double>("preco"));
		tblCaixa.getColumns().add(colunaPreco2);

		BorderPane conteudoCaixa = new BorderPane();
		conteudoCaixa.setStyle("-fx-padding: 0 0 0 5");
		conteudoCaixa.setTop(barraCaixa);
		conteudoCaixa.setCenter(tblCaixa);

		// Divisão do conteúdo
		splitPane.getItems().addAll(conteudoListaProdutos, conteudoCaixa);
	}

	private void realizarBusca(String filtro) {
		tblListaProdutos.getItems().clear();
		for (Produto produto: operadorProduto.lista) {
			String titulo = produto.getTitulo();
			String id = String.valueOf(produto.getId());
			if(titulo.indexOf(filtro) != -1 || id.indexOf(filtro) != -1) {
				tblListaProdutos.getItems().add(produto);
			}
		}
	}

	public void solicitaQuantidade(Produto produto) {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Quantidade");
		dialog.setHeaderText("Adicione a quantidade:");
		// Botões
		ButtonType btnFinalizar = new ButtonType("Finalizar", ButtonData.OK_DONE);
		ButtonType btnCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(btnFinalizar, btnCancelar);
		TextField tfQuantidade = new TextField();
		HBox conteudo = new HBox();
		conteudo.setPadding(new Insets(10));
		conteudo.getChildren().add(tfQuantidade);
		dialog.getDialogPane().setContent(conteudo);
		Button getBtnFinalizar = (Button) dialog.getDialogPane().lookupButton(btnFinalizar);
		getBtnFinalizar.addEventFilter(ActionEvent.ACTION, event -> {
			ArrayList<String> erros = new ArrayList<String>();
			if(ValidaFormulario.nulo(tfQuantidade))
				erros.add("Quantidade vazia");
			if(!ValidaFormulario.inteiro(tfQuantidade))
				erros.add("O valor inserido não é um número inteiro");
			if(erros.size() > 0) {
				Alerta.listaErros(erros);
				event.consume();
			}
		});
		Optional<ButtonType> resultado = dialog.showAndWait();
		if(resultado.get() == btnFinalizar) {

			// Quantidade de itens para o caixa
			int quantidade = Conversor.StringParaInt(tfQuantidade.getText());

			// Produto agora será um ProdutoVenda
			insereCaixa(produto, quantidade);

		}

		// Necessário para executar mudanças na interface após encerrar diálogo
		Platform.runLater(new Runnable() {
			@Override public void run() {
				limpaBusca();
				atualizaCaixa();
			}
		});
	}

	private void limpaBusca() {
		tblListaProdutos.getItems().clear();
		tfBusca.setText("");
	}

	private void atualizaCaixa() {
		tblCaixa.getItems().clear();
		for (ProdutoVenda produto: venda.lista) {
			tblCaixa.getItems().add(produto);
		}
	}

	private void insereCaixa(Produto produto, int quantidade) {

		// Verifica se já existe em caixa o produto
		ProdutoVenda produtoEmCaixa = null;
		for (ProdutoVenda produtoVenda: venda.lista) {
			if(produtoVenda.getId() == produto.getId()) 
				produtoEmCaixa = produtoVenda;
		}
		
		// Caso tenha em caixa, adiciona quantidade
		if(produtoEmCaixa != null)
			produtoEmCaixa.adicionaQuantidade(quantidade);

		// caso não tenha, o Produto será adicionado em Venda como ProdutoVenda
		else venda.inserirProduto(produto, quantidade);

	}
}