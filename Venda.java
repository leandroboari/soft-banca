import java.util.*;

public class Venda {

	private static int lastId = 0;
	private int id;
	public ArrayList<ProdutoVenda> lista;
	private int itens;
	private double total;
	private String dataHora;
	private String meioPagamento;

	public Venda() {
		itens = 0;
		dataHora = null;
		lista = new ArrayList<ProdutoVenda>();
	}

	public void finalizar(String meioPagamento) {
		this.meioPagamento = meioPagamento;
		id = ++lastId;
		itens = 0;
		for(ProdutoVenda pv: lista) {
			itens += pv.getQuantidade();
		}
		dataHora = Conversor.DataHoraParaString(new Date());
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

	public boolean vazia() {
		if(lista.size() == 0) return true;
		return false;
	}

	public int getId() {
		return id;
	}

	public int getItens() {
		return itens;
	}

	public String getMeioPagamento() {
		return meioPagamento;
	}

	public void setMeioPagamento(String meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

}