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

public class InterfaceEstoque extends Pagina {

	public static final String titulo = "Estoque";
	public static final Image icone = new Image("img/menu-estoque.png");

	private OperadorEstoque operadorEstoque;
	private TableView<Produto> tabela;

	public InterfaceEstoque(Layout layout, OperadorEstoque operadorEstoque) {
		super(layout);
		super.alteraTitulo(titulo);
		super.selecionaBotao(layout.btnEstoque);

		this.operadorEstoque = operadorEstoque;

		// Barra de Opções

		// Barra de Ações
		HBox barraAcoes = new HBox();
		barraAcoes.getStyleClass().add("barraOpcoes");

		// Botões
		Button btnEstoque = new Button("Inserir");
		Button btnEditar = new Button("Editar");
		Button btnDeletar = new Button("Deletar");
		Button btnAdicionar = new Button("Adicionar Produto");
		barraAcoes.getChildren().addAll(btnEstoque, btnEditar, btnDeletar, btnAdicionar);

		// Campo de texto e botão
		TextField tfBusca = new TextField();
		tfBusca.setPromptText("Filtre pela ID ou Título");
		Button btnBuscar = new Button("Buscar");

		// Busca
		HBox barraBusca = new HBox();
		barraBusca.getStyleClass().add("barraOpcoes");
		barraBusca.getChildren().addAll(tfBusca, btnBuscar);

		BorderPane barraOpcoes = new BorderPane();
		barraOpcoes.setLeft(barraAcoes);
		barraOpcoes.setRight(barraBusca);

		// Tabela
		tabela = new TableView<Produto>();

		// Define Colunas
		TableColumn<Produto, Integer> colunaId = new TableColumn<Produto, Integer>("ID");
		colunaId.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("id"));
		tabela.getColumns().add(colunaId);

		TableColumn<Produto, String> colunaTitulo = new TableColumn<Produto, String>("Título");
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<Produto, String>("titulo"));
		tabela.getColumns().add(colunaTitulo);

		TableColumn<Produto, String> colunaPreco = new TableColumn<Produto, String>("Preço");
		colunaPreco.setCellValueFactory(new PropertyValueFactory<Produto, String>("precoArredondado"));
		tabela.getColumns().add(colunaPreco);

		TableColumn<Produto, Integer> colunaEstoque = new TableColumn<Produto, Integer>("Estoque");
		colunaEstoque.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("qtdEstoque"));
		tabela.getColumns().add(colunaEstoque);

		TableColumn<Produto, String> colunaEntrada = new TableColumn<Produto, String>("Entrada");
		colunaEntrada.setCellValueFactory(new PropertyValueFactory<Produto, String>("dataEntrada"));
		tabela.getColumns().add(colunaEntrada);

		// Define largura e altura da tabela
		tabela.prefHeightProperty().bind(layout.conteudo.heightProperty());
		tabela.prefWidthProperty().bind(layout.conteudo.widthProperty());

		atualizarTabela();

		// Caso não tenham produtos inseridos
		tabela.setPlaceholder(new Label("Não foram encontrados produtos."));

		// Botão Adicionar
		btnAdicionar.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				editarProduto(null);
			}
		});

		// Botão Editar
		btnEditar.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Produto produtoSelecionado = tabela.getSelectionModel().getSelectedItem();
				if(produtoSelecionado == null) {
					Alerta.erro("Por favor, é necessário selecionar um produto.");
				} else {
					editarProduto(produtoSelecionado);
				}
			}
		});

		// Botão Deletar
		btnDeletar.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Produto produtoSelecionado = tabela.getSelectionModel().getSelectedItem();
				if(produtoSelecionado == null) {
					Alerta.erro("Por favor, é necessário selecionar um produto.");
				} else {
					if(Alerta.confirmacao("Você realmente deseja deletar este item?")) {
						operadorEstoque.remover(produtoSelecionado);
						atualizarTabela();
					}
				}
			}
		});

		// Botão Inserir
		btnEstoque.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Produto produtoSelecionado = tabela.getSelectionModel().getSelectedItem();
				if(produtoSelecionado == null) {
					Alerta.erro("Por favor, é necessário selecionar um produto.");
				} else {
					inserirEstoque(produtoSelecionado);
				}
			}
		});

		// Botão Buscar
		btnBuscar.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				realizarBusca(tfBusca.getText());
			}
		});

		BorderPane conteudoProdutos = new BorderPane();
		conteudoProdutos.setTop(barraOpcoes);
		conteudoProdutos.setCenter(tabela);

		super.layout.conteudo.getChildren().add(conteudoProdutos);
	}

	private void editarProduto(Produto produto) {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Editar Produto");
		dialog.setHeaderText("Verifique as informações a seguir:");
		
		// Botões
		ButtonType btnFinalizar = new ButtonType("Finalizar", ButtonData.OK_DONE);
		ButtonType btnCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(btnFinalizar, btnCancelar);
		
		// Grid
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		TextField tfTitulo = new TextField();
		tfTitulo.setPromptText("Por escrever...");
		TextField tfPreco = new TextField();
		tfPreco.setPromptText("Por escrever...");
		TextField tfEstoque = new TextField();
		tfEstoque.setPromptText("Por escrever...");
		DatePicker tfData = new DatePicker();
		tfData.setPromptText("Por escrever...");
		LocalDate ld;
		if(produto != null) {
			tfTitulo.setText(produto.getTitulo());
			tfPreco.setText(String.valueOf(produto.getPreco()));
			tfEstoque.setText(String.valueOf(produto.getQtdEstoque()));
			ld = LocalDate.parse(produto.getDataEntrada(), Conversor.formataData);
			tfData.setValue(ld);
		}
		tfData.setConverter(new StringConverter<LocalDate>() {
			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return Conversor.formataData.format(date);
				} else {
					return "";
				}
			}
			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, Conversor.formataData);
				} else {
					return null;
				}
			}
		});
		grid.add(new Label("Título:"), 0, 0);
		grid.add(tfTitulo, 1, 0);
		grid.add(new Label("Preco:"), 0, 1);
		grid.add(tfPreco, 1, 1);
		grid.add(new Label("Estoque:"), 0, 2);
		grid.add(tfEstoque, 1, 2);
		grid.add(new Label("Data:"), 0, 3);
		grid.add(tfData, 1, 3);
		dialog.getDialogPane().setContent(grid);
		Button verBtnFinalizar = (Button) dialog.getDialogPane().lookupButton(btnFinalizar);
		verBtnFinalizar.addEventFilter(ActionEvent.ACTION, event -> {
			ArrayList<String> erros = new ArrayList<String>();
			if(ValidaFormulario.nulo(tfTitulo))
				erros.add("Título em branco");
			if(ValidaFormulario.nulo(tfPreco))
				erros.add("Preço em branco");
			if(ValidaFormulario.nulo(tfEstoque))
				erros.add("Estoque em branco");
			if(ValidaFormulario.nulo(tfData))
				erros.add("Data em branco ou inválida");
			if(!ValidaFormulario.decimal(tfPreco))
				erros.add("O preço não é um número decimal");
			if(!ValidaFormulario.inteiro(tfEstoque))
				erros.add("O estoque não é um número inteiro");
			if(erros.size() > 0) {
				Alerta.listaErros(erros);
				event.consume();
			}
		});
		Optional<ButtonType> resultado = dialog.showAndWait();
		if(resultado.get() == btnFinalizar) {
			if(produto == null) {
				operadorEstoque.adicionar(new Produto(tfTitulo.getText(),
							Conversor.StringParaDouble(tfPreco.getText()),
							Conversor.StringParaInt(tfEstoque.getText()),
							Conversor.DataParaString(tfData.getValue())));
			} else {
				operadorEstoque.editar(produto, tfTitulo.getText(),
								Conversor.StringParaDouble(tfPreco.getText()),
								Conversor.StringParaInt(tfEstoque.getText()),
								Conversor.DataParaString(tfData.getValue()));
			}
			atualizarTabela();
		}
	}

	private void inserirEstoque(Produto produto) {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Inserir Estoque");
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
			produto.inserirEstoque(Conversor.StringParaInt(tfQuantidade.getText()));
			atualizarTabela();
		}
	}

	private void atualizarTabela() {
		tabela.getItems().clear();
		for (Produto produto: operadorEstoque.lista) {
			tabela.getItems().add(produto);
		}
	}

	private void realizarBusca(String filtro) {
		if(filtro.equals("")) {
			atualizarTabela();
			return;
		}
		tabela.getItems().clear();
		for (Produto produto: operadorEstoque.lista) {
			String titulo = produto.getTitulo();
			String id = String.valueOf(produto.getId());
			if(titulo.indexOf(filtro) != -1 || id.indexOf(filtro) != -1) {
				tabela.getItems().add(produto);
			}
		}
	}

}