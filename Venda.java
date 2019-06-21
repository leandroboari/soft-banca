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
		atualizaTotal();
	}

	public void inserirProduto(Produto produto, int quantidade) {
		int id = produto.getId();
		String titulo = produto.getTitulo();
		double preco = produto.getPreco();
		int qtdEstoque = produto.getQtdEstoque();
		String dataEntrada = produto.getDataEntrada();
		ProdutoVenda novoProduto = new ProdutoVenda(id, quantidade, titulo, preco, qtdEstoque, dataEntrada);
		lista.add(novoProduto);
		atualizaTotal();
	}

	public void removeProduto(ProdutoVenda produto) {
		lista.remove(produto);
	}

	public void inserirQuantidade(Produto produto, int quantidade) {
		int posicao = lista.indexOf(produto);
		lista.get(posicao).inserirQuantidade(quantidade);
		atualizaTotal();
	}

	private void atualizaTotal() {
		total = 0;
		for(ProdutoVenda produto: lista) {
			total += produto.getQuantidade() * produto.getPreco();
		}
	}

	public double getTotal() {
		return total;
	}

}