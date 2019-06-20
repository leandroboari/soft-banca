import java.util.*;

public class Venda {

	private static int lastId = 0;
	private int id;
	public ArrayList<ProdutoVenda> lista;
	private double total;
	private boolean finalizada;

	public Venda() {
		id = ++lastId;
		finalizada = false;
		lista = new ArrayList<ProdutoVenda>();
	}

	public void finalizar() {
		finalizada = true;
	}

	public void inserirProduto(ProdutoVenda produto) {
		lista.add(produto);
	}

	public void inserirProduto(Produto produto, int quantidade) {
		int id = produto.getId();
		String titulo = produto.getTitulo();
		double preco = produto.getPreco();
		int qtdEstoque = produto.getQtdEstoque();
		String dataEntrada = produto.getDataEntrada();
		ProdutoVenda novoProduto = new ProdutoVenda(id, quantidade, titulo, preco, qtdEstoque, dataEntrada);
		lista.add(novoProduto);
	}

	public void removeProduto(ProdutoVenda produto) {
		lista.remove(lista);
	}

	public void setTotal(double total) {
		this.total = total;
	}

}