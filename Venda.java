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

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public Venda() {
		itens = 0;
		dataHora = null;
		lista = new ArrayList<ProdutoVenda>();
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public Venda(int lastId) {
		this.lastId = lastId;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void inserirQuantidade(Produto produto, int quantidade) {
		int posicao = lista.indexOf(produto);
		lista.get(posicao).inserirQuantidade(quantidade);
		atualizaTotal();
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void removeProduto(ProdutoVenda produto) {
		lista.remove(produto);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void atualizaTotal() {
		total = 0;
		for(ProdutoVenda produto: lista) {
			total += produto.getQuantidade() * produto.getPreco();
		}
		totalArredondado = Conversor.DoubleParaPreco(total, false);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public boolean vazia() {
		if(lista.size() == 0) return true;
		return false;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public int getId() {
		return id;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public int getItens() {
		return itens;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public String getMeioPagamento() {
		return meioPagamento;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void setMeioPagamento(String meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public double getTotal() {
		return total;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void setTotal(double total) {
		this.total = total;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public String getTotalArredondado() {
		return totalArredondado;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void setTotalArredondado(String total) {
		this.totalArredondado = total;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public String getDataHora() {
		return dataHora;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public ArrayList<ProdutoVenda> getLista() {
		return lista;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public static int getLastId() {
		return lastId;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	@Override
	public String toString() {
		return "Venda [id="+id+", lista="+lista+", itens="+itens+", total="+total+", totalArredondado="+totalArredondado+", dataHora="+dataHora+", meioPagamento="+meioPagamento+"]";
	}

}