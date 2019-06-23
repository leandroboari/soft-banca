import java.util.*;
import java.io.Serializable;

public class Venda implements Serializable {

	private static final long serialVersionUID = 19L;
	transient private static int lastId = 0;
	private int id;
	private ArrayList<ProdutoVenda> lista;
	private int itens;
	private double total;
	private String totalArredondado;
	private String dataHora;
	private String meioPagamento;

	public Venda() {
		itens = 0;
		dataHora = null;
		lista = new ArrayList<ProdutoVenda>();
	}

	public Venda(int lastId) {
		this.lastId = lastId;
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

	public void inserirQuantidade(Produto produto, int quantidade) {
		int posicao = lista.indexOf(produto);
		lista.get(posicao).inserirQuantidade(quantidade);
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

	public void atualizaTotal() {
		total = 0;
		for(ProdutoVenda produto: lista) {
			total += produto.getQuantidade() * produto.getPreco();
		}
		totalArredondado = Conversor.DoubleParaPreco(total, false);
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

	public String getTotalArredondado() {
		return totalArredondado;
	}

	public void setTotalArredondado(String total) {
		this.totalArredondado = total;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public ArrayList<ProdutoVenda> getLista() {
		return lista;
	}

	public static int getLastId() {
		return lastId;
	}

	@Override
	public String toString() {
		return "Venda [id="+id+", lista="+lista+", itens="+itens+", total="+total+", totalArredondado="+totalArredondado+", dataHora="+dataHora+", meioPagamento="+meioPagamento+"]";
	}

}