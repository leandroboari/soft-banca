import java.util.*;
import java.io.Serializable;

/**
* Classe que caracteriza os atributos em métodos de uma Venda.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

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

	/**
	* Construtor principal da classe.
	*/

	public Venda() {
		itens = 0;
		dataHora = null;
		lista = new ArrayList<ProdutoVenda>();
	}

	/**
	* Construtor para referenciar a última ID estatitcamente.
	* @param lastId
	*/

	public Venda(int lastId) {
		this.lastId = lastId;
	}

	/**
	* Finalizar a condição de uma venda para finalizada.
	* @param meioPagamento Meio de Pagamento.
	*/

	public void finalizar(String meioPagamento) {
		this.meioPagamento = meioPagamento;
		id = ++lastId;
		itens = 0;
		for(ProdutoVenda pv: lista) {
			itens += pv.getQuantidade();
		}
		dataHora = Conversor.DataHoraParaString(new Date());
	}

	/**
	* Insere uma quantidade de itens na venda 
	* @param produto Produto a ser modificado.
	* @param quantidade Quantidade a ser inserida.
	*/

	public void inserirQuantidade(Produto produto, int quantidade) {
		int posicao = lista.indexOf(produto);
		lista.get(posicao).inserirQuantidade(quantidade);
		atualizaTotal();
	}

	/**
	* Inserir novo produto na venda.
	* @param produto Produto a ser inserido.
	* @param quantidade Quantidade a ser inserida.
	*/

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

	/**
	* Remover um produto da venda.
	* @param produto Produto a ser removido.
	*/

	public void removeProduto(ProdutoVenda produto) {
		lista.remove(produto);
	}

	/**
	* Atualiza total da venda de acordo com a lista de produtos.
	*/

	public void atualizaTotal() {
		total = 0;
		for(ProdutoVenda produto: lista) {
			total += produto.getQuantidade() * produto.getPreco();
		}
		totalArredondado = Conversor.DoubleParaPreco(total, false);
	}

	/**
	* Verifica se a venda está vazia de produtos.
	* @return boolean
	*/

	public boolean vazia() {
		if(lista.size() == 0) return true;
		return false;
	}

	/**
	* Acessa a Identificação da venda.
	* @return int
	*/

	public int getId() {
		return id;
	}

	/**
	* Acessa a quantidade de itens de um determinado produto.
	* @return int
	*/

	public int getItens() {
		return itens;
	}

	/**
	* Acessa o meio de pagamento para a venda.
	* @return String
	*/

	public String getMeioPagamento() {
		return meioPagamento;
	}

	/**
	* Altera meio de pagamento para a venda.
	* @param meioPagamento Meio de pagamento para a venda.
	*/

	public void setMeioPagamento(String meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	/**
	* Acessa o total da venda.
	* @return double
	*/

	public double getTotal() {
		return total;
	}

	/**
	* Altera o total da venda.
	* @param total total da venda.
	*/

	public void setTotal(double total) {
		this.total = total;
	}

	/**
	* Acessa o total arredondado da venda.
	* @return String
	*/

	public String getTotalArredondado() {
		return totalArredondado;
	}

	/**
	* Altera o total arredondado da venda.
	* @param total
	*/

	public void setTotalArredondado(String total) {
		this.totalArredondado = total;
	}

	/**
	* Acessa a data e hora que a venda foi realizada.
	* @return String Data e hora que a venda foi realizada.
	*/

	public String getDataHora() {
		return dataHora;
	}

	/**
	* Altera a data e hora que a venda foi realizada.
	* @param dataHora Data e hora que a venda foi realizada.
	*/

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	/**
	* Lista de produtos inseridos na venda.
	* @return ArrayList<ProdutoVenda>
	*/

	public ArrayList<ProdutoVenda> getLista() {
		return lista;
	}

	/**
	* Acessa a última Identidade de venda.
	* @return int
	*/

	public static int getLastId() {
		return lastId;
	}

	/**
	* Serialização da classe.
	* @return String valores a serem descritos.
	*/

	@Override
	public String toString() {
		return "Venda [id="+id+", lista="+lista+", itens="+itens+", total="+total+", totalArredondado="+totalArredondado+", dataHora="+dataHora+", meioPagamento="+meioPagamento+"]";
	}

}